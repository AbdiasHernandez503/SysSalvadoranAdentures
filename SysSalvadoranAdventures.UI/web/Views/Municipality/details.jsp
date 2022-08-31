<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="syssalvadoranadventures.el.Municipality"%>
<% Municipality municipality = (Municipality) request.getAttribute("municipality");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle de Municipio</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle de Municipio</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" name="municipalityName" type="text" value="<%=municipality.getMunicipalityName()%>">
                    <label for="txtNombre">Nombre</label>
                </div>
                <div class="input-field col l4 s12">
                        <input id="txtDepartment" type="text" value="<%=municipality.getDepartment().getDepartmentName()%>" disabled>
                        <label for="txtDepartment">Department</label>
                </div>              
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="Municipality?accion=edit&idMunicipality=<%=municipality.getIdMunicipality()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="Municipality" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>

