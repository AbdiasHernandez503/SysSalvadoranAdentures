/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syssalvadoranadventures.dal;

import java.util.*;
import java.sql.*;
import syssalvadoranadventures.el.*;


public class MunicipalityDAL {
    static String getCampos(){
        return "m.IdMunicipality, m.IdDepartment, m.MunicipalityName";
    }
    
    // Metodo para obtener el SELECT a la tabla Municipality y el TOP en el caso que se utilice una base de datos SQL SERVER
    private static String getSelect(Municipality pMunicipality) {
        String sql;
        sql = "SELECT ";
        if (pMunicipality.getTop_aux() > 0 && ComunDB.typeDB == ComunDB.TypeDB.SQLSERVER) {
            // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
            sql += "TOP " + pMunicipality.getTop_aux() + " ";
        }
        sql += (getCampos() + " FROM Municipality m"); // Agregar los campos de la tabla de Municipality mas el FROM a la tabla Municipality con su alias "m"
        return sql;
    }
    
    private static String addOrderBy(Municipality pMunicipality) {
        String sql = " ORDER BY m.IdMunicipality DESC";
        if (pMunicipality.getTop_aux() > 0 && ComunDB.typeDB == ComunDB.TypeDB.MYSQL) {
    
            sql += " LIMIT " + pMunicipality.getTop_aux() + " ";
        }
        return sql;
    }
    
    
    public static int create(Municipality pMunicipality) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "INSERT INTO Municipality(IdDepartment,MunicipalityName) VALUES(?,?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                  ps.setInt(1, pMunicipality.getIdDepartment()); // Agregar el parametro a la consulta donde estan el simbolo "?" #1  
                  ps.setString(2, pMunicipality.getMunicipalityName()); // Agregar el parametro a la consulta donde estan el simbolo "?" #2  
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

    
    public static int update(Municipality pMunicipality) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "UPDATE Municipality SET  IdDepartment=?,MunicipalityName=? WHERE IdMunicipality=?"; 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pMunicipality.getIdDepartment()); // agregar el parametro a la consulta donde estan el simbolo ? #1 
                ps.setString(2, pMunicipality.getMunicipalityName()); // Agregar el parametro a la consulta donde estan el simbolo ? #2  
                ps.setInt(3, pMunicipality.getIdMunicipality()); // Agregar el parametro a la consulta donde estan el simbolo ? #3  
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

    
    public static int delete(Municipality pMunicipality) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "DELETE FROM Municipality WHERE IdMunicipality=?";  
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pMunicipality.getIdMunicipality()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
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
    
    
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignDataResultSet(Municipality pMunicipality, ResultSet pResultSet, int pIndex) throws Exception {
        
        pIndex++;
        pMunicipality.setIdMunicipality(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pMunicipality.setIdDepartment(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pMunicipality.setMunicipalityName(pResultSet.getString(pIndex)); // index 3
        return pIndex;
    }
    
    
    private static void getData(PreparedStatement pPS, ArrayList<Municipality> pMunicipalities) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { 
                Municipality municipality = new Municipality(); 
                asignDataResultSet(municipality, resultSet, 0); 
                pMunicipalities.add(municipality); 
            }
            resultSet.close(); // Cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Municipio y JOIN a la tabla de Departamento
    private static void getDataIncludeDepartment(PreparedStatement pPS, ArrayList<Municipality> pMunicipalities) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            HashMap<Integer, Department> departmentMap = new HashMap(); //crear un HashMap para automatizar la creacion de instancias de la clase Departamento
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Municipio JOIN a la tabla de Departamento
                Municipality municipality = new Municipality();
                 // Llenar las propiedaddes de la Entidad Municipio con los datos obtenidos de la fila en el ResultSet
                int index = asignDataResultSet(municipality, resultSet, 0);
                if (departmentMap.containsKey(municipality.getIdDepartment()) == false) { // verificar que el HashMap aun no contenga el Departamento actual
                    Department department = new Department();
                    // en el caso que el Departamento no este en el HashMap se asignara
                    DepartmentDAL.asignDataResultSet(department, resultSet, index);
                    departmentMap.put(department.getIdDepartment(), department); // agregar el Departamento al  HashMap
                    municipality.setDepartment(department); // agregar el Departamento al Municipio
                } else {
                    // En el caso que el Departamento existe en el HashMap se agregara automaticamente al Municipio
                    municipality.setDepartment(departmentMap.get(municipality.getIdDepartment())); 
                }
                pMunicipalities.add(municipality); // Agregar el Municipio de la fila actual al ArrayList de Municipio
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }

   
    public static Municipality getById(Municipality pMunicipality) throws Exception {
        Municipality municipality = new Municipality();
        ArrayList<Municipality> municipalities = new ArrayList();
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = getSelect(pMunicipality); // Obtener la consulta SELECT de la tabla Departamento
            sql += " WHERE m.IdMunicipality=?"; 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pMunicipality.getIdMunicipality()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                getData(ps, municipalities); // Llenar el ArrayList de Departamento con las fila que devolvera la consulta SELECT a la tabla de Departamento
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (municipalities.size() > 0) { // Verificar si el ArrayList de LugarTuristico trae mas de un registro en tal caso solo debe de traer uno
            municipality = municipalities.get(0); // Si el ArrayList de LugarTuristico trae un registro o mas obtenemos solo el primero 
        }
        return municipality; // Devolver el lugarturistico encontrado por Id 
    }

    
    public static ArrayList<Municipality> getAll() throws Exception {
        ArrayList<Municipality> municipality;
        municipality = new ArrayList<>();
        try (Connection conn = ComunDB.getConexion();) {// Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = getSelect(new Municipality()); 
            sql += addOrderBy(new Municipality());  
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                getData(ps, municipality);
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return municipality; 
    }
    
    
    static void querySelect(Municipality pMunicipality, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // Obtener el PreparedStatement al cual aplicar los parametros
        if (pMunicipality.getIdMunicipality() > 0) { 
            pUtilQuery.AgregarWhereAnd(" m.IdMunicipality=? "); // Agregar el campo Id al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                
                statement.setInt(pUtilQuery.getNumWhere(), pMunicipality.getIdMunicipality()); 
            }
        }
        // verificar si se va incluir el campo IdDepartamento en el filtro de la consulta SELECT de la tabla de Municipio
        if (pMunicipality.getIdDepartment() > 0) {
            pUtilQuery.AgregarWhereAnd(" m.IdDepartment=? "); 
            if (statement != null) {
                 
                statement.setInt(pUtilQuery.getNumWhere(), pMunicipality.getIdDepartment());
            }
        }
        
        if (pMunicipality.getMunicipalityName() != null && pMunicipality.getMunicipalityName().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" m.MunicipalityName LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                
                statement.setString(pUtilQuery.getNumWhere(), "%" + pMunicipality.getMunicipalityName() + "%"); 
            }
        }
    }

    
    
     // Metodo para obtener todos los registro de la tabla de Municipio que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Municipio 
    public static ArrayList<Municipality> search(Municipality pMunicipality) throws Exception {
        ArrayList<Municipality> municipalities = new ArrayList();
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = getSelect(pMunicipality); // obtener la consulta SELECT de la tabla Municipio
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pMunicipality, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Municipio
            sql = utilQuery.getSQL();
            sql += addOrderBy(pMunicipality); // Concatenar a la consulta SELECT de la tabla Municipio el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pMunicipality, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Municipio
                getData(ps, municipalities); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Municipio
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return municipalities; // Devolver el ArrayList de Municipio
    }

     // Metodo para obtener todos los registro de la tabla de Municipio que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Municipio 
    public static ArrayList<Municipality> searchIncludeDepartment(Municipality pMunicipality) throws Exception {
        ArrayList<Municipality> municipalities = new ArrayList();
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "SELECT "; // Iniciar la variables para el String de la consulta SELECT
            if (pMunicipality.getTop_aux() > 0 && ComunDB.typeDB == ComunDB.TypeDB.SQLSERVER) {
                sql += "TOP " + pMunicipality.getTop_aux() + " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
            }
            sql += getCampos(); // Obtener los campos de la tabla de Municipio que iran en el SELECT
            sql += ",";
            sql += DepartmentDAL.getCampos(); // Obtener los campos de la tabla de Departamento que iran en el SELECT
            sql += " FROM Municipality m";
            sql += " JOIN Department d on (m.IdDepartment=d.IdDepartment)"; // agregar el join para unir la tabla de Municipio con Departamento
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pMunicipality, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Municipio
            sql = utilQuery.getSQL();
            sql += addOrderBy(pMunicipality); // Concatenar a la consulta SELECT de la tabla Municipio el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pMunicipality, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Municipio
                getDataIncludeDepartment(ps, municipalities);// Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Municipio
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return municipalities; // Devolver el ArrayList de Municipio
    }
}
