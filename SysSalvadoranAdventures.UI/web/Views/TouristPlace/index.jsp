<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="syssalvadoranadventures.el.TouristPlace"%>
<%@page import="syssalvadoranadventures.el.Municipality"%>
<%@page import="syssalvadoranadventures.el.TypeTouristPlace"%>
<%@page import="syssalvadoranadventures.el.User"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<TouristPlace> touristPlaces = (ArrayList<TouristPlace>) request.getAttribute("touristPlaces");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (touristPlaces == null) {
        touristPlaces = new ArrayList();
    } else if (touristPlaces.size() > numReg) {
        double divNumPage = (double) touristPlaces.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }
    String strTop_aux = request.getParameter("top_aux");
    int top_aux = 10;
    if (strTop_aux != null && strTop_aux.trim().length() > 0) {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Buscar Lugar Turistico</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Buscar Lugar Turistico</h5>
            <form action="TouristPlace" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="touristPlaceName">
                        <label for="txtNombre">Nombre</label>
                    </div>  
                    <div class="input-field col l4 s12">   
                    <jsp:include page="/Views/Municipality/select.jsp">                           
                        <jsp:param name="idMunicipality" value="0" />  
                    </jsp:include>                        
                </div>
                <div class="input-field col l4 s12">   
                    <jsp:include page="/Views/TypeTouristPlace/select.jsp">                           
                        <jsp:param name="idTypeTouristPlace" value="0" />  
                    </jsp:include>                        
                </div>
                <div class="input-field col l4 s12">   
                    <jsp:include page="/Views/User/select.jsp">                           
                        <jsp:param name="idUser" value="0" />  
                    </jsp:include>                        
                </div>

                <div class="input-field col l4 s12">   
                    <jsp:include page="/Views/Shared/selectTop.jsp">
                        <jsp:param name="top_aux" value="<%=top_aux%>" />                        
                    </jsp:include>                        
                </div> 
                </div>                    
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn teal darken-4"><i class="material-icons right">search</i>Buscar</button>
                        <a href="TouristPlace?accion=create" title="Crear" class="btn-floating waves-effect waves-light btn blue"><i class="material-icons right">add</i>Crear</a>                          
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs">
                            <thead>
                                <tr>                                     
                                    <th>Nombre</th>  

                                    <th>Municipio</th>
                                    <th>Descripcion</th>  
                                    <th>Actividades</th> 

                                    <th>Acciones</th>
                                </tr>
                            </thead>                       
                            <tbody>                           
                                <% for (TouristPlace touristPlace : touristPlaces) {
                                        int tempNumPage = numPage;
                                        if (numPage > 1) {
                                            countReg++;
                                            double divTempNumPage = (double) countReg / (double) numReg;
                                            tempNumPage = (int) Math.ceil(divTempNumPage);
                                        }
                                %>
                                <tr data-page="<%= tempNumPage%>">                                    
                                    <td><%=touristPlace.getTouristPlaceName()%></td>  
                                    <td><%=touristPlace.getMunicipality().getMunicipalityName()%></td>

                                    <td><%=touristPlace.getDescription()%></td>
                                    <td><%=touristPlace.getActivities()%></td>  

                                    <td>
                                        <div style="display:flex">
                                            <a href="TouristPlace?accion=edit&idTouristPlace=<%=touristPlace.getIdTouristPlace()%>" title="Modificar" class="btn-floating waves-effect waves-light btn green">
                                                <i class="material-icons">edit</i>
                                            </a>
                                            <a href="TouristPlace?accion=details&idTouristPlace=<%=touristPlace.getIdTouristPlace()%>" title="Ver" class="btn-floating waves-effect waves-light btn blue">
                                                <i class="material-icons">description</i>
                                            </a>
                                            <a href="TouristPlace?accion=delete&idTouristPlace=<%=touristPlace.getIdTouristPlace()%>" title="Eliminar" class="btn-floating waves-effect waves-light btn red">
                                                <i class="material-icons">delete</i>
                                            </a>    
                                        </div>                                                                    
                                    </td>                                   
                                </tr>
                                <%}%>                                                       
                            </tbody>
                        </table>
                    </div>                  
                </div>
            </div>             
            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPage%>" />                        
                    </jsp:include>
                </div>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      

    </body>
</html>
