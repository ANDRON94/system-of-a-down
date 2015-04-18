
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <c:if test="${not empty workers}">
        <table class="table table-hover">
            <thead>
            <tr>
                <td ><strong>Ім’я та Прізвище</strong></td>
                <td><strong>Спеціалізації</strong></td>
                <td><strong>Операції</strong></td>
            </tr>
            </thead>
            <c:forEach var="w" items="${workers}">
                <tr>
                    <td>${w.name} ${w.sename}</td>
                    <td><c:forEach var="s" items="${w.specializations}" varStatus="st">
                        ${s.name}<br>
                    </c:forEach></td>
                    <td>
                        <a href="/manager/editWorker/${w.id}" class="btn btn-primary"><i class="icon-edit icon-white"></i>Редагувати</a>
                        <a href="/manager/deleteWorker/${w.id}" class="btn btn-primary"><i class="icon-remove icon-white"></i>Видалити</a>
                        <a href="/manager/viewWorker/${w.id}" class="btn btn-primary" ><i class="icon-share icon-white"></i>Переглянути</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>