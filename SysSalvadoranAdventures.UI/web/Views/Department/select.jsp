<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="syssalvadoranadventures.el.Department"%>
<%@page import="syssalvadoranadventures.dal.DepartmentDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Department> departments = DepartmentDAL.getAll();
    int id = Integer.parseInt(request.getParameter("idDepartment"));
%>
<select id="slDepartment" name="idDepartment">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Department department : departments) {%>
    <option <%=(id == department.getIdDepartment()) ? "selected" : ""%>  value="<%=department.getIdDepartment()%>"><%= department.getDepartmentName()%></option>
    <%}%>
</select>
<label for="idDepartment">Departamento</label>
