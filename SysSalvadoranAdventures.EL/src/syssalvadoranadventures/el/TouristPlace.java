/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syssalvadoranadventures.el;

/**
 *
 * @author Abdias_Hernandez
 */
public class TouristPlace {
    
    private int idTouristPlace;
    private String touristPlaceName;
    private int idTypeTouristPlace;
    private int idMunicipality;
    private String description;
    private String activities;
    private int idUser;
    private String photos;
    
    //Atributos no mapeados
    private int top_aux;
    private TypeTouristPlace typeTouristPlace;
    private Municipality municipality;
    private User user;
    
    public TouristPlace(){
        
    }
    
    public TouristPlace(int idTouristPlace, String touristPlaceName, int idTypeTouristPlace, int idMunicipality, String description, String activities, int idUser, String photos){
        this.idTouristPlace = idTouristPlace;
        this.touristPlaceName = touristPlaceName;
        this.idTypeTouristPlace = idTypeTouristPlace;
        this.idMunicipality = idMunicipality;
        this.description = description;
        this.activities = activities;
        this.idUser = idUser; 
        this.photos = photos;
    }

    public int getIdTouristPlace() {
        return idTouristPlace;
    }

    public void setIdTouristPlace(int idTouristPlace) {
        this.idTouristPlace = idTouristPlace;
    }

    public String getTouristPlaceName() {
        return touristPlaceName;
    }

    public void setTouristPlaceName(String touristPlaceName) {
        this.touristPlaceName = touristPlaceName;
    }

    public int getIdTypeTouristPlace() {
        return idTypeTouristPlace;
    }

    public void setIdTypeTouristPlace(int idTypeTouristPlace) {
        this.idTypeTouristPlace = idTypeTouristPlace;
    }

    public int getIdMunicipality() {
        return idMunicipality;
    }

    public void setIdMunicipality(int idMunicipality) {
        this.idMunicipality = idMunicipality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public TypeTouristPlace getTypeTouristPlace() {
        return typeTouristPlace;
    }

    public void setTypeTouristPlace(TypeTouristPlace typeTouristPlace) {
        this.typeTouristPlace = typeTouristPlace;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    
}
