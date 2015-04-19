<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <c:if test="${not empty details}">
        <table class="table table-hover">
            <thead>
            <tr>
                <td ><strong>Назва деталі</strong></td>
                <td><strong>Тип</strong></td>
                <td><strong>Операції</strong></td>
            </tr>
            </thead>
            <c:forEach var="d" items="${details}">
                <tr>
                    <td>${d.name}</td>
                    <td>${d.detailType.name}</td>
                    <td>
                        <a href="/manager/editDetail/${d.id}" class="btn btn-primary"><i class="icon-edit icon-white"></i>Редагувати</a>
                        <a href="/manager/deleteDetail/${d.id}" class="btn btn-primary"><i class="icon-remove icon-white"></i>Видалити</a>
                        <a href="/manager/viewDetail/${d.id}" class="btn btn-primary" ><i class="icon-share icon-white"></i>Переглянути</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>


