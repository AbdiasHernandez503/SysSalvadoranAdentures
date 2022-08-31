<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Lugar Turistico</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Lugar Turistico</h5>
            <form action="TouristPlace" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="touristPlaceName" required class="validate" maxlength="60">
                        <label for="txtNombre">Nombre</label>
                    </div>                       
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/TypeTouristPlace/select.jsp">                           
                            <jsp:param name="idTypeTouristPlace" value="0" />  
                        </jsp:include>  
                        <span id="slTypeTouristPlace_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Municipality/select.jsp">                           
                            <jsp:param name="idMunicipality" value="0" />  
                        </jsp:include>  
                        <span id="slTypeTouristPlace_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtDescription" type="text" name="description" required class="validate" maxlength="200">
                        <label for="txtDescription">Descripcion</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtActivities" type="text" name="activities" required class="validate" maxlength="200">
                        <label for="txtActivities">Actividades</label>
                    </div> 
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/User/select.jsp">                           
                            <jsp:param name="idUser" value="0" />  
                        </jsp:include>  
                        <span id="slTypeTouristPlace_error" style="color:red" class="helper-text"></span>
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
                
                var slRol = document.getElementById("slTypeTouristPlace");
                var slTypeTouristPlace_error = document.getElementById("slTypeTouristPlace_error");
                if (slTypeTouristPlace.value == 0) {
                    slTypeTouristPlace_error.innerHTML = "El Tipo Lugar turistico es obligatorio es obligatorio";
                    result = false;
                } else {
                    slTypeTouristPlace_error.innerHTML = "";
                }

                return result;
            }
        </script>
    </body>
</html>
