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
import syssalvadoranadventures.el.Department;
import syssalvadoranadventures.dal.DepartmentDAL;
import syssalvadoranadventures.ui.utils.*;
import java.util.ArrayList;

/**
 *
 * @author Abdias_Hernandez
 */
@WebServlet(name = "DepartmentServlet", urlPatterns = {"/Department"})
public class DepartmentServlet extends HttpServlet {

    private Department getDepartment(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Department department = new Department();
        if (accion.equals("create") == false) {
            department.setIdDepartment(Integer.parseInt(Utilidad.getParameter(request, "idDepartment", "0")));
        }

        department.setDepartmentName(Utilidad.getParameter(request, "departmentName", ""));
        if (accion.equals("index")) {
            department.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            department.setTop_aux(department.getTop_aux() == 0 ? Integer.MAX_VALUE : department.getTop_aux());
        }

        return department;
    }
    
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Department department = new Department(); 
            department.setTop_aux(10); 
            ArrayList<Department> departments = DepartmentDAL.search(department);
            request.setAttribute("departments", departments); 
            
            request.setAttribute("top_aux", department.getTop_aux());
                          
            request.getRequestDispatcher("Views/Department/index.jsp").forward(request, response); 
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); 
        }
    }
    
     private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Department department = getDepartment(request); 
            ArrayList<Department> departments = DepartmentDAL.search(department); 
            request.setAttribute("departments", departments); 
            
            request.setAttribute("top_aux", department.getTop_aux());
            request.getRequestDispatcher("Views/Department/index.jsp").forward(request, response); 
        } catch (Exception ex) {
           
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
     private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getRequestDispatcher("Views/Department/create.jsp").forward(request, response);
    }
    
     private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Department department = getDepartment(request); 
           
            int result = DepartmentDAL.create(department);
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
            Department department = getDepartment(request); 
            
            Department department_result = DepartmentDAL.getById(department);
            if (department_result.getIdDepartment() > 0) { 
                request.setAttribute("department", department_result);
            } else {
                Utilidad.enviarError("El Id:" + department.getIdDepartment() + " no existe en la tabla de Department", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
     
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Department/edit.jsp").forward(request, response);
    }
    
    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Department department = getDepartment(request); 
            int result = DepartmentDAL.update(department);
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
        
        request.getRequestDispatcher("Views/Department/details.jsp").forward(request, response);
    }
     
     private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Department/delete.jsp").forward(request, response);
    } 
     
     private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Department department = getDepartment(request); 
            
            int result = DepartmentDAL.delete(department);
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
