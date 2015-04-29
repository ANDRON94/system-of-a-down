<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andron94
  Date: 19.04.15
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
  <c:when test="${order.getStatus().getName()=='NEW_ORDER'}">
    <c:set var="orderStatus" value="New order" />
  </c:when>
  <c:when test="${order.getStatus().getName()=='IN_QUEUE'}">
    <c:set var="orderStatus" value="Pending" />
  </c:when>
  <c:when test="${order.getStatus().getName()=='IN_PROSESS'}">
    <c:set var="orderStatus" value="Processing" />
  </c:when>
  <c:when test="${order.getStatus().getName()=='DONE'}">
    <c:set var="orderStatus" value="Success" />
  </c:when>
  <c:when test="${order.getStatus().getName()=='SYSTEM_CANCEL'}">
    <c:set var="orderStatus" value="Canceled by client" />
  </c:when>
  <c:when test="${order.getStatus().getName()=='USER_CANCEL'}">
    <c:set var="orderStatus" value="Canceled by client" />
  </c:when>
</c:choose>


<div>
  <div class="row-fluid">
    <div class="span2">
      <h4 class="text-info">Id:</h4>
      <h4>${order.getId()}</h4>
    </div>
    <div class="span4">
      <h4 class="text-info">Client:</h4>
      <h4> ${order.getUser().getFirstName()} ${order.getUser().getLastName()}</h4>
    </div>
    <div class="span6">
      <h4 class="text-info">Status:</h4>
      <h4> ${orderStatus}</h4>
    </div>
  </div>
  <table class="table table-bordered">
    <tr>
      <td>All Price:</td>
      <td>${order.getPrice()}$</td>
    </tr>
    <tr>
      <td>Computer price:</td>
      <td>${order.getComputer().getPrice()}</td>
    </tr>
    <tr>
      <td>Count:</td>
      <td>${order.getCountComputers()}</td>
    </tr>
    <tr>
      <td>Due date:</td>
      <td>${order.getDeadilne()}</td>
    </tr>
    <tr>
      <td>Quality:</td>
      <td>${order.computer.quality}</td>
    </tr>
    <tr>
      <td>Power:</td>
      <td>${order.computer.power}</td>
    </tr>
    <tr>
      <td>Propose:</td>
      <td>Uknown</td>
    </tr>
    <tr>
      <td>Computer components:</td>
      <td>
        <table class="table table-bordered">
          <c:set var="detailList" value="${order.getComputer().getDetailList()}"/>
          <c:forEach var="detail" items="${detailList}" >
            <tr>
              <td>${detail.getDetailType().getName()}</td>
              <td>${detail.getName()}</td>
            </tr>
          </c:forEach>
        </table>
      </td>
    </tr>
  </table>
</div>
