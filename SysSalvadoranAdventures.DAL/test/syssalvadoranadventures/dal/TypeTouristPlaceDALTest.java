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
import syssalvadoranadventures.el.TypeTouristPlace;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Silvia Juárez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TypeTouristPlaceDALTest {

    private static TypeTouristPlace typeTouristPlaceActual;

    public TypeTouristPlaceDALTest() {
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
     * Test of create method, of class TypeTouristPlaceDAL.
     */
    @Test
    public void test1Create() throws Exception {
        System.out.println("create");
        TypeTouristPlace pTypeTouristPlace = new TypeTouristPlace(0, "TEST UNIT Dep");
        int expResult = 0;
        int result = TypeTouristPlaceDAL.create(pTypeTouristPlace);
        assertNotEquals(expResult, result);
    }

    public int testIndividualQuerySelect(TypeTouristPlace pTypeTouristPlace) throws Exception {
        ComunDB comundb = new ComunDB();
        ComunDB.UtilQuery pUtilQuery = comundb.new UtilQuery("", null, 0);
        TypeTouristPlaceDAL.querySelect(pTypeTouristPlace, pUtilQuery);
        return pUtilQuery.getNumWhere();
    }

    /**
     * Test of querySelect method, of class TypeTouristPlaceDAL.
     */
    @Test
    public void test2QuerySelect() throws Exception {
        System.out.println("querySelect");
        TypeTouristPlace pTypeTouristPlace = new TypeTouristPlace();
        pTypeTouristPlace.setIdTypeTouristPlace(1);
        assertTrue(testIndividualQuerySelect(pTypeTouristPlace) == 1);
        pTypeTouristPlace.setTypeTouristPlaceName("TEST");
        assertTrue(testIndividualQuerySelect(pTypeTouristPlace) == 2);
    }

    /**
     * Test of search method, of class TypeTouristPlaceDAL.
     */
    @Test
    public void test3Search() throws Exception {
        System.out.println("search");
        TypeTouristPlace pTypeTouristPlace = new TypeTouristPlace(0, "TEST UNIT Dep");
        ArrayList<TypeTouristPlace> result = TypeTouristPlaceDAL.search(pTypeTouristPlace);
        assertTrue(result.size() > 0);
        typeTouristPlaceActual = result.get(0);
    }

    /**
     * Test of getById method, of class TypeTouristPlaceDAL.
     */
    @Test
    public void test4GetById() throws Exception {
        System.out.println("getById");
        TypeTouristPlace result = TypeTouristPlaceDAL.getById(typeTouristPlaceActual);
        assertEquals(typeTouristPlaceActual.getIdTypeTouristPlace(), result.getIdTypeTouristPlace());
    }

    /**
     * Test of update method, of class TypeTouristPlaceDAL.
     */
    @Test
    public void test5Update() throws Exception {
        System.out.println("update");
        TypeTouristPlace pTypeTouristPlace = new TypeTouristPlace();
        pTypeTouristPlace.setIdTypeTouristPlace(typeTouristPlaceActual.getIdTypeTouristPlace());
        pTypeTouristPlace.setTypeTouristPlaceName("TEST UNIT Dep M");
        int expResult = 0;
        int result = TypeTouristPlaceDAL.update(pTypeTouristPlace);
        assertNotEquals(expResult, result);
        TypeTouristPlace typeTouristPlaceUpdate = TypeTouristPlaceDAL.getById(typeTouristPlaceActual);
        assertTrue(typeTouristPlaceUpdate.getTypeTouristPlaceName().equals(pTypeTouristPlace.getTypeTouristPlaceName()));
    }

    /**
     * Test of getAll method, of class TypeTouristPlaceDAL.
     */
    @Test
    public void test6GetAll() throws Exception {
        System.out.println("getAll");
        ArrayList<TypeTouristPlace> result = TypeTouristPlaceDAL.getAll();
        assertTrue(result.size() > 0);
    }

    /**
     * Test of delete method, of class TypeTouristPlaceDAL.
     */
    @Test
    public void test7Delete() throws Exception {
        System.out.println("delete");
        int expResult = 0;
        int result = TypeTouristPlaceDAL.delete(typeTouristPlaceActual);
        assertNotEquals(expResult, result);
        TypeTouristPlace typeTouristPlaceDelete = TypeTouristPlaceDAL.getById(typeTouristPlaceActual);
        assertTrue(typeTouristPlaceDelete.getIdTypeTouristPlace() == 0);
    }

}
