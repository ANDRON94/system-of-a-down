
<%--
  Created by IntelliJ IDEA.
  User: andron94
  Date: 18.04.15
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
  <c:choose>
    <c:when test="${not empty orders}">
      <table class="table">
        <thead>
        <tr>
          <td><strong>ID</strong></td>
          <td><strong>Deadline</strong></td>
          <td><strong>Action</strong></td>
        </tr>
        </thead>
        <c:forEach var="order" items="${orders}">
          <tr class="${rowStyle}">
            <td>${order.getId()}</td>
            <td>${order.deadilne}</td>
            <td>${order.status.name}</td>
            <td>
              <c:choose>
                <c:when test="${order.getStatus().getName()!='IN_PROSESS'
                             && order.getStatus().getName()!='DONE'
                             && order.getStatus().getName()!='SYSTEM_CANCEL'
                             && order.getStatus().getName()!='USER_CANCEL'}">
                    <a href="/user/cancel/${order.getId()}" class="btn btn-danger">Cancel</a>
                </c:when>
                <c:otherwise>
                    <c:if test="${order.getStatus().getName()=='USER_CANCEL'}">
                      <a href="#" class="btn  disabled">Cancelled</a>
                    </c:if>
                </c:otherwise>
              </c:choose>
              <a href="/user/viewOrder/${order.getId()}" class="btn btn-success">View order</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </c:when>
    <c:otherwise>
      <h1>Sorry, but . . . all orders, gone . . . =(</h1>
    </c:otherwise>
  </c:choose>
</div>
