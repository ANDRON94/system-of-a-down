
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css"/>">
<script type="text/javascript"
        src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js"/>">
</script>
<script type="text/javascript">$(function(){$('#datetimepicker').datetimepicker()});</script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#id_price").keydown(function (e) {
            // Allow: backspace, delete, tab, escape, enter and .
            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1 ||
                        // Allow: Ctrl+A
                    (e.keyCode == 65 && e.ctrlKey === true) ||
                        // Allow: home, end, left, right, down, up
                    (e.keyCode >= 35 && e.keyCode <= 40)) {
                // let it happen, don't do anything
                return;
            }
            // Ensure that it is a number and stop the keypress
            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                e.preventDefault();
            }
        });

    });
</script>


<div  class="row-fluid pagination-centered">
    <h1>
        New Order
    </h1>
    <form:form id="myForm" method="POST" enctype="utf8" action="/user/newOrder" commandName="orderDTO">
        <div class="span4">
            <label for="price">Price: </label>
            <div class="input-prepend input-append">
                <span class="add-on">$</span>
                <form:input cssClass="input-medium"  id="id_price"  path="price"/>
                <span class="add-on">.00</span>
            </div>

            <label for="power">Power:</label>
            <div class="input-prepend input-append">
                <span role="button" onclick="  $('#PowerHelp').modal({
        keyboard: false

    })" class="add-on">  <i class="icon-book"></i></span>
                <form:select path="power" items="${estimations}"/>
            </div>



            <div class="modal hide fade" id="PowerHelp">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3>Power estimates</h3>
                </div>
                <div class="modal-body">
                    <ul class="nav nav-list">
                        <li><p><strong>1</strong> - DESCRIPTION</p></li>
                        <li><p><strong>2</strong> - DESCRIPTION</p></li>
                        <li><p><strong>3</strong> - DESCRIPTION</p></li>
                        <li><p><strong>4</strong> - DESCRIPTION</p></li>
                        <li><p><strong>5</strong> - DESCRIPTION</p></li>
                    </ul>
                </div>
            </div>


            <label for="quality">Quality:</label>
            <div class="input-prepend input-append">
                <span role="button" onclick="  $('#QualityHelp').modal({
                    keyboard: false
                        })" class="add-on">  <i class="icon-book"></i></span>
                <form:select path="quality" items="${estimations}"/>
            </div>


            <div class="modal hide fade" id="QualityHelp">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3>Quality estimates</h3>
                </div>
                <div class="modal-body">
                    <ul class="nav nav-list">
                        <li><p><strong>1</strong> - DESCRIPTION</p></li>
                        <li><p><strong>2</strong> - DESCRIPTION</p></li>
                        <li><p><strong>3</strong> - DESCRIPTION</p></li>
                        <li><p><strong>4</strong> - DESCRIPTION</p></li>
                        <li><p><strong>5</strong> - DESCRIPTION</p></li>
                    </ul>
                </div>
            </div>
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
<c:if test="${not empty errors}">
    <div class="alert alert-block alert-error fade in">
        <button type="button" class="close" data-dismiss="alert">×</button>
        <h4 class="alert-heading">${errors}</h4>
    </div>

</c:if>

