<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="syssalvadoranadventures.el.TypeTouristPlace"%>
<%@page import="syssalvadoranadventures.dal.TypeTouristPlaceDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<TypeTouristPlace> typetouristplaces = TypeTouristPlaceDAL.getAll();
    int id = Integer.parseInt(request.getParameter("idTypeTouristPlace"));
%>
<select id="slTypeTouristPlace" name="idTypeTouristPlace">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (TypeTouristPlace typetouristplace : typetouristplaces) {%>
    <option <%=(id == typetouristplace.getIdTypeTouristPlace()) ? "selected" : ""%>  value="<%=typetouristplace.getIdTypeTouristPlace()%>"><%= typetouristplace.getTypeTouristPlaceName()%></option>
    <%}%>
</select>
<label for="idTypeTouristPlace">TypeTouristPlace</label>
