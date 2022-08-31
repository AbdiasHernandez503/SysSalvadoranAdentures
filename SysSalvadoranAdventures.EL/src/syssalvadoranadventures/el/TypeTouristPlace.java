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
public class TypeTouristPlace {
    
     private int idTypeTouristPlace;
    private String typeTouristPlaceName;
    
    private int top_aux;
    private ArrayList<TouristPlace> touristPlaces;
    
    public TypeTouristPlace() {
        
    }
    
    public TypeTouristPlace(int idTypeTouristPlace, String typeTouristPlaceName) {
        this.idTypeTouristPlace = idTypeTouristPlace;
        this.typeTouristPlaceName = typeTouristPlaceName;
    }

    public int getIdTypeTouristPlace() {
        return idTypeTouristPlace;
    }

    public void setIdTypeTouristPlace(int idTypeTouristPlace) {
        this.idTypeTouristPlace = idTypeTouristPlace;
    }

    public String getTypeTouristPlaceName() {
        return typeTouristPlaceName;
    }

    public void setTypeTouristPlaceName(String typeTouristPlaceName) {
        this.typeTouristPlaceName = typeTouristPlaceName;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public ArrayList<TouristPlace> getTouristPlaces() {
        return touristPlaces;
    }

    public void setTouristPlaces(ArrayList<TouristPlace> touristPlaces) {
        this.touristPlaces = touristPlaces;
    }
}
