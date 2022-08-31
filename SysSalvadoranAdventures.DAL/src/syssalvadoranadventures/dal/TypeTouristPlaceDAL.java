/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syssalvadoranadventures.dal;
import java.util.*;
import java.sql.*;
import syssalvadoranadventures.el.*;


/**
 *
 * @author Abdias_Hernandez
 */
public class TypeTouristPlaceDAL {
    static String getCampos(){
        return "p.IdTypeTouristPlace, p.TypeTouristPlaceName";
    }
    
     // Metodo para obtener el SELECT a la tabla Rol y el TOP en el caso que se utilice una base de datos SQL SERVER
    private static String getSelect(TypeTouristPlace pTypeTouristPlace) {
        String sql;
        sql = "SELECT ";
        if (pTypeTouristPlace.getTop_aux() > 0 && ComunDB.typeDB == ComunDB.TypeDB.SQLSERVER) {
            // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
            sql += "TOP " + pTypeTouristPlace.getTop_aux() + " ";
        }
        sql += (getCampos() + " FROM TypeTouristPlace p"); // Agregar los campos de la tabla de TypeTouristPlace mas el FROM a la tabla Rol con su alias "p"
        return sql;
    }
    
    // Metodo para obtener Order by a la consulta SELECT de la tabla Rol y ordene los registros de mayor a menor 
    private static String addOrderBy(TypeTouristPlace pTypeTouristPlace) {
        String sql = " ORDER BY p.IdTypeTouristPlace DESC";
        if (pTypeTouristPlace.getTop_aux() > 0 && ComunDB.typeDB == ComunDB.TypeDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de Rol en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + pTypeTouristPlace.getTop_aux() + " ";
        }
        return sql;
    }
    
    // Metodo para poder insertar un nuevo registro en la tabla de Rol
    public static int create(TypeTouristPlace pTypeTouristPlace) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "INSERT INTO TypeTouristPlace(TypeTouristPlaceName) VALUES(?)"; // Definir la consulta INSERT a la tabla de Rol utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, pTypeTouristPlace.getTypeTouristPlaceName()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                result = ps.executeUpdate(); // Ejecutar la consulta INSERT en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el INSERT en la base de datos 
    }
    
    // Metodo para poder actualizar un registro en la tabla de Rol
    public static int update(TypeTouristPlace pTypeTouristPlace) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "UPDATE TypeTouristPlace SET TypeTouristPlaceName=? WHERE IdTypeTouristPlace=?"; // Definir la consulta UPDATE a la tabla de Rol utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, pTypeTouristPlace.getTypeTouristPlaceName()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setInt(2, pTypeTouristPlace.getIdTypeTouristPlace()); // Agregar el parametro a la consulta donde estan el simbolo ? #2  
                result = ps.executeUpdate(); // Ejecutar la consulta UPDATE en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el UPDATE en la base de datos 
    }
    
    // Metodo para poder eliminar un registro en la tabla de Rol
    public static int delete(TypeTouristPlace pTypeTouristPlace) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "DELETE FROM TypeTouristPlace WHERE IdTypeTouristPlace=?";  // Definir la consulta DELETE a la tabla de Rol utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pTypeTouristPlace.getIdTypeTouristPlace()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                result = ps.executeUpdate();  // Ejecutar la consulta DELETE en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el DELETE en la base de datos 
    } 
    
    // Metodo para llenar las propiedades de la entidad de TypeTouristPlace con los datos que viene en el ResultSet,
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignDataResultSet(TypeTouristPlace pTypeTouristPlace, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT p.IdTypeTouristPlace(indice 1),p.TypeTouristPlaceName(indice 2) * FROM TypeTouristPlace
        pIndex++;
        pTypeTouristPlace.setIdTypeTouristPlace(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pTypeTouristPlace.setTypeTouristPlaceName(pResultSet.getString(pIndex)); // index 2
        return pIndex;
    }
    
     // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Rol 
    private static void getData(PreparedStatement pPS, ArrayList<TypeTouristPlace> pTypeTouristPlaces) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Rol
                TypeTouristPlace typeTouristPlace = new TypeTouristPlace(); 
                asignDataResultSet(typeTouristPlace, resultSet, 0); // Llenar las propiedaddes de la Entidad Rol con los datos obtenidos de la fila en el ResultSet
                pTypeTouristPlaces.add(typeTouristPlace); // Agregar la entidad Rol al ArrayList de Rol
            }
            resultSet.close(); // Cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    // Metodo para obtener por Id un registro de la tabla de Rol 
    public static TypeTouristPlace getById(TypeTouristPlace pTypeTouristPlace) throws Exception {
        TypeTouristPlace typeTouristPlace = new TypeTouristPlace();
        ArrayList<TypeTouristPlace> typeTouristPlaces = new ArrayList();
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = getSelect(pTypeTouristPlace); // Obtener la consulta SELECT de la tabla Rol
            sql += " WHERE p.IdTypeTouristPlace=?"; // Concatenar a la consulta SELECT de la tabla Rol el WHERE 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pTypeTouristPlace.getIdTypeTouristPlace()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                getData(ps, typeTouristPlaces); // Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (typeTouristPlaces.size() > 0) { // Verificar si el ArrayList de Rol trae mas de un registro en tal caso solo debe de traer uno
            typeTouristPlace = typeTouristPlaces.get(0); // Si el ArrayList de Rol trae un registro o mas obtenemos solo el primero 
        }
        return typeTouristPlace; // Devolver el rol encontrado por Id 
    }
    
     // Metodo para obtener todos los registro de la tabla de Rol
    public static ArrayList<TypeTouristPlace> getAll() throws Exception {
        ArrayList<TypeTouristPlace> typeTouristPlaces;
        typeTouristPlaces = new ArrayList<>();
        try (Connection conn = ComunDB.getConexion();) {// Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = getSelect(new TypeTouristPlace());  // Obtener la consulta SELECT de la tabla Rol
            sql += addOrderBy(new TypeTouristPlace());  // Concatenar a la consulta SELECT de la tabla Rol el ORDER BY por Id 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                getData(ps, typeTouristPlaces ); // Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return typeTouristPlaces; // Devolver el ArrayList de Rol
    }
    
    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Rol de forma dinamica
    static void querySelect(TypeTouristPlace pTypeTouristPlace, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // Obtener el PreparedStatement al cual aplicar los parametros
        if (pTypeTouristPlace.getIdTypeTouristPlace() > 0) { 
            pUtilQuery.AgregarWhereAnd(" p.IdTypeTouristPlace=? "); // Agregar el campo Id al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                
                statement.setInt(pUtilQuery.getNumWhere(), pTypeTouristPlace.getIdTypeTouristPlace()); 
            }
        }
        
        if (pTypeTouristPlace.getTypeTouristPlaceName() != null && pTypeTouristPlace.getTypeTouristPlaceName().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.TypeTouristPlaceName LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                
                statement.setString(pUtilQuery.getNumWhere(), "%" + pTypeTouristPlace.getTypeTouristPlaceName() + "%"); 
            }
        }
    }

     
    public static ArrayList<TypeTouristPlace> search(TypeTouristPlace pTypeTouristPlace) throws Exception {
        ArrayList<TypeTouristPlace> typeTouristPlaces = new ArrayList();
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = getSelect(pTypeTouristPlace); 
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(pTypeTouristPlace, utilQuery);  
            sql = utilQuery.getSQL(); 
            sql += addOrderBy(pTypeTouristPlace); 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pTypeTouristPlace, utilQuery);  
                getData(ps, typeTouristPlaces); 
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return typeTouristPlaces; 
    }
}
