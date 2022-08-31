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
public class TouristPlaceDAL {
    static String getCampos(){
        return "t.IdTouristPlace, t.TouristPlaceName, t.IdTypeTouristPlace, "
                + "t.IdMunicipality, t.Description, t.Activities, t.IdUser, t.Photos";
    }
    
     // Metodo para obtener el SELECT a la tabla LugarTuristico y el TOP en el caso que se utilice una base de datos SQL SERVER
    private static String getSelect(TouristPlace pTouristPlace) {
        String sql;
        sql = "SELECT ";
        if (pTouristPlace.getTop_aux() > 0 && ComunDB.typeDB == ComunDB.TypeDB.SQLSERVER) {
            // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
            sql += "TOP " + pTouristPlace.getTop_aux() + " ";
        }
        sql += (getCampos() + " FROM TouristPlace t"); // Agregar los campos de la tabla de LugarTuristico mas el FROM a la tabla LugarTuristico con su alias "r"
        return sql;
    }
    
     // Metodo para obtener Order by a la consulta SELECT de la tabla LugarTuristico y ordene los registros de mayor a menor 
    private static String addOrderBy(TouristPlace pTouristPlace) {
        String sql = " ORDER BY t.IdTouristPlace DESC";
        if (pTouristPlace.getTop_aux() > 0 && ComunDB.typeDB == ComunDB.TypeDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de LugarTuristico en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + pTouristPlace.getTop_aux() + " ";
        }
        return sql;
    }
    
    // Metodo para poder insertar un nuevo registro en la tabla de LugarTuristico
    public static int create(TouristPlace pTouristPlace) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "INSERT INTO  TouristPlace(TouristPlaceName,IdTypeTouristPlace,IdMunicipality, Description, Activities, IdUser, Photos) VALUES(?,?,?,?,?,?,?)"; // Definir la consulta INSERT a la tabla de LugarTuristico utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, pTouristPlace.getTouristPlaceName()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                ps.setInt(2, pTouristPlace.getIdTypeTouristPlace()); // Agregar el parametro a la consulta donde estan el simbolo "?" #2
                ps.setInt(3, pTouristPlace.getIdMunicipality()); // Agregar el parametro a la consulta donde estan el simbolo "?" #3
                ps.setString(4, pTouristPlace.getDescription());
                ps.setString(5, pTouristPlace.getActivities());
                ps.setInt(6, pTouristPlace.getIdUser()); // Agregar el parametro a la consulta donde estan el simbolo "?" #4  
                ps.setString(7, pTouristPlace.getPhotos());
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
    // Metodo para poder actualizar un registro en la tabla de LugarTuristico
    public static int update(TouristPlace pTouristPlace) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "UPDATE TouristPlace SET TouristPlaceName=?,IdTypeTouristPlace=?,IdMunicipality=?,Description=?,Activities=?,IdUser=?,Photos=? WHERE IdTouristPlace=?"; // Definir la consulta UPDATE a la tabla de LugarTuristico utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, pTouristPlace.getTouristPlaceName()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setInt(2, pTouristPlace.getIdTypeTouristPlace()); // agregar el parametro a la consulta donde estan el simbolo ? #2
                ps.setInt(3, pTouristPlace.getIdMunicipality()); // agregar el parametro a la consulta donde estan el simbolo ? #3 
                ps.setString(4, pTouristPlace.getDescription());
                ps.setString(5, pTouristPlace.getActivities());
                ps.setInt(6, pTouristPlace.getIdUser()); // agregar el parametro a la consulta donde estan el simbolo ? #4 
                ps.setString(7, pTouristPlace.getPhotos());
                ps.setInt(8, pTouristPlace.getIdTouristPlace()); // Agregar el parametro a la consulta donde estan el simbolo ? #5  
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
     // Metodo para poder eliminar un registro en la tabla de LugarTuristico
    public static int delete(TouristPlace pTouristPlace) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "DELETE FROM TouristPlace WHERE IdTouristPlace=?";  // Definir la consulta DELETE a la tabla de LugarTuristico utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pTouristPlace.getIdTouristPlace()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
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
    
