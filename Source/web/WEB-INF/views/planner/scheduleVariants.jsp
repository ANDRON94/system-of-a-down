<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
  <c:when test="${ not empty scheduleVariants}">
    <c:forEach varStatus="status" var="schedule" items="${scheduleVariants}">
      <h3>Schedule № ${status.count}</h3>
      Total salary for all workers: ${schedule.getCash()}<br>
      <a href="/planner/scheduleVariant/${status.index}">See schedule result</a>
    </c:forEach>
  </c:when>
  <c:otherwise>
    <h1>We can`t perform this order!</h1>
  </c:otherwise>
</c:choose>