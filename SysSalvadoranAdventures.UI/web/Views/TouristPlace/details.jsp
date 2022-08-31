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
            <h5>Detalle de Lugar  Turistico</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" name="touristPlaceName" type="text" value="<%=touristPlace.getTouristPlaceName()%>">
                    <label for="txtNombre">Nombre</label>
                </div>
                <div class="input-field col l4 s12">
                    <input id="txtTypeTouristPlace" type="text" value="<%=touristPlace.getTypeTouristPlace().getTypeTouristPlaceName()%>" disabled>
                    <label for="txtTypeTouristPlace">TipoLugar</label>
                </div> 
                <div class="input-field col l4 s12">
                    <input id="txtMunicipio" type="text" value="<%=touristPlace.getMunicipality().getMunicipalityName()%>" disabled>
                    <label for="txtMunicipio">Municipio</label>
                </div> 
                <div class="input-field col l4 s12">
                    <input id="txtDescripcion" type="text" value="<%=touristPlace.getDescription()%>" disabled>
                    <label for="txtDescripcion">Descripcion</label>
                </div> 
                <div class="input-field col l4 s12">
                    <input id="txtActivities" type="text" value="<%=touristPlace.getActivities()%>" disabled>
                    <label for="txtActivities">Actividades</label>
                </div> 
                <div class="input-field col l4 s12">
                    <input id="txtUser" type="text" value="<%=touristPlace.getUser().getLogin()%>" disabled>
                    <label for="txtUser">Usuario</label>
                </div> 
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="TouristPlace?accion=edit&idTouristPlace=<%=touristPlace.getIdTouristPlace()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="TouristPlace" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" /> 
    </body>
</html>
