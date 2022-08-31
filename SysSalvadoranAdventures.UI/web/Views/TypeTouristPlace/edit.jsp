<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="syssalvadoranadventures.el.TypeTouristPlace"%>
<% TypeTouristPlace typetouristplace = (TypeTouristPlace) request.getAttribute("typeTouristPlace");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar TypeTouristPlace</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar TypeTouristPlace</h5>
            <form action="TypeTouristPlace" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="idTypeTouristPlace" value="<%=typetouristplace.getIdTypeTouristPlace()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="typeTouristPlaceName" value="<%=typetouristplace.getTypeTouristPlaceName()%>" required class="validate" maxlength="60">
                        <label for="txtNombre">Nombre</label>
                    </div>                                       
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="TypeTouristPlace" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>