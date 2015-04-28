<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
  <c:when test="${ not empty scheduleVariants}">
    <c:forEach varStatus="status" var="schedule" items="${scheduleVariants}">
        ${status.count}.<br>
      ${schedule.planner.render()}

    </c:forEach>
  </c:when>
  <c:otherwise>
    <h1>We can`t perform this order!</h1>
  </c:otherwise>
</c:choose>

