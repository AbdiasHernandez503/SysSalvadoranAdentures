package syssalvadoranadventures.ui.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import syssalvadoranadventures.el.Department;
import syssalvadoranadventures.el.Municipality;
import syssalvadoranadventures.dal.MunicipalityDAL;
import syssalvadoranadventures.dal.DepartmentDAL;
import syssalvadoranadventures.ui.utils.*;
import java.util.ArrayList;
/**
 *
 * @author Abdias_Hernandez
 */
@WebServlet(name = "MunicipalityServlet", urlPatterns = {"/Municipality"})
public class MunicipalityServlet extends HttpServlet {

    private Municipality getMunicipality(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
         Municipality municipality = new Municipality();
         
        if (accion.equals("create") == false) {
            municipality.setIdMunicipality(Integer.parseInt(Utilidad.getParameter(request, "idMunicipality", "0")));
        }
       
        municipality.setMunicipalityName(Utilidad.getParameter(request, "municipalityName", ""));
        municipality.setIdDepartment(Integer.parseInt(Utilidad.getParameter(request, "idDepartment", "0")));
        
        if (accion.equals("index")) {
            municipality.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            municipality.setTop_aux(municipality.getTop_aux() == 0 ? Integer.MAX_VALUE : municipality.getTop_aux());
        }

        return municipality; 
    }
    
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Municipality municipality = new Municipality(); 
            municipality.setTop_aux(10); 
            ArrayList<Municipality> municipalities = MunicipalityDAL.searchIncludeDepartment(municipality);
            
            request.setAttribute("municipalities", municipalities);
            request.setAttribute("top_aux", municipality.getTop_aux());
            request.getRequestDispatcher("Views/Municipality/index.jsp").forward(request, response); 
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); 
        }
    }
    
    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Municipality municipality = getMunicipality(request); 

            ArrayList<Municipality> municipalities = MunicipalityDAL.searchIncludeDepartment(municipality);
            
            request.setAttribute("municipalities", municipalities);
            request.setAttribute("top_aux", municipality.getTop_aux());
            request.getRequestDispatcher("Views/Municipality/index.jsp").forward(request, response); 
        } catch (Exception ex) {
           
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
     private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getRequestDispatcher("Views/Municipality/create.jsp").forward(request, response);
    }
     
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Municipality municipality = getMunicipality(request); 
           
            int result = MunicipalityDAL.create(municipality);
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
            Municipality municipality = getMunicipality(request); 
            
            Municipality municipality_result = MunicipalityDAL.getById(municipality);
            if (municipality_result.getIdMunicipality() > 0) { 
                Department department = new Department();
                department.setIdDepartment(municipality_result.getIdDepartment());
                
                
                municipality_result.setDepartment(DepartmentDAL.getById(department));
                
                request.setAttribute("municipality", municipality_result);
            } else {
                
                Utilidad.enviarError("El Id:" + municipality_result.getIdMunicipality() + " no existe en la tabla de Municipality", request, response);
            }
        } catch (Exception ex) {
            
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Municipality/edit.jsp").forward(request, response);
    }
    
    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Municipality municipality = getMunicipality(request); 
            int result = MunicipalityDAL.update(municipality);
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
        
        request.getRequestDispatcher("Views/Municipality/details.jsp").forward(request, response);
    }
     
     private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Municipality/delete.jsp").forward(request, response);
    } 
     
     private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Municipality municipality = getMunicipality(request); 
            
            int result = MunicipalityDAL.delete(municipality);
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
                    doPostRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doPostRequestCreate(request, response); // Ir al metodo doPostRequestCreate.
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doPostRequestEdit(request, response); // Ir al metodo doPostRequestEdit.
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doPostRequestDelete(request, response); // Ir al metodo doPostRequestDelete.
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
            }
        });
    }
// </editor-fold>

}
