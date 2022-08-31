package syssalvadoranadventures.ui.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import syssalvadoranadventures.ui.utils.*;
import syssalvadoranadventures.el.TouristPlace;
import syssalvadoranadventures.el.Municipality;
import syssalvadoranadventures.el.TypeTouristPlace;
import syssalvadoranadventures.el.User;
import syssalvadoranadventures.dal.TouristPlaceDAL;
import syssalvadoranadventures.dal.MunicipalityDAL;
import syssalvadoranadventures.dal.TypeTouristPlaceDAL;
import syssalvadoranadventures.dal.UserDAL;
import java.util.ArrayList;

/**
 *
 * @author Abdias_Hernandez
 */
@WebServlet(name = "TouristPlaceServlet", urlPatterns = {"/TouristPlace"})
public class TouristPlaceServlet extends HttpServlet {
    private TouristPlace getTouristPlace(HttpServletRequest request) {
            String accion = Utilidad.getParameter(request, "accion", "index");
            TouristPlace touristPlace = new TouristPlace();
            
            if(accion.equals("create") == false){
                touristPlace.setIdTouristPlace(Integer.parseInt(Utilidad.getParameter(request, "idTouristPlace", "0")));
            }
            
            touristPlace.setTouristPlaceName(Utilidad.getParameter(request, "touristPlaceName", ""));

            touristPlace.setIdTypeTouristPlace(Integer.parseInt(Utilidad.getParameter(request, "idTypeTouristPlace", "0")));

            touristPlace.setIdMunicipality(Integer.parseInt(Utilidad.getParameter(request, "idMunicipality", "0")));

            touristPlace.setDescription(Utilidad.getParameter(request, "description", ""));

            touristPlace.setActivities(Utilidad.getParameter(request, "activities", ""));

            touristPlace.setIdUser(Integer.parseInt(Utilidad.getParameter(request, "idUser", "0")));

            if (accion.equals("index")) {
                touristPlace.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
                touristPlace.setTop_aux(touristPlace.getTop_aux() == 0 ? Integer.MAX_VALUE : touristPlace.getTop_aux());
            }

            return touristPlace;
        }

        /**
         * @param request en este parámetro vamos a recibir el request de la peticion get enviada al servlet Usuario
         * @param response en este parámetro vamos a recibir el response de la peticion get enviada al servlet Usuario que utlizaremos para enviar el jsp
         * @throws javax.servlet.ServletException
         * @throws java.io.IOException
         */
        private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                TouristPlace touristPlace = new TouristPlace(); 
                touristPlace.setTop_aux(10);
                
                ArrayList<TouristPlace> touristPlaces = TouristPlaceDAL.searchIncludeMunicipality(touristPlace);
                ArrayList<TouristPlace> touristPlacesType = TouristPlaceDAL.searchIncludeTypeTouristPlace(touristPlace);
                ArrayList<TouristPlace> users = TouristPlaceDAL.searchIncludeUser(touristPlace);
                // Enviar los usuarios al jsp utilizando el request.setAttribute con el nombre del atributo usuario.
                request.setAttribute("touristPlaces", touristPlaces);
                request.setAttribute("touristPlaceType", touristPlacesType);
                request.setAttribute("users", users);
                
