<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="syssalvadoranadventures.el.TypeTouristPlace"%>
<% TypeTouristPlace typetouristplace = (TypeTouristPlace) request.getAttribute("typeTouristPlace");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle de TypeTouristPlace</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle de TypeTouristPlace</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" name="typeTouristPlaceName" type="text" value="<%=typetouristplace.getTypeTouristPlaceName()%>">
                    <label for="txtNombre">Nombre</label>
                </div>                                         
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="TypeToristPlace?accion=edit&idTypeTouristPlace=<%=typetouristplace.getIdTypeTouristPlace()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="TypeTouristPlace" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>