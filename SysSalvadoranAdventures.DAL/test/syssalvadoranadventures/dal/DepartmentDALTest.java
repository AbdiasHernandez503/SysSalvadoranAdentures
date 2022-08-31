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
import syssalvadoranadventures.el.Department;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Silvia Juárez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class DepartmentDALTest {

    private static Department departmentActual;

    public DepartmentDALTest() {
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
     * Test of create method, of class DepartmentDAL.
     */
    @Test
    public void test1Create() throws Exception {
        System.out.println("create");
        Department pDepartment = new Department(0, "TEST UNIT Dep");
        int expResult = 0;
        int result = DepartmentDAL.create(pDepartment);
        assertNotEquals(expResult, result);
    }

    public int testIndividualQuerySelect(Department pDepartment) throws Exception {
        ComunDB comundb = new ComunDB();
        ComunDB.UtilQuery pUtilQuery = comundb.new UtilQuery("", null, 0);
        DepartmentDAL.querySelect(pDepartment, pUtilQuery);
        return pUtilQuery.getNumWhere();
    }

    /**
     * Test of querySelect method, of class DepartmentDAL.
     */
    @Test
    public void test2QuerySelect() throws Exception {
        System.out.println("querySelect");
        Department pDepartment = new Department();
        pDepartment.setIdDepartment(1);
        assertTrue(testIndividualQuerySelect(pDepartment) == 1);
        pDepartment.setDepartmentName("TEST");
        assertTrue(testIndividualQuerySelect(pDepartment) == 2);
    }

    /**
     * Test of search method, of class DepartmentDAL.
     */
    @Test
    public void test3Search() throws Exception {
        System.out.println("search");
        Department pDepartment = new Department(0, "TEST UNIT Dep");
        ArrayList<Department> result = DepartmentDAL.search(pDepartment);
        assertTrue(result.size() > 0);
        departmentActual = result.get(0);
    }

    /**
     * Test of getById method, of class DepartmentDAL.
     */
    @Test
    public void test4GetById() throws Exception {
        System.out.println("getById");
        Department result = DepartmentDAL.getById(departmentActual);
        assertEquals(departmentActual.getIdDepartment(), result.getIdDepartment());
    }

    /**
     * Test of update method, of class DepartmentDAL.
     */
    @Test
    public void test5Update() throws Exception {
        System.out.println("update");
        Department pDepartment = new Department();
        pDepartment.setIdDepartment(departmentActual.getIdDepartment());
        pDepartment.setDepartmentName("TEST UNIT Dep M");
        int expResult = 0;
        int result = DepartmentDAL.update(pDepartment);
        assertNotEquals(expResult, result);
        Department departmentUpdate = DepartmentDAL.getById(departmentActual);
        assertTrue(departmentUpdate.getDepartmentName().equals(pDepartment.getDepartmentName()));
    }

    /**
     * Test of getAll method, of class DepartmentDAL.
     */
    @Test
    public void test6GetAll() throws Exception {
        System.out.println("getAll");
        ArrayList<Department> result = DepartmentDAL.getAll();
        assertTrue(result.size() > 0);
    }

    /**
     * Test of delete method, of class DepartmentDAL.
     */
    @Test
    public void test7Delete() throws Exception {
        System.out.println("delete");
        int expResult = 0;
        int result = DepartmentDAL.delete(departmentActual);
        assertNotEquals(expResult, result);
        Department departmentDelete = DepartmentDAL.getById(departmentActual);
        assertTrue(departmentDelete.getIdDepartment() == 0);
    }

}