                request.setAttribute("top_aux", touristPlace.getTop_aux());
                request.getRequestDispatcher("Views/TouristPlace/index.jsp").forward(request, response);
            } catch (Exception ex) {
                Utilidad.enviarError(ex.getMessage(), request, response);
            }
        }

        /**
         * En este método se ejecutara cuando se envie una peticion post, al servlet Usuario , y el parámetro accion sea igual index. Este método se encargara de enviar los datos de los usuarios al jsp de index de Usuario
         *
         * @param request en este parámetro vamos a recibir el request de la peticion post enviada al servlet Usuario
         * @param response
         * @throws javax.servlet.ServletException
         * @throws java.io.IOException
         */
        private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                TouristPlace touristPlace = getTouristPlace(request); // Llenar la instancia de Usuario con los parámetros enviados en el request.
                // Ir a la capa de acceso a datos y buscar los registros de Usuario y asociar Rol.
                ArrayList<TouristPlace> touristPlaces = TouristPlaceDAL.searchIncludeMunicipality(touristPlace);
                ArrayList<TouristPlace> touristPlacesType = TouristPlaceDAL.searchIncludeTypeTouristPlace(touristPlace);
                ArrayList<TouristPlace> users = TouristPlaceDAL.searchIncludeUser(touristPlace);
                // Enviar los usuarios al jsp utilizando el request.setAttribute con el nombre del atributo usuario.
                request.setAttribute("touristPlaces", touristPlaces);
                request.setAttribute("touristPlacesType", touristPlacesType);
                request.setAttribute("users", users);
                
                request.setAttribute("top_aux", touristPlace.getTop_aux());
                request.getRequestDispatcher("Views/TouristPlace/index.jsp").forward(request, response); 
            } catch (Exception ex) {
                Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
            }
        }

        /**
         * En este método se ejecutara cuando se envie una peticion get al servlet Usuario, y el parámetro accion sea igual create.
         *
         * @param request en este parámetro vamos a recibir el request de la peticion get enviada al servlet Usuario
         * @param response
         * @throws javax.servlet.ServletException
         * @throws java.io.IOException
         */
        private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // direccionar al jsp create de Usuario
            request.getRequestDispatcher("Views/TouristPlace/create.jsp").forward(request, response);
        }

        /**
         * En este método se ejecutara cuando se envie una peticion post al servlet Usuario , y el parámetro accion sea igual create.
         *
         * @param request en este parámetro vamos a recibir el request de la peticion post enviada al servlet Usuario
         * @param response
         * @throws javax.servlet.ServletException
         * @throws java.io.IOException
         */
        private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                TouristPlace touristPlace = getTouristPlace(request); // Llenar la instancia de Usuario con los parámetros enviados en el request
                // Enviar los datos de Usuario a la capa de accesoa a datos para que lo almacene en la base de datos el registro.
                int result = TouristPlaceDAL.create(touristPlace);
                if (result != 0) { 
                    request.setAttribute("accion", "index");
                    doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex para que nos direcciones al jsp index
                } else {
                    // Enviar al jsp de error el siguiente mensaje. No se logro registrar un nuevo registro
                    Utilidad.enviarError("No se logro registrar un nuevo registro", request, response);
                }
            } catch (Exception ex) {
                // Enviar al jsp de error si hay un Exception
                Utilidad.enviarError(ex.getMessage(), request, response);
            }

        }

        /**
         * En este método obtiene por Id un Usuario desde la capa de acceso a datos el Id se captura del request que se envio al servlet de Usuario
         *
         * @param request en este parámetro vamos a recibir el request de la peticion get o post enviada al servlet Usuario
         * @param response
         * @throws javax.servlet.ServletException
         * @throws java.io.IOException
         */
        private void requestGetById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                TouristPlace touristPlace = getTouristPlace(request);
                TouristPlace touristPlace_result = TouristPlaceDAL.getById(touristPlace);
                if (touristPlace_result.getIdTouristPlace() > 0) {
                    //Obteniendo el Id de Municipio
                    Municipality municipality = new Municipality();
                    municipality.setIdMunicipality(touristPlace_result.getIdMunicipality());
                    touristPlace_result.setMunicipality(MunicipalityDAL.getById(municipality));
                    //Obteniendo el Id de Tipo Lugar Turistico
                    TypeTouristPlace typeTouristPlace = new TypeTouristPlace();
                    typeTouristPlace.setIdTypeTouristPlace(touristPlace_result.getIdTypeTouristPlace());
                    touristPlace_result.setTypeTouristPlace(TypeTouristPlaceDAL.getById(typeTouristPlace));
                    // Obteniendo el Id de Usuario
                    User user = new User();
                    user.setIdUser(touristPlace_result.getIdUser());
                    touristPlace_result.setUser(UserDAL.getById(user));
                    
                    
                    request.setAttribute("touristPlace", touristPlace_result);
                } else {
                    Utilidad.enviarError("El Id:" + touristPlace_result.getIdTouristPlace() + " no existe en la tabla de Lugar Turistico", request, response);
                }
            } catch (Exception ex) {
                // enviar al jsp de error si hay un Exception
                Utilidad.enviarError(ex.getMessage(), request, response);
            }
        }

        /**
         * En este método se ejecutara cuando se envie una peticion get al servlet Usuario , y el parámetro accion sea igual edit.
         *
         * @param request en este parámetro vamos a recibir el request de la peticion get enviada al servlet Usuario
         * @param response
         * @throws javax.servlet.ServletException
         * @throws java.io.IOException
         */
        private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Enviar el usuario al jsp de edit que se obtiene por Id
            requestGetById(request, response);
            // Direccionar al jsp edit de Usuario
            request.getRequestDispatcher("Views/TouristPlace/edit.jsp").forward(request, response);
        }

        /**
         * En este método se ejecutara cuando se envie una peticion post al servlet Usuario , y el parámetro accion sea igual edit.
         *
         * @param request en este parámetro vamos a recibir el request de la peticion post enviada al servlet Usuario
         * @param response
         * @throws javax.servlet.ServletException
         * @throws java.io.IOException
         */
        private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                TouristPlace touristPlace = getTouristPlace(request);
                // Enviar los datos de Usuario a la capa de accesoa a datos para modificar el registro.
                int result = TouristPlaceDAL.update(touristPlace);
                if (result != 0) {
                    request.setAttribute("accion", "index");
                    doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex para que nos direcciones al jsp index.
                } else {
                    // Enviar al jsp de error el siguiente mensaje. No se logro actualizar el registro.
                    Utilidad.enviarError("No se logro actualizar el registro", request, response);
                }
            } catch (Exception ex) {
                // Enviar al jsp de error si hay un Exception
                Utilidad.enviarError(ex.getMessage(), request, response);
            }
        }

        /**
         * @param request en este parámetro vamos a recibir el request de la peticion get enviada al servlet Usuario
         * @param response
         * @throws javax.servlet.ServletException
         * @throws java.io.IOException
         */
        private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Enviar el usuario al jsp de details que se obtiene por Id.
            requestGetById(request, response);
            // Direccionar al jsp details de Usuario.
            request.getRequestDispatcher("Views/TouristPlace/details.jsp").forward(request, response);
        }

        /**
         * En este método se ejecutara cuando se envie una peticion get al servlet Usuario , y el parámetro accion sea igual delete.
         *
         * @param request en este parámetro vamos a recibir el request de la peticion get enviada al servlet Usuario
         * @param response
         * @throws javax.servlet.ServletException
         * @throws java.io.IOException
         */
        private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Enviar el usuario al jsp de delete que se obtiene por Id.
            requestGetById(request, response);
            // Direccionar al jsp delete de Usuario.
            request.getRequestDispatcher("Views/TouristPlace/delete.jsp").forward(request, response);
        }

        /**
         * En este método se ejecutara cuando se envie una peticion post al servlet Usuario , y el parámetro accion sea igual delete.
         *
         * @param request en este parámetro vamos a recibir el request de la peticion post enviada al servlet Usuario
         * @param response
         * @throws javax.servlet.ServletException
         * @throws java.io.IOException
         */
        private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                TouristPlace touristPlace = getTouristPlace(request); // Llenar la instancia de Usuario con los parámetros enviados en el request.
                // Enviar los datos de Usuario a la capa de accesoa a datos para que elimine el registro.
                int result = TouristPlaceDAL.delete(touristPlace);
                if (result != 0) { // Si el result es diferente a cero significa que los datos fueron eliminados correctamente.
                    // Enviar el atributo accion con el valor index al jsp de index.
                    request.setAttribute("accion", "index");
                    doGetRequestIndex(request, response);  // Ir al método doGetRequestIndex para que nos direccione al jsp index.
                } else {
                    // Enviar al jsp de error el siguiente mensaje. No se logro eliminar el registro.
                    Utilidad.enviarError("No se logro eliminar el registro", request, response);
                }
            } catch (Exception ex) {
                // Enviar al jsp de error si hay un Exception.
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
                        // Enviar el atributo accion al jsp de index.
                        request.setAttribute("accion", accion);
                        doGetRequestIndex(request, response); // Ir al método doGetRequestIndex.
                        break;
                    case "create":
                        // Enviar el atributo accion al jsp de create.
                        request.setAttribute("accion", accion);
                        doGetRequestCreate(request, response); // Ir al metodo doGetRequestCreate.
                        break;
                    case "edit":
                        // Enviar el atributo accion al jsp de edit.
                        request.setAttribute("accion", accion);
                        doGetRequestEdit(request, response);// Ir al metodo doGetRequestEdit.
                        break;
                    case "delete":
                        // Enviar el atributo accion al jsp de delete.
                        request.setAttribute("accion", accion);
                        doGetRequestDelete(request, response); // Ir al metodo doGetRequestDelete.
                        break;
                    case "details":
                        // Enviar el atributo accion al jsp de details.
                        request.setAttribute("accion", accion);
                        doGetRequestDetails(request, response); // Ir al metodo doGetRequestDetails.
                        break;
                    default:
                        // Enviar el atributo accion al jsp de index.
                        request.setAttribute("accion", accion);
                        doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
                }
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
