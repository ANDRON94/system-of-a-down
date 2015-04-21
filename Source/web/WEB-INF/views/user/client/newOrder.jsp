
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css"/>">
<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js"/>"></script>
<script type="text/javascript">$(function(){$('#datetimepicker').datetimepicker()});</script>
<div class="pagination-centered">
    <h1>
        New Order
    </h1>
    <form:form method="POST" enctype="utf8" action="/user/newOrder" commandName="orderDTO">
        <div class="span4">
            <label for="price">Price: </label>
            <div class="input-prepend input-append">
                <span class="add-on">$</span>
                <form:input cssClass="input-medium" id="id_price"  path="price"/>
                <span class="add-on">.00</span>
            </div>
            <label for="power">Power:</label>
                <form:select path="power" items="${estimations}"/>
            <label for="quality">Quality:</label>
                <form:select path="quality" items="${estimations}"/>

        </div>
        <div class="span4">
            <label for="cpuCount">CPU: </label>
                <form:select path="cpuCount" items="${countsDetail}"/>
            <label for="ramCount">RAM:</label>
                <form:select path="ramCount" items="${countsDetail}"/>
            <label for="gpuCount">GPU:</label>
                <form:select path="gpuCount" items="${countsDetail}"/>
            <div class="pagination-centered">
                <button class="btn btn-primary" type="submit">Make order</button>
            </div>
        </div>
        <div class="span4">
            <label for="hddCount">HDD: </label>
            <form:select path="hddCount" items="${countsDetail}">
            </form:select>
            <label for="mbCount">Motherbord:</label>
            <form:select path="mbCount" items="${countsDetail}">
            </form:select>
            <label for="id_date">Final date: </label>
            <div id="datetimepicker" class="input-append date">

                <form:input path="deadilne"  id="id_date"/>
                                <span class="add-on">
                                    <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                                </span>
            </div>
        </div>
    </form:form>

</div>

