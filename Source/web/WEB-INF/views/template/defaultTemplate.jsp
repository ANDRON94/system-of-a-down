<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/bootstrap-responsive.css" />" rel="stylesheet">
  <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
  <script src="<c:url value="/resources/js/jquery.js" />"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Spring MVC - Tiles Integration tutorial</title>
  <link rel="stylesheet" href="resources/css/screen.css"
        type="text/css"/>
</head>
<body>
<div class="container" >
  <!-- Header -->
  <tiles:insertAttribute name="header" />

  <div class="row">
    <!-- Menu Page -->
    <div class="span2">
      <tiles:insertAttribute name="menu" />
    </div>
    <!-- Body Page -->
    <div class="span10">
      <tiles:insertAttribute name="body" />
    </div>
  </div>


  <!-- Footer Page -->
  <div class="span12">
  <tiles:insertAttribute name="footer" />
  </div>
</div>
</body>
</html>