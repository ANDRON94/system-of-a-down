
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<link rel="stylesheet" type="text/css" media="screen" href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
<script type="text/javascript"  src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">$(function(){$('#datetimepicker').datetimepicker()});</script>
<div class="pagination-centered">
    <h1>
        New Order
    </h1>
    <form:form method="POST" enctype="utf8" commandName="orderDTO">
        <div class="span4">
            <label for="id_prise">Prise: </label>
            <div class="input-prepend input-append">
                <span class="add-on">$</span>
                <form:input class="input-medium" id="id_prise" type="text" path="price"/>
                <span class="add-on">.00</span>
            </div>
            <label for="id_power">Power:</label>
            <select id="id_power">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
            <label for="id_qa">Quality:</label>
            <select id="id_qa">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
        </div>
        <div class="span4">
            <label for="id_cpu">CPU: </label>
            <select id="id_cpu">
                <option selected="selected" value="0">None</option>
                <option>1</option>
                <option>2</option>
                <option>4</option>
                <option>8</option>
                <option>16</option>
            </select>
            <label for="id_ram">RAM:</label>
            <select id="id_ran">
                <option selected="selected" value="0">None</option>
                <option>1</option>
                <option>2</option>
                <option>4</option>
                <option>8</option>
                <option>16</option>
            </select>
            <label for="id_gpu">GPU:</label>
            <select id="id_gpu">
                <option selected="selected" value="0">None</option>
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
            <div class="row span12" style="margin-top: 20px; padding-left: 50px;"><button class="btn btn-primary" type="submit">Make order</button></div>
        </div>
        <div class="span4">
            <label for="id_hdd">HDD: </label>
            <select id="id_hdd">
                <option selected="selected" value="0">None</option>
                <option>1</option>
                <option>2</option>
                <option>4</option>
                <option>8</option>
                <option>16</option>
            </select>
            <label for="id_MB">Motherbord:</label>
            <select id="id_ran">
                <option selected="selected" value="0">None</option>
                <option>1</option>
                <option>2</option>
                <option>4</option>
                <option>8</option>
                <option>16</option>
            </select>
            <label for="id_date">Final date: </label>
            <div id="datetimepicker" class="input-append date">
                <form:input path="" data-format="dd/MM/yyyy" id="id_date"/>
                                <span class="add-on">
                                    <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                                </span>
            </div>
        </div>
    </form:form>

</div>

