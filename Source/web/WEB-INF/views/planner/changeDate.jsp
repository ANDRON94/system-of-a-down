<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css"/>">
<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js"/>"></script>
<script type="text/javascript">$(function(){$('#datetimepicker').datetimepicker()});</script>
<div class="pagination-centered ">
  <form:form action="/planner/changeTodayDate" cssClass="span12" method="post">

      <div id="datetimepicker" class="input-append date ">

        <input type="text" name="dateToday" value="${date}"/>
                                <span class="add-on">
                                    <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                                </span>
      </div>
      <div>
          <input type="submit" class="btn btn-primary" value="ok">
      </div>
      <div>
          <p class="text-danger">${error}</p>
      </div>
  </form:form>
</div>
