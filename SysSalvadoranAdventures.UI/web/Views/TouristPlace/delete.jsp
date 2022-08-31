<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="syssalvadoranadventures.el.TouristPlace"%>
<% TouristPlace touristPlace = (TouristPlace) request.getAttribute("touristPlace");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Lugar Turistico</h5>          
            <form action="TouristPlace" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="idTouristPlace" value="<%=touristPlace.getIdTouristPlace()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input disabled  id="txtNombre" type="text" name="touristPlaceName" value="<%=touristPlace.getTouristPlaceName()%>">
                        <label for="txtNombre">Nombre</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input id="txtTypeTouristPlace" type="text" value="<%=touristPlace.getMunicipality().getMunicipalityName()%>" disabled>
                        <label for="txtTypeTouristPlace">Tipo Lugar</label>
                    </div>  
                    <div class="input-field col l4 s12">
                        <input id="txtMunicipio" type="text" value="<%=touristPlace.getMunicipality().getMunicipalityName()%>" disabled>
                        <label for="txtMunicipio">Municipio</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input disabled  id="txtDescripcion" type="text" name="description" value="<%=touristPlace.getDescription()%>">
                        <label for="txtDescription">Descripcion</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input disabled  id="txtActivities" type="text" name="activities" value="<%=touristPlace.getActivities()%>">
                        <label for="txtActivities">Actividades</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input id="txtUser" type="text" value="<%=touristPlace.getUser().getLogin()%>" disabled>
                        <label for="txtUser">Usuario</label>
                    </div>
                </div>


                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="TouristPlace" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" /> 
    </body>
</html>
