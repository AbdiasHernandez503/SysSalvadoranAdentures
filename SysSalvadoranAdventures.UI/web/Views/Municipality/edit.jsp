<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="syssalvadoranadventures.el.Municipality"%>
<% Municipality municipality = (Municipality) request.getAttribute("municipality");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Municipio</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar Municipio</h5>
            <form action="Municipality" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="idMunicipality" value="<%=municipality.getIdMunicipality()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="municipalityName" value="<%=municipality.getMunicipalityName()%>" required class="validate" maxlength="60">
                        <label for="txtNombre">Nombre</label>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Department/select.jsp">                           
                            <jsp:param name="idDepartment" value="<%=municipality.getIdDepartment() %>" />  
                        </jsp:include>  
                        <span id="slDepartment_error" style="color:red" class="helper-text"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Municipality" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />    
        <script>
            function validarFormulario() {
                var result = true;
                var slDepartment = document.getElementById("slDepartment");
                var slDepartment_error = document.getElementById("slDepartment_error");
                
                if (slDepartment.value == 0) {
                    slDepartment_error.innerHTML = "El Department es obligatorio";
                    result = false;
                } else {
                    slDepartment_error.innerHTML = "";
                }

                return result;
            }
        </script>
    </body>
</html>
