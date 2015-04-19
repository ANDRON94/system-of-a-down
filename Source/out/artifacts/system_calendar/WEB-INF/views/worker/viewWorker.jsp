<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1>
        ${worker.name} ${worker.sename}
    </h1>
    <ul class="nav nav-list well">
        <li>Cash : ${worker.cash}</li>
        <li>DetailTypes : <c:forEach var="s" items="${worker.specializations}">
            ${s.name}&emsp;
        </c:forEach></li>
    </ul>
</div>
