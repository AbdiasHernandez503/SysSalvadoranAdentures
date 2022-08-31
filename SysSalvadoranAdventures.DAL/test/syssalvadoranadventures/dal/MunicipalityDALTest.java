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
import syssalvadoranadventures.el.Municipality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import syssalvadoranadventures.el.Department;
import java.time.LocalDate;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Silvia Juárez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MunicipalityDALTest {

    private static Municipality municipalityActual;

    public MunicipalityDALTest() {
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
     * Test of create method, of class MunicipalityDAL.
     */
    @Test
    public void test1Create() throws Exception {
        System.out.println("create");
        Municipality municipality = new Municipality();
        municipality.setMunicipalityName("Nombre UNIT TEST");
        Department departmentB = new Department();
        departmentB.setTop_aux(1);
        municipality.setIdDepartment(DepartmentDAL.search(departmentB).get(0).getIdDepartment());
        int expResult = 0;
        int result = MunicipalityDAL.create(municipality);
        assertNotEquals(expResult, result);
    }

    public int testIndividualQuerySelect(Municipality pMunicipality) throws Exception {
        ComunDB comundb = new ComunDB();
        ComunDB.UtilQuery pUtilQuery = comundb.new UtilQuery("", null, 0);
        MunicipalityDAL.querySelect(pMunicipality, pUtilQuery);
        return pUtilQuery.getNumWhere();
    }

    /**
     * Test of querySelect method, of class MunicipalityDAL.
     */
    @Test
    public void test2QuerySelect() throws Exception {
        System.out.println("querySelect");
        int index = 0;
        Municipality municipality = new Municipality();
        municipality.setIdMunicipality(1);
        index++;
        assertTrue(testIndividualQuerySelect(municipality) == index);
        municipality.setMunicipalityName("TEST");
        index++;
        assertTrue(testIndividualQuerySelect(municipality) == index);
        municipality.setIdDepartment(1);
        index++;
        assertTrue(testIndividualQuerySelect(municipality) == index);
    }
    
     /**
     * Test of search method, of class MunicipalityDAL.
     */
    /*
    @Test
    public void test3Search() throws Exception {
        System.out.println("search");
        Municipality pMunicipality = new Municipality(0, "TEST UNIT Mun");
        ArrayList<Municipality> result = MunicipalityDAL.search(pMunicipality);
        assertTrue(result.size() > 0);
        municipalityActual = result.get(0);
    }*/

   
    /**
     * Test of getById method, of class MunicipalityDAL.
     */
    @Test
    public void test4GetById() throws Exception {
        System.out.println("getById");
        Municipality result = MunicipalityDAL.getById(municipalityActual);
        assertEquals(municipalityActual.getIdMunicipality(), result.getIdMunicipality());
    }

    /**
     * Test of update method, of class MunicipalityDAL.
     */
    @Test
    public void test5Update() throws Exception {
        System.out.println("update");
        Municipality pMunicipality = new Municipality();
        pMunicipality.setIdMunicipality(municipalityActual.getIdDepartment());
        pMunicipality.setMunicipalityName("Nombre UNIT TEST M");
        Department departmentB = new Department();
        departmentB.setTop_aux(2);
        pMunicipality.setIdDepartment(DepartmentDAL.search(departmentB).get(1).getIdDepartment());
        int expResult = 0;
        int result = MunicipalityDAL.update(pMunicipality);
        assertNotEquals(expResult, result);
        Municipality municipalityUpdate = MunicipalityDAL.getById(municipalityActual);
        assertTrue(municipalityUpdate.getMunicipalityName().equals(pMunicipality.getMunicipalityName()));

    }

    /**
     * Test of getAll method, of class MunicipalityDAL.
     */
    @Test
    public void test6GetAll() throws Exception {
        System.out.println("getAll");
        ArrayList<Municipality> result = MunicipalityDAL.getAll();
        assertTrue(result.size() > 0);
    }

     /**
     * Test of searchIncludeDepartment method, of class MunicipalityDAL.
     */
    @Test
    public void test7SearchIncludeDepartment() throws Exception {
        System.out.println("searchIncludeDepartment");
        Municipality municipality = new Municipality();
        municipality.setTop_aux(10);
        ArrayList<Municipality> result = MunicipalityDAL.searchIncludeDepartment(municipality);
        assertTrue(result.size() > 0);
        Municipality municipalityConDepartment = result.get(0);
        assertTrue(municipalityConDepartment.getIdDepartment() == municipalityConDepartment.getDepartment().getIdDepartment());
    }

    /**
     * Test of delete method, of class MunicipalityDAL.
     */
    @Test
    public void test8Delete() throws Exception {
        System.out.println("delete");
        int expResult = 0;
        int result = MunicipalityDAL.delete(municipalityActual);
        assertNotEquals(expResult, result);
        Municipality municipalityDelete = MunicipalityDAL.getById(municipalityActual);
        assertTrue(municipalityDelete.getIdMunicipality() == 0);
    }

}
