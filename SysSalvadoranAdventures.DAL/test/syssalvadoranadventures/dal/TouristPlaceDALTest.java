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
import syssalvadoranadventures.el.TouristPlace;

/**
 *
 * @author Silvia Juárez
 */
public class TouristPlaceDALTest {
    
    public TouristPlaceDALTest() {
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
     * Test of getCampos method, of class TouristPlaceDAL.
     */
    @Test
    public void testGetCampos() {
        System.out.println("getCampos");
        String expResult = "";
        String result = TouristPlaceDAL.getCampos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class TouristPlaceDAL.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        TouristPlace pTouristPlace = null;
        int expResult = 0;
        int result = TouristPlaceDAL.create(pTouristPlace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class TouristPlaceDAL.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        TouristPlace pTouristPlace = null;
        int expResult = 0;
        int result = TouristPlaceDAL.update(pTouristPlace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class TouristPlaceDAL.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        TouristPlace pTouristPlace = null;
        int expResult = 0;
        int result = TouristPlaceDAL.delete(pTouristPlace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of asignDataResultSet method, of class TouristPlaceDAL.
     */
    @Test
    public void testAsignDataResultSet() throws Exception {
        System.out.println("asignDataResultSet");
        TouristPlace pTouristPlace = null;
        ResultSet pResultSet = null;
        int pIndex = 0;
        int expResult = 0;
        int result = TouristPlaceDAL.asignDataResultSet(pTouristPlace, pResultSet, pIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getById method, of class TouristPlaceDAL.
     */
    @Test
    public void testGetById() throws Exception {
        System.out.println("getById");
        TouristPlace pTouristPlace = null;
        TouristPlace expResult = null;
        TouristPlace result = TouristPlaceDAL.getById(pTouristPlace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class TouristPlaceDAL.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        ArrayList<TouristPlace> expResult = null;
        ArrayList<TouristPlace> result = TouristPlaceDAL.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of querySelect method, of class TouristPlaceDAL.
     */
    @Test
    public void testQuerySelect() throws Exception {
        System.out.println("querySelect");
        TouristPlace pTouristPlace = null;
        ComunDB.UtilQuery pUtilQuery = null;
        TouristPlaceDAL.querySelect(pTouristPlace, pUtilQuery);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of search method, of class TouristPlaceDAL.
     */
    @Test
    public void testSearch() throws Exception {
        System.out.println("search");
        TouristPlace pTouristPlace = null;
        ArrayList<TouristPlace> expResult = null;
        ArrayList<TouristPlace> result = TouristPlaceDAL.search(pTouristPlace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchIncludeTypeTouristPlace method, of class TouristPlaceDAL.
     */
    @Test
    public void testSearchIncludeTypeTouristPlace() throws Exception {
        System.out.println("searchIncludeTypeTouristPlace");
        TouristPlace pTouristPlace = null;
        ArrayList<TouristPlace> expResult = null;
        ArrayList<TouristPlace> result = TouristPlaceDAL.searchIncludeTypeTouristPlace(pTouristPlace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchIncludeMunicipality method, of class TouristPlaceDAL.
     */
    @Test
    public void testSearchIncludeMunicipality() throws Exception {
        System.out.println("searchIncludeMunicipality");
        TouristPlace pTouristPlace = null;
        ArrayList<TouristPlace> expResult = null;
        ArrayList<TouristPlace> result = TouristPlaceDAL.searchIncludeMunicipality(pTouristPlace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchIncludeUser method, of class TouristPlaceDAL.
     */
    @Test
    public void testSearchIncludeUser() throws Exception {
        System.out.println("searchIncludeUser");
        TouristPlace pTouristPlace = null;
        ArrayList<TouristPlace> expResult = null;
        ArrayList<TouristPlace> result = TouristPlaceDAL.searchIncludeUser(pTouristPlace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
