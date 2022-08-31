<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="syssalvadoranadventures.el.Department"%>
<% Department department = (Department) request.getAttribute("department");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Departamento</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Departamento</h5>          
            <form action="Department" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="idDepartment" value="<%=department.getIdDepartment()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" type="text" name="departmentName" value="<%=department.getDepartmentName()%>">
                    <label for="txtNombre">Nombre</label>
                </div>                                        
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Department" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
