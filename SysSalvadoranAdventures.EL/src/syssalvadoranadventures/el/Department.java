/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syssalvadoranadventures.el;

import java.util.ArrayList;
/**
 *
 * @author Abdias_Hernandez
 */
public class Department {
    
    private int idDepartment;
    private String departmentName;
    
    private int top_aux;
    private ArrayList<Municipality>municipalities;
    
    public Department() {
        
    }
    
    public Department(int idDepartment, String departmentName){
        this.idDepartment = idDepartment;
        this.departmentName = departmentName;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public ArrayList<Municipality> getMunicipalities() {
        return municipalities;
    }

    public void setMunicipalities(ArrayList<Municipality> municipalities) {
        this.municipalities = municipalities;
    }
    
}
