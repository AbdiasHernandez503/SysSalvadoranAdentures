<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="syssalvadoranadventures.el.Municipality"%>
<%@page import="syssalvadoranadventures.dal.MunicipalityDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Municipality> municipalities = MunicipalityDAL.getAll();
    int id = Integer.parseInt(request.getParameter("idMunicipality"));
%>
<select id="slMunicipality" name="idMunicipality">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Municipality municipality : municipalities) {%>
    <option <%=(id == municipality.getIdMunicipality()) ? "selected" : ""%>  value="<%=municipality.getIdMunicipality()%>"><%= municipality.getMunicipalityName()%></option>
    <%}%>
</select>
<label for="idMunicipality">Municipality</label>