     // Metodo para llenar las propiedades de la entidad de LugarTuristico con los datos que viene en el ResultSet,
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignDataResultSet(TouristPlace pTouristPlace, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT r.Id(indice 1),r.Nombre(indice 2),u.IdMunicipio(indice 3),u.IdTipoLugarTuristico(indice 4),u.IdUsuaio(indice 5), * FROM LugarTuristico
        pIndex++;
        pTouristPlace.setIdTouristPlace(pResultSet.getInt(pIndex)); // index 1
        
        pIndex++;
        pTouristPlace.setTouristPlaceName(pResultSet.getString(pIndex)); // index 2
        
        pIndex++;
        pTouristPlace.setIdTypeTouristPlace(pResultSet.getInt(pIndex)); // index 3
        
        pIndex++;
        pTouristPlace.setIdMunicipality(pResultSet.getInt(pIndex)); // index 4
        
        pIndex++;
        pTouristPlace.setDescription(pResultSet.getString(pIndex)); //Index 5
        
        pIndex++;
        pTouristPlace.setActivities(pResultSet.getString(pIndex));  //Index 6
        
        pIndex++;
        pTouristPlace.setIdUser(pResultSet.getInt(pIndex)); // index 7
        
        pIndex++;
        pTouristPlace.setPhotos(pResultSet.getString(pIndex));
        return pIndex;
    }
     // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de LugarTuristico
    private static void getData(PreparedStatement pPS, ArrayList<TouristPlace> pTouristPlaces) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla LugarTuristico
                TouristPlace touristPlace = new TouristPlace(); 
                asignDataResultSet(touristPlace, resultSet, 0); // Llenar las propiedaddes de la Entidad LugarTuristico con los datos obtenidos de la fila en el ResultSet
                pTouristPlaces.add(touristPlace); // Agregar la entidad LugarTuristico al ArrayList de LugarTuristico
            }
            resultSet.close(); // Cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de LugarTuristico y JOIN a la tabla de Municipio
    private static void getDataIncludeMunicipality(PreparedStatement pPS, ArrayList<TouristPlace> pTouristPlaces) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            HashMap<Integer, Municipality> municipalityMap = new HashMap(); //crear un HashMap para automatizar la creacion de instancias de la clase Municipio
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla LugarTuristico JOIN a la tabla de Municipio
                TouristPlace touristPlace = new TouristPlace();
                 // Llenar las propiedaddes de la Entidad LugarTuristico con los datos obtenidos de la fila en el ResultSet
                int index = asignDataResultSet(touristPlace, resultSet, 0);
                if (municipalityMap.containsKey(touristPlace.getIdMunicipality()) == false) { // verificar que el HashMap aun no contenga el Municipio actual
                    Municipality municipality = new Municipality();
                    // en el caso que el Municipio no este en el HashMap se asignara
                    MunicipalityDAL.asignDataResultSet(municipality, resultSet, index);
                    municipalityMap.put(municipality.getIdMunicipality(), municipality); // agregar el Municipio al  HashMap
                    touristPlace.setMunicipality(municipality); // agregar el Municipio al LugarTuristico
                } else {
                    // En el caso que el Municipio existe en el HashMap se agregara automaticamente al LugarTuristico
                    touristPlace.setMunicipality(municipalityMap.get(touristPlace.getIdMunicipality())); 
                }
                pTouristPlaces.add(touristPlace); // Agregar el LugarTuristio de la fila actual al ArrayList de LugarTuristico
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de LugarTuristico y JOIN a la tabla de TipoLugarTuristico
    private static void getDataIncludeTypeTouristPlace(PreparedStatement pPS, ArrayList<TouristPlace> pTouristPlaces) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            HashMap<Integer, TypeTouristPlace> typeTouristPlaceMap = new HashMap(); //crear un HashMap para automatizar la creacion de instancias de la clase TipoLugarTuristico
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla LugarTuristico JOIN a la tabla de TipoLugarTuristico
                TouristPlace touristPlace = new TouristPlace();
                 // Llenar las propiedaddes de la Entidad LugarTuristico con los datos obtenidos de la fila en el ResultSet
                int index = asignDataResultSet(touristPlace, resultSet, 0);
                if (typeTouristPlaceMap.containsKey(touristPlace.getIdTypeTouristPlace()) == false) { // verificar que el HashMap aun no contenga el TipoLugarTuristico actual
                    TypeTouristPlace typeTouristPlace = new TypeTouristPlace();
                    // en el caso que el TipoLugarTuristico no este en el HashMap se asignara
                    TypeTouristPlaceDAL.asignDataResultSet(typeTouristPlace, resultSet, index);
                    typeTouristPlaceMap.put(typeTouristPlace.getIdTypeTouristPlace(), typeTouristPlace); // agregar el TipoLugarTuristico al  HashMap
                    touristPlace.setTypeTouristPlace(typeTouristPlace); // agregar el TipoLugarTuristico al LugarTuristico
                } else {
                    // En el caso que el TipoLugarTuristico existe en el HashMap se agregara automaticamente al LugarTuristico
                    touristPlace.setTypeTouristPlace(typeTouristPlaceMap.get(touristPlace.getIdTypeTouristPlace())); 
                }
                pTouristPlaces.add(touristPlace); // Agregar el LugarTuristio de la fila actual al ArrayList de LugarTuristico
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de LugarTuristico y JOIN a la tabla de Usuario
    private static void obtenerDatosIncluirUsuario(PreparedStatement pPS, ArrayList<TouristPlace> pTouristPlaces) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            HashMap<Integer, User> usuarioMap = new HashMap(); //crear un HashMap para automatizar la creacion de instancias de la clase Usuario
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla LugarTuristico JOIN a la tabla de Usuario
                TouristPlace touristPlace = new TouristPlace();
                 // Llenar las propiedaddes de la Entidad LugarTuristico con los datos obtenidos de la fila en el ResultSet
                int index = asignDataResultSet(touristPlace, resultSet, 0);
                if (usuarioMap.containsKey(touristPlace.getIdUser()) == false) { // verificar que el HashMap aun no contenga el Usuario actual
                    User user = new User();
                    // en el caso que el Usuario no este en el HashMap se asignara
                    UserDAL.asignDataResultSet(user, resultSet, index);
                    usuarioMap.put(user.getIdUser(), user); // agregar el Usuario al  HashMap
                    touristPlace.setUser(user); // agregar el Usuario al LugarTuristico
                } else {
                    // En el caso que el Usuario existe en el HashMap se agregara automaticamente al LugarTuristico
                    touristPlace.setUser(usuarioMap.get(touristPlace.getIdUser())); 
                }
                pTouristPlaces.add(touristPlace); // Agregar el LugarTuristio de la fila actual al ArrayList de LugarTuristico
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    // Metodo para obtener por Id un registro de la tabla de LugarTuristico 
    public static TouristPlace getById(TouristPlace pTouristPlace) throws Exception {
        TouristPlace touristPlace = new TouristPlace();
        ArrayList<TouristPlace> touristPlaces = new ArrayList();
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = getSelect(pTouristPlace); // Obtener la consulta SELECT de la tabla LugarTuristico
            sql += " WHERE t.IdTouristPlace=?"; // Concatenar a la consulta SELECT de la tabla LugarTuristico el WHERE 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pTouristPlace.getIdTouristPlace()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                getData(ps, touristPlaces); // Llenar el ArrayList de LugarTuristico con las fila que devolvera la consulta SELECT a la tabla de LugarTuristico
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (touristPlaces.size() > 0) { // Verificar si el ArrayList de LugarTuristico trae mas de un registro en tal caso solo debe de traer uno
            touristPlace = touristPlaces.get(0); // Si el ArrayList de LugarTuristico trae un registro o mas obtenemos solo el primero 
        }
        return touristPlace; // Devolver el lugarturistico encontrado por Id 
    }
    
    // Metodo para obtener todos los registro de la tabla de LugarTuristico
    public static ArrayList<TouristPlace> getAll() throws Exception {
        ArrayList<TouristPlace> touristPlaces;
        touristPlaces = new ArrayList<>();
        try (Connection conn = ComunDB.getConexion();) {// Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = getSelect(new TouristPlace());  // Obtener la consulta SELECT de la tabla LugarTuristico
            sql += addOrderBy(new TouristPlace());  // Concatenar a la consulta SELECT de la tabla LugarTuristico el ORDER BY por Id 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                getData(ps, touristPlaces); // Llenar el ArrayList de LugarTuristico con las fila que devolvera la consulta SELECT a la tabla de LugarTuristico
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return touristPlaces; // Retornar el ArrayList de LugarTuristico
    }
     // Metodo para asignar los filtros de la consulta SELECT de la tabla de LugarTuristico de forma dinamica
    static void querySelect(TouristPlace pTouristPlace, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // Obtener el PreparedStatement al cual aplicar los parametros
        if (pTouristPlace.getIdTouristPlace() > 0) { // Verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de LugarTuristico
            pUtilQuery.AgregarWhereAnd(" t.IdTouristPlace=? "); // Agregar el campo Id al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                // Agregar el parametro del campo Id a la consulta SELECT de la tabla de LugarTuristico
                statement.setInt(pUtilQuery.getNumWhere(), pTouristPlace.getIdTouristPlace()); 
            }
        }
        // Verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de LugarTuristico
        if (pTouristPlace.getTouristPlaceName()!= null && pTouristPlace.getTouristPlaceName().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" t.TouristPlaceName LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de LugarTuristico
                statement.setString(pUtilQuery.getNumWhere(), "%" + pTouristPlace.getTouristPlaceName() + "%"); 
            }
        }
        
        // verificar si se va incluir el campo IdTipoLugarTuristico en el filtro de la consulta SELECT de la tabla de LugarTuristico
        if (pTouristPlace.getIdTypeTouristPlace() > 0) {
            pUtilQuery.AgregarWhereAnd(" t.IdTypeTouristPlace=? "); // agregar el campo IdMunicipio al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdMunicipio a la consulta SELECT de la tabla de LugarTuristico
                statement.setInt(pUtilQuery.getNumWhere(), pTouristPlace.getIdTypeTouristPlace());
            }
        }
        
        // verificar si se va incluir el campo IdMunicipio en el filtro de la consulta SELECT de la tabla de LugarTuristico
        if (pTouristPlace.getIdMunicipality()> 0) {
            pUtilQuery.AgregarWhereAnd(" t.IdMunicipality=? "); // agregar el campo IdTipoLugarTuristico al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdTipoLugarTuristico a la consulta SELECT de la tabla de LugarTuristico
                statement.setInt(pUtilQuery.getNumWhere(), pTouristPlace.getIdMunicipality());
            }
        }
        
        //Verificar si se va incluir el campo Descripcion en el filtro de la consulta SELECT de la tabla de LugarTuristico
        if (pTouristPlace.getDescription()!= null && pTouristPlace.getDescription().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" t.Description LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de LugarTuristico
                statement.setString(pUtilQuery.getNumWhere(), "%" + pTouristPlace.getDescription() + "%"); 
            }
        }
        
        //Verificar si se va incluir el campo Actividades en el filtro de la consulta SELECT de la tabla de LugarTuristico
        if (pTouristPlace.getActivities()!= null && pTouristPlace.getActivities().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" t.Activities LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de LugarTuristico
                statement.setString(pUtilQuery.getNumWhere(), "%" + pTouristPlace.getActivities() + "%"); 
            }
        }
        
        // verificar si se va incluir el campo IdUsuario en el filtro de la consulta SELECT de la tabla de LugarTuristico
        if (pTouristPlace.getIdUser() > 0) {
            pUtilQuery.AgregarWhereAnd(" t.IdUser=? "); // agregar el campo IdUsuario al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdUsuario a la consulta SELECT de la tabla de LugarTuristico
                statement.setInt(pUtilQuery.getNumWhere(), pTouristPlace.getIdUser());
            }
        }
        
        if (pTouristPlace.getPhotos()!=null && pTouristPlace.getPhotos().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" t.Photos LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pTouristPlace.getPhotos()+ "%"); 
            }
        }
    }
   // Metodo para obtener todos los registro de la tabla de LugarTuristico que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de LugarTuristico 
    public static ArrayList<TouristPlace> search(TouristPlace pTouristPlace) throws Exception {
        ArrayList<TouristPlace> touristPlaces = new ArrayList();
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = getSelect(pTouristPlace); // Obtener la consulta SELECT de la tabla LugarTuristico
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(pTouristPlace, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de LugarTuristico            sql = utilQuery.getSQL(); 
            sql += addOrderBy(pTouristPlace); // Concatenar a la consulta SELECT de la tabla LugarTuristico el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pTouristPlace, utilQuery);  // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de LugarTuristico
                getData(ps, touristPlaces); // Llenar el ArrayList de Departamento con las fila que devolvera la consulta SELECT a la tabla de LugarTuristico
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return touristPlaces; // Devolver el ArrayList de LugarTuristico
    }

    // Metodo para obtener todos los registro de la tabla de LugarTuristico que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de LugarTuristico 
    public static ArrayList<TouristPlace> searchIncludeTypeTouristPlace(TouristPlace pTouristPlace) throws Exception {
        ArrayList<TouristPlace> lugaresTuristicos = new ArrayList();
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "SELECT "; // Iniciar la variables para el String de la consulta SELECT
            if (pTouristPlace.getTop_aux() > 0 && ComunDB.typeDB == ComunDB.TypeDB.SQLSERVER) {
                sql += "TOP " + pTouristPlace.getTop_aux() + " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
            }
            sql += getCampos(); // Obtener los campos de la tabla de LugarTuristico que iran en el SELECT
            sql += ",";
            sql += TypeTouristPlaceDAL.getCampos(); // Obtener los campos de la tabla de TipoLugarTuristico que iran en el SELECT
            sql += " FROM TouristPlace t";
            sql += " JOIN TypeTouristPlace p on (t.IdTypeTouristPlace=p.IdTypeTouristPlace)"; // agregar el join para unir la tabla de LugarTuristico con TipoLugarTuristico
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pTouristPlace, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de LugarTuristico
            sql = utilQuery.getSQL();
            sql += addOrderBy(pTouristPlace); // Concatenar a la consulta SELECT de la tabla LugarTuristico el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pTouristPlace, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de LugarTuristico
                getDataIncludeTypeTouristPlace(ps, lugaresTuristicos);// Llenar el ArrayList de LugarTuristico con las fila que devolvera la consulta SELECT a la tabla de LugarTuristico
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return lugaresTuristicos; // Devolver el ArrayList de lLugarTuristico
    }
    
        
     // Metodo para obtener todos los registro de la tabla de LugarTuristico que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de LugarTuristico 
    public static ArrayList<TouristPlace> searchIncludeMunicipality(TouristPlace pTouristPlace) throws Exception {
        ArrayList<TouristPlace> lugaresTuristicos = new ArrayList();
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "SELECT "; // Iniciar la variables para el String de la consulta SELECT
            if (pTouristPlace.getTop_aux() > 0 && ComunDB.typeDB == ComunDB.TypeDB.SQLSERVER) {
                sql += "TOP " + pTouristPlace.getTop_aux() + " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
            }
            sql += getCampos(); // Obtener los campos de la tabla de LugarTuristico que iran en el SELECT
            sql += ",";
            sql += MunicipalityDAL.getCampos(); // Obtener los campos de la tabla de Municipio que iran en el SELECT
            sql += " FROM TouristPlace t";
            sql += " JOIN Municipality m on (t.IdMunicipality=m.IdMunicipality)"; // agregar el join para unir la tabla de LugarTuristico con Municipio
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pTouristPlace, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de LugarTuristico
            sql = utilQuery.getSQL();
            sql += addOrderBy(pTouristPlace); // Concatenar a la consulta SELECT de la tabla LugarTuristico el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pTouristPlace, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de LugarTuristico
                getDataIncludeMunicipality(ps, lugaresTuristicos);// Llenar el ArrayList de LugarTuristico con las fila que devolvera la consulta SELECT a la tabla de LugarTuristico
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return lugaresTuristicos; // Devolver el ArrayList de LugarTuristico
    }
    
    
    // Metodo para obtener todos los registro de la tabla de LugarTuristico que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de LugarTuristico 
    public static ArrayList<TouristPlace> searchIncludeUser(TouristPlace pTouristPlace) throws Exception {
        ArrayList<TouristPlace> lugaresTuristicos = new ArrayList();
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "SELECT "; // Iniciar la variables para el String de la consulta SELECT
            if (pTouristPlace.getTop_aux() > 0 && ComunDB.typeDB == ComunDB.TypeDB.SQLSERVER) {
                sql += "TOP " + pTouristPlace.getTop_aux() + " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
            }
            sql += getCampos(); // Obtener los campos de la tabla de LugarTuristico que iran en el SELECT
            sql += ",";
            sql += UserDAL.getCampos(); // Obtener los campos de la tabla de Usuario que iran en el SELECT
            sql += " FROM TouristPlace t";
            sql += " JOIN Usuario u on (t.IdUser=u.IdUser)"; // agregar el join para unir la tabla de LugarTuristico con Usuario (error solucionado!!)
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pTouristPlace, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de LugarTuristico
            sql = utilQuery.getSQL();
            sql += addOrderBy(pTouristPlace); // Concatenar a la consulta SELECT de la tabla LugarTuristico el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pTouristPlace, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de LugarTuristico
                obtenerDatosIncluirUsuario(ps, lugaresTuristicos);// Llenar el ArrayList de LugarTuristico con las fila que devolvera la consulta SELECT a la tabla de LugarTuristico
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return lugaresTuristicos; // Devolver el ArrayList de LugarTuristico
    }
}
