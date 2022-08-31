<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="syssalvadoranadventures.el.TouristPlace"%>
<% TouristPlace touristPlace = (TouristPlace) request.getAttribute("touristPlace");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Lugar Turistico</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar Lugar Turistico</h5>
            <form action="TouristPlace" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="idTouristPlace" value="<%=touristPlace.getIdTouristPlace()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="touristPlaceName" value="<%=touristPlace.getTouristPlaceName()%>" required class="validate" maxlength="60">
                        <label for="txtNombre">Nombre</label>
                    </div>
                     <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/TypeTouristPlace/select.jsp">                           
                            <jsp:param name="idTypeTouristPlace" value="<%=touristPlace.getIdTypeTouristPlace() %>" />  
                        </jsp:include>  
                        <span id="slTypeTouristPlace_error" style="color:red" class="helper-text"></span>
                    </div>
                  
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Municipality/select.jsp">                           
                            <jsp:param name="idMunicipality" value="<%=touristPlace.getIdMunicipality() %>" />  
                        </jsp:include>  
                        <span id="slMunicipality_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtDescripcion" type="text" name="description" value="<%=touristPlace.getTouristPlaceName()%>" required class="validate" maxlength="200">
                        <label for="txtDescripcion">Descripcion</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtActivities" type="text" name="activities" value="<%=touristPlace.getTouristPlaceName()%>" required class="validate" maxlength="200">
                        <label for="txtActivities">Actividades</label>
                    </div>
                    
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/User/select.jsp">                           
                            <jsp:param name="idUser" value="<%=touristPlace.getIdUser() %>" />  
                        </jsp:include>  
                        <span id="slUser_error" style="color:red" class="helper-text"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="TouristPlace" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />    
        <script>
            function validarFormulario() {
                var result = true;
                var slTypeTouristPlace = document.getElementById("slTypeTouristPlace");
                var slTypeTouristPlace_error = document.getElementById("slTypeTouristPlace_error");
                var slMunicipality = document.getElementById("slMunicipality");
                var slMunicipality_error = document.getElementById("slMunicipality_error");
                var slUser = document.getElementById("slUser");
                var slUser_error = document.getElementById("slUser_error");
                
                if (slTypeTouristPlace.value == 0) {
                    slTypeTouristPlace_error.innerHTML = "El TypeTouristPlace es obligatorio";
                    result = false;
                } else {
                    slTypeTouristPlace_error.innerHTML = "";
                    
                }
                if (slMunicipality.value == 0) {
                    slMunicipality_error.innerHTML = "El Municipality es obligatorio";
                    result = false;
                } else {
                    slMunicipality_error.innerHTML = "";
                }
                if (slUser.value == 0) {
                    slUser_error.innerHTML = "El User es obligatorio";
                    result = false;
                } else {
                    slUser_error.innerHTML = "";
                }

                return result;
            }
        </script>
    </body>
</html>

