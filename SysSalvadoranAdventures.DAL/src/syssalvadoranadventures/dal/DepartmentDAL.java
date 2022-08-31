/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syssalvadoranadventures.dal;
import java.sql.*;
import java.util.*;
import syssalvadoranadventures.el.*;

/**
 *
 * @author Abdias_Hernandez
 */
public class DepartmentDAL {
    // Metodo para obtener los campos a utilizar en la consulta SELECT de la tabla de Departamento
    static String getCampos(){
        return "d.IdDepartment, d.DepartmentName";
    }
    
    private static String getSelect(Department pDepartment) {
        String sql;
        sql = "SELECT ";
        if (pDepartment.getTop_aux() > 0 && ComunDB.typeDB == ComunDB.TypeDB.SQLSERVER) {
            // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
            sql += "TOP " + pDepartment.getTop_aux() + " ";
        }
        sql += (getCampos() + " FROM Department d"); // Agregar los campos de la tabla de Departamento mas el FROM a la tabla Departamento con su alias "d"
        return sql;
    }
    
    private static String addOrderBy(Department pDepartment) {
        String sql = " ORDER BY d.IdDepartment DESC";
        if (pDepartment.getTop_aux() > 0 && ComunDB.typeDB == ComunDB.TypeDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de Departamento en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + pDepartment.getTop_aux() + " ";
        }
        return sql;
    }
    
    //Metodo para insertar un nuevo registro en la tabla Department
    public static int create(Department pDepartment) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.getConexion();) { 
            sql = "INSERT INTO Department(DepartmentName) VALUES(?)"; 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                ps.setString(1, pDepartment.getDepartmentName()); 
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
    
    // Metodo para poder actualizar un registro en la tabla de Departamento
    public static int update(Department pDepartment) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "UPDATE Department SET DepartmentName=? WHERE IdDepartment=?"; // Definir la consulta UPDATE a la tabla de Departamento utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, pDepartment.getDepartmentName()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setInt(2, pDepartment.getIdDepartment()); // Agregar el parametro a la consulta donde estan el simbolo ? #2  
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
    
     // Metodo para poder eliminar un registro en la tabla de Departamento
    public static int delete(Department pDepartment) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.getConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "DELETE FROM Department WHERE IdDepartment=?";  // Definir la consulta DELETE a la tabla de Departmento utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pDepartment.getIdDepartment()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
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
    
    // Metodo para llenar las propiedades de la entidad de Departamento con los datos que viene en el ResultSet,
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignDataResultSet(Department pDepartment, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT r.Id(indice 1),r.Nombre(indice 2) * FROM Departamento
        pIndex++;
        pDepartment.setIdDepartment(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pDepartment.setDepartmentName(pResultSet.getString(pIndex)); // index 2
        return pIndex;
    }
    
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Departamento
    private static void getData(PreparedStatement pPS, ArrayList<Department> pDepartments) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Departamento
                Department department = new Department(); 
                asignDataResultSet(department, resultSet, 0); // Llenar las propiedaddes de la Entidad Departamento con los datos obtenidos de la fila en el ResultSet
                pDepartments.add(department); // Agregar la entidad Departamento al ArrayList de LugarTuristico
            }
            resultSet.close(); // Cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    // Metodo para obtener por Id un registro de la tabla de Departamento 
    public static Department getById(Department pDepartment) throws Exception {
        Department department = new Department();
        ArrayList<Department> departments = new ArrayList();
        try (Connection conn = ComunDB.getConexion();) { 
            String sql = getSelect(pDepartment); 
            sql += " WHERE d.IdDepartment=?";  
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                ps.setInt(1, pDepartment.getIdDepartment()); 
                getData(ps, departments); 
                ps.close(); 
            } catch (SQLException ex) {
                throw ex;  
            }
            conn.close(); 
        }
        catch (SQLException ex) {
            throw ex; 
        }
        if (departments.size() > 0) { 
            department = departments.get(0);
        }
        return department;  
    }
    
    // Metodo para obtener todos los registro de la tabla de Departamento
    public static ArrayList<Department> getAll() throws Exception {
        ArrayList<Department> departments;
        departments = new ArrayList<>();
        try (Connection conn = ComunDB.getConexion();) {
            String sql = getSelect(new Department());  
            sql += addOrderBy(new Department());  
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { 
                getData(ps, departments); 
                ps.close(); 
            } catch (SQLException ex) {
                throw ex; 
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return departments; // Devolver el ArrayList de Departamento
    }
    
     // Metodo para asignar los filtros de la consulta SELECT de la tabla de Departamento de forma dinamica
    static void querySelect(Department pDepartment, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // Obtener el PreparedStatement al cual aplicar los parametros
        if (pDepartment.getIdDepartment() > 0) { // Verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Departamento
            pUtilQuery.AgregarWhereAnd(" d.IdDepartment=? "); // Agregar el campo Id al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                // Agregar el parametro del campo Id a la consulta SELECT de la tabla de Departamento
                statement.setInt(pUtilQuery.getNumWhere(), pDepartment.getIdDepartment()); 
            }
        }
        // Verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Departamento
        if (pDepartment.getDepartmentName() != null && pDepartment.getDepartmentName().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" d.DepartmentName LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Departamento
                statement.setString(pUtilQuery.getNumWhere(), "%" + pDepartment.getDepartmentName() + "%"); 
            }
        }
    }
    
    // Metodo para obtener todos los registro de la tabla de Departamento que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Departamento 
    public static ArrayList<Department> search(Department pDepartment) throws Exception {
        ArrayList<Department> departments = new ArrayList();
        try (Connection conn = ComunDB.getConexion();) { 
            String sql = getSelect(pDepartment); 
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(pDepartment, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Departamento            sql = utilQuery.getSQL(); 
            sql = utilQuery.getSQL(); 
            sql += addOrderBy(pDepartment); // Concatenar a la consulta SELECT de la tabla Departamento el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pDepartment, utilQuery);  // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Departamento
                getData(ps, departments); // Llenar el ArrayList de Departamento con las fila que devolvera la consulta SELECT a la tabla de Departamento
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return departments; // Devolver el ArrayList de Departamento
    }
}
