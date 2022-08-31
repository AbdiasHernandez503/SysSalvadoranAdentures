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
import syssalvadoranadventures.el.Rol;

/**
 *
 * @author Abdias_Hernandez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RolDALIT {
    private static Rol rolActual;
    
    public RolDALIT() {
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
     * Test of create method, of class RolDAL.
     */
    @Test
    public void test1Create() throws Exception {
        System.out.println("create");
        Rol pRol = new Rol(0, "TEST UNIT ROL");
        int expResult = 0;
        int result = RolDAL.create(pRol);
        assertNotEquals(expResult, result);
    }
    
    public int testIndividualQuerySelect(Rol pRol) throws Exception {
        ComunDB comundb = new ComunDB();
        ComunDB.UtilQuery pUtilQuery = comundb.new UtilQuery("",null, 0);
        RolDAL.querySelect(pRol, pUtilQuery);
        return pUtilQuery.getNumWhere();
    }
    
    /**
     * Test of querySelect method, of class RolDAL.
     */
    @Test
    public void test2QuerySelect() throws Exception {
        System.out.println("querySelect");
         Rol pRol = new Rol();
        pRol.setIdRol(1);
        assertTrue(testIndividualQuerySelect(pRol) == 1);
        pRol.setName("TEST");
        assertTrue(testIndividualQuerySelect(pRol) == 2);
    }

    /**
     * Test of search method, of class RolDAL.
     */
    @Test
    public void test3Search() throws Exception {
        System.out.println("search");
        Rol pRol = new Rol(0, "TEST UNIT ROL");
        ArrayList<Rol> result = RolDAL.search(pRol);
        assertTrue(result.size() > 0);
        rolActual = result.get(0);
    }
    
    /**
     * Test of getById method, of class RolDAL.
     */
    @Test
    public void test4GetById() throws Exception {
        System.out.println("getById");
        Rol result = RolDAL.getById(rolActual);
        assertEquals(rolActual.getIdRol(), result.getIdRol());
    }

    /**
     * Test of update method, of class RolDAL.
     */
    @Test
    public void test5Update() throws Exception {
        System.out.println("update");
        Rol pRol = new Rol();
        pRol.setIdRol(rolActual.getIdRol());
        pRol.setName("TEST UNIT ROL M");
        int expResult = 0;
        int result = RolDAL.update(pRol);
        assertNotEquals(expResult, result);
        Rol rolUpdate = RolDAL.getById(rolActual);
        assertTrue(rolUpdate.getName().equals(pRol.getName()));
    }
    
     /**
     * Test of getAll method, of class RolDAL.
     */
    @Test
    public void test6GetAll() throws Exception {
        System.out.println("getAll");
         ArrayList<Rol> result = RolDAL.getAll();
        assertTrue(result.size() > 0);
    }

    /**
     * Test of delete method, of class RolDAL.
     */
    @Test
    public void test7Delete() throws Exception {
        System.out.println("delete");
        int expResult = 0;
        int result = RolDAL.delete(rolActual);
        assertNotEquals(expResult, result);
        Rol rolDelete = RolDAL.getById(rolActual);
        assertTrue(rolDelete.getIdRol() == 0);
    }
}
