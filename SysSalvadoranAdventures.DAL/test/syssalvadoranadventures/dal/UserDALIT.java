/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package syssalvadoranadventures.dal;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import syssalvadoranadventures.el.User;
import syssalvadoranadventures.el.Rol;
import java.sql.*;

/**
 *
 * @author Abdias_Hernandez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDALIT {
    private static User userActual;
    private static String login;
    
    public UserDALIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class UserDAL.
     */
    @Test
    public void test1Create() throws Exception {
        System.out.println("create");
        
        login = "sysAdmin";
        User usuario = new User();
        usuario.setName("ADMINISTRADOR");
        usuario.setLastName("SISTEMA");
        usuario.setTelephone("12345678");
        usuario.setLogin(login);
        usuario.setPassword("admin");
        Rol rolB = new Rol();
        rolB.setTop_aux(1);
        usuario.setIdRol(RolDAL.search(rolB).get(0).getIdRol());
        int expResult = 0;
        int result = UserDAL.create(usuario);
        assertNotEquals(expResult, result);
    }
    
    public int testIndividualQuerySelect(User pUsuario) throws Exception {
        ComunDB comundb = new ComunDB();
        ComunDB.UtilQuery pUtilQuery = comundb.new UtilQuery("", null, 0);
        UserDAL.querySelect(pUsuario, pUtilQuery);
        return pUtilQuery.getNumWhere();
    }
    
    /**
     * Test of querySelect method, of class UserDAL.
     */
    @Test
    public void test2QuerySelect() throws Exception {
        System.out.println("querySelect");
        int index = 0;
        User usuario = new User();
        usuario.setIdUser(1);
        index++;
        assertTrue(testIndividualQuerySelect(usuario) == index);
        usuario.setName("TEST");
        index++;
        assertTrue(testIndividualQuerySelect(usuario) == index);
        usuario.setLastName("TEST");
        index++;
        assertTrue(testIndividualQuerySelect(usuario) == index);
        usuario.setTelephone("TEST");
        index++;
        assertTrue(testIndividualQuerySelect(usuario) == index);
        usuario.setLogin("TEST");
        index++;
        assertTrue(testIndividualQuerySelect(usuario) == index);
        usuario.setIdRol(1);
        index++;
        assertTrue(testIndividualQuerySelect(usuario) == index);
    }
    
    /**
     * Test of search method, of class UserDAL.
     * @throws java.lang.Exception 
     */
    @Test
    public void test3Search() throws Exception {
        System.out.println("search");
        User usuario = new User();
        usuario.setName("Nombre UNIT TEST");
        usuario.setLastName("Apellido UNIT TEST");
        usuario.setTelephone("Tel TEST");
        usuario.setLogin(login);
        usuario.setTop_aux(10);
        ArrayList<User> result = UserDAL.search(usuario);
        assertTrue(result.size() > 0);
        userActual = result.get(0);
    }
    
    /**
     * Test of getById method, of class UserDAL
     * @throws java.lang.Exception 
     */
    @Test
    public void test4GetById() throws Exception {
        System.out.println("getById");
        User result = UserDAL.getById(userActual);
        assertEquals(userActual.getIdUser(), result.getIdUser());
    }

    /**
     * Test of update method, of class UserDAL.
     * @throws java.lang.Exception 
     */
    @Test
    public void test5Update() throws Exception {
        System.out.println("update");
         User usuario = new User();
        usuario.setIdUser(userActual.getIdUser());
        usuario.setName("Nombre UNIT TEST M");
        usuario.setLastName("Apellido UNIT TEST M");
        usuario.setTelephone("TelTESTM");
        login+="_MOD";
        usuario.setLogin(login);
        Rol rolB = new Rol();
        rolB.setTop_aux(2);
        usuario.setIdRol(RolDAL.search(rolB).get(1).getIdRol());
        int expResult = 0;
        int result = UserDAL.update(usuario);
        assertNotEquals(expResult, result);
        User usuarioUpdate = UserDAL.getById(userActual);
        assertTrue(usuarioUpdate.getLogin().equals(usuario.getLogin()));
    }
    
    /**
     * Test of getAll method, of class UserDAL.
     */
    @Test
    public void test6GetAll() throws Exception {
        System.out.println("getAll");
        ArrayList<User> result = UserDAL.getAll();
        assertTrue(result.size() > 0);
    }
    
    /**
     * Test of searchIncludeRol method, of class UserDAL.
     */
    @Test
    public void test7SearchIncludeRol() throws Exception {
        System.out.println("searchIncludeRol");
        User usuario = new User();
        usuario.setTop_aux(10);
        ArrayList<User> result = UserDAL.searchIncludeRol(usuario);
        assertTrue(result.size() > 0);
        User usuarioConRol = result.get(0);
        assertTrue(usuarioConRol.getIdRol() == usuarioConRol.getRol().getIdRol());
    }
    
    
    /**
     * Test of getCampos method, of class UserDAL.
     */
    @Test
    public void test8GetCampos() {
        System.out.println("getCampos");
        String expResult = "";
        String result = UserDAL.getCampos();
        assertNotEquals(expResult, result);
    }
    
    /**
     * Test of changePassword method, of class UserDAL.
     */
    @Test
    public void test90ChangePassword() throws Exception {
        System.out.println("changePassword");
        User usuario = new User();
        usuario.setIdUser(userActual.getIdUser());
        usuario.setLogin(login);
        usuario.setPassword("UNODOSTRES");
        String pPasswordAnt = "12345";
        int expResult = 0;
        int result = UserDAL.changePassword(usuario, pPasswordAnt);
        assertNotEquals(expResult, result);
    }
    
    /**
     * Test of login method, of class UserDAL.
     */
    @Test
    public void test91Login() throws Exception {
        System.out.println("login");
        User usuario = new User();
        usuario.setLogin(login);
        usuario.setPassword("UNODOSTRES");
        User result = UserDAL.login(usuario);
        assertTrue(result.getIdUser() > 0);
        assertEquals(usuario.getLogin(), result.getLogin());
    }
    
        /**
     * Test of asignDataResultSet method, of class UserDAL.
     */
    @Test
    public void test92AignDataResultSet() throws Exception {
        System.out.println("asignDataResultSet");
        User usuario = new User();
        try (Connection conn = ComunDB.getConexion();) {
            String sql = "SELECT " + UserDAL.getCampos()+ " FROM Usuario u";
            sql += " WHERE u.IdUser=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, userActual.getIdUser());
                try (ResultSet resultSet = ComunDB.obtenerResultSet(ps);) {
                    while (resultSet.next()) {
                        UserDAL.asignDataResultSet(usuario, resultSet, 0);
                    }
                    resultSet.close();
                } catch (SQLException ex) {
                    throw ex;
                }
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } // Handle any errors that may have occurred.
        catch (SQLException ex) {
            throw ex;
        }
        assertTrue(usuario.getIdUser() == userActual.getIdUser());
    }

    /**
     * Test of delete method, of class UserDAL.
     */
    @Test
    public void test93Delete() throws Exception {
        System.out.println("delete");
        int expResult = 0;
        int result = UserDAL.delete(userActual);
        assertNotEquals(expResult, result);
        User usuarioDelete = UserDAL.getById(userActual);
        assertTrue(usuarioDelete.getIdUser() == 0);
    }
}
