
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
          <c:choose>
            <c:when test="${order.getStatus().getName()=='NEW_ORDER'}" >
              <c:set var="orderStatus" value="New order" />
              <c:set var="rowStyle" value="info" />
              <c:set var="btnStyle" value="btn btn-primary" />
              <c:set var="btnName" value="Plan schedule" />
              <c:set var="url" value="error" />
            </c:when>
            <c:when test="${order.getStatus().getName()=='IN_QUEUE'}">
              <c:set var="orderStatus" value="Pending" />
              <c:set var="rowStyle" value="warning" />
              <c:set var="btnStyle" value="btn btn-success" />
              <c:set var="btnName" value="View order" />
              <c:set var="url" value="/viewOrder/${order.getId()}" />
            </c:when>
            <c:when test="${order.getStatus().getName()=='SYSTEM_CANCEL'}">
              <c:set var="orderStatus" value="Canceled by client" />
              <c:set var="rowStyle" value="error" />
              <c:set var="btnStyle" value="btn btn-danger" />
              <c:set var="btnName" value="Send propose" />
              <c:set var="url" value="error" />
            </c:when>
            <c:when test="${order.getStatus().getName()=='USER_CANCEL'}">
              <c:set var="orderStatus" value="Canceled by client" />
              <c:set var="rowStyle" value="error" />
              <c:set var="btnStyle" value="btn disabled" />
              <c:set var="btnName" value="Canceled" />
              <c:set var="url" value="error" />
            </c:when>
          </c:choose>
          <tr class="${rowStyle}">
            <td>${order.getId()}</td>
            <td>${order.deadilne}</td>
            <td>${orderStatus}</td>
            <td>
              <c:choose>
                <c:when test="${order.getStatus().getName()!='IN_PROSESS'
                             && order.getStatus().getName()!='DONE'
                             && order.getStatus().getName()!='SYSTEM_CANCEL'
                             && order.getStatus().getName()!='USER_CANCEL'}">
                    <a href="#" class="btn btn-danger">Cancel</a>
                </c:when>
                <c:otherwise>
                    <c:if test="${order.getStatus().getName()=='USER_CANCEL'}">
                      <a href="#" class="btn  disabled">Cancelled</a>
                    </c:if>
                </c:otherwise>
              </c:choose>
              <c:if test="${order.getStatus().getName()!='IN_PROSESS'
              && order.getStatus().getName()!='IN_QUEUE'}">
          <a href="${url}" class="${btnStyle}">${btnName}</a>
              </c:if>
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
