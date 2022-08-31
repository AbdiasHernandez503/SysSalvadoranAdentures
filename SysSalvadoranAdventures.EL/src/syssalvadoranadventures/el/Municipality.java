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
public class Municipality {
    private int idMunicipality;
    private int idDepartment;
    private String municipalityName;
    
    private int top_aux;
    private Department department;
    private ArrayList<TouristPlace>touristPlaces;
    
    public Municipality(){
        
    }
    public Municipality(int idMunicipality, int idDepartment, String municipalityName) {
        this.idMunicipality = idMunicipality;
        this.idDepartment = idDepartment;
        this.municipalityName = municipalityName;
    }

    public int getIdMunicipality() {
        return idMunicipality;
    }

    public void setIdMunicipality(int idMunicipality) {
        this.idMunicipality = idMunicipality;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ArrayList<TouristPlace> getTouristPlaces() {
        return touristPlaces;
    }

    public void setTouristPlaces(ArrayList<TouristPlace> touristPlaces) {
        this.touristPlaces = touristPlaces;
    }
}
