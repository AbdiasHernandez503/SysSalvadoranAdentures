/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package syssalvadoranadventures.ui.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import syssalvadoranadventures.el.TypeTouristPlace;
import syssalvadoranadventures.dal.TypeTouristPlaceDAL;
import syssalvadoranadventures.ui.utils.*;
import java.util.ArrayList;
/**
 *
 * @author Abdias_Hernandez
 */
@WebServlet(name = "TypeTouristPlaceServlet", urlPatterns = {"/TypeTouristPlace"})
public class TypeTouristPlaceServlet extends HttpServlet {
  
    private TypeTouristPlace getTypeTouristPlace(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        TypeTouristPlace typeTouristPlace = new TypeTouristPlace();
        if (accion.equals("create") == false) {
            typeTouristPlace.setIdTypeTouristPlace(Integer.parseInt(Utilidad.getParameter(request, "idTypeTouristPlace", "0")));
        }

        typeTouristPlace.setTypeTouristPlaceName(Utilidad.getParameter(request, "typeTouristPlaceName", ""));
        if (accion.equals("index")) {
            typeTouristPlace.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            typeTouristPlace.setTop_aux(typeTouristPlace.getTop_aux() == 0 ? Integer.MAX_VALUE : typeTouristPlace.getTop_aux());
        }

        return typeTouristPlace;
    }
    
     private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TypeTouristPlace typeTouristPlace = new TypeTouristPlace(); 
            typeTouristPlace.setTop_aux(10); 
            ArrayList<TypeTouristPlace> typeTouristPlaces = TypeTouristPlaceDAL.search(typeTouristPlace);
            request.setAttribute("typeTouristPlaces", typeTouristPlaces); 
            
            request.setAttribute("top_aux", typeTouristPlace.getTop_aux());
                          
            request.getRequestDispatcher("Views/TypeTouristPlace/index.jsp").forward(request, response); 
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); 
        }
    }
     
     private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TypeTouristPlace typeTouristPlace = getTypeTouristPlace(request); 
            ArrayList<TypeTouristPlace> typeTouristPlaces = TypeTouristPlaceDAL.search(typeTouristPlace); 
            request.setAttribute("typeTouristPlaces", typeTouristPlaces); 
            
            request.setAttribute("top_aux", typeTouristPlace.getTop_aux());
            request.getRequestDispatcher("Views/TypeTouristPlace/index.jsp").forward(request, response); 
        } catch (Exception ex) {
           
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    } 
    
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getRequestDispatcher("Views/TypeTouristPlace/create.jsp").forward(request, response);
    }
    
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TypeTouristPlace typeTouristPlace = getTypeTouristPlace(request); 
           
            int result = TypeTouristPlaceDAL.create(typeTouristPlace);
            if (result != 0) { 
                    
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); 
            } else {
                
                Utilidad.enviarError("No se logro registrar un nuevo registro", request, response);
            }
        } catch (Exception ex) {
            
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void requestGetById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TypeTouristPlace typeTouristPlace = getTypeTouristPlace(request); 
            
            TypeTouristPlace typeTouristPlace_result = TypeTouristPlaceDAL.getById(typeTouristPlace);
            if (typeTouristPlace_result.getIdTypeTouristPlace() > 0) { 
                request.setAttribute("typeTouristPlace", typeTouristPlace_result);
            } else {
                Utilidad.enviarError("El Id:" + typeTouristPlace.getIdTypeTouristPlace() + " no existe en la tabla de TypeTouristPlace", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/TypeTouristPlace/edit.jsp").forward(request, response);
    }
    
    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TypeTouristPlace typeTouristPlace = getTypeTouristPlace(request); 
            int result = TypeTouristPlaceDAL.update(typeTouristPlace);
            if (result != 0) { 
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); 
            } else {
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
     private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        requestGetById(request, response);
        
        request.getRequestDispatcher("Views/TypeTouristPlace/details.jsp").forward(request, response);
    }
    
    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        requestGetById(request, response);
        request.getRequestDispatcher("Views/TypeTouristPlace/delete.jsp").forward(request, response);
    }  
    
    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TypeTouristPlace typeTouristPlace = getTypeTouristPlace(request); 
            
            int result = TypeTouristPlaceDAL.delete(typeTouristPlace);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); 
            } else {
               
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, "accion", "index");
            switch (accion) {
                case "index":
                    
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); 
                    break;
                case "create":
                   
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response); 
                    break;
                case "edit":
                   
                    request.setAttribute("accion", accion);
                    doGetRequestEdit(request, response);
                    break;
                case "delete":
                   
                    request.setAttribute("accion", accion);
                    doGetRequestDelete(request, response); 
                    break;
                case "details":
                    
                    request.setAttribute("accion", accion);
                    doGetRequestDetails(request, response); 
                    break;
                default:
                   
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);             }
        });
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, "accion", "index");
            switch (accion) {
                case "index":
                    request.setAttribute("accion", accion);
                    doPostRequestIndex(request, response); 
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doPostRequestCreate(request, response); 
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doPostRequestEdit(request, response); 
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doPostRequestDelete(request, response); 
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); 
            }
        });
    }
    // </editor-fold>
}
