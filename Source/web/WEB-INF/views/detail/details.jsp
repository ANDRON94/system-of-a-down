<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1>${user.iduser}</h1><br><h1>${user.password}</h1>
    <c:if test="${not empty details}">
        <table class="table table-hover">
            <thead>
            <tr>
                <td ><strong>Назва деталі</strong></td>
                <td><strong>Тип</strong></td>
                <td><strong>Операції</strong></td>
            </tr>
            </thead>
            <c:forEach var="r" items="${details}">
                <tr>
                    <td>${r.iddetail}</td>
                    <td>${r.title}</td>
                    <td>
                        <a href="/user/editRecord/${r.iddetail}" class="btn btn-primary"><i class="icon-edit icon-white"></i>Редагувати</a>
                        <a href="/user/deleteRecord/${r.iddetail}" class="btn btn-primary"><i class="icon-remove icon-white"></i>Видалити</a>
                        <a href="/user/viewRecord/${r.iddetail}" class="btn btn-primary" ><i class="icon-share icon-white"></i>Переглянути</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>


