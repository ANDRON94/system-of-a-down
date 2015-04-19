<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 29.03.15
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.dhtmlx.planner.*,com.dhtmlx.planner.data.*"  %>
<%@ page import="com.dhtmlx.planner.controls.DHXTimelineView" %>
<%@ page import="com.dhtmlx.planner.controls.DHXTimelineUnit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <div class="planner" id="planner"><%=getPlanner(request)%></div>

  <%!
    String getPlanner(HttpServletRequest request) throws Exception{
      DHXPlanner s = new DHXPlanner("/resource/codebase/",DHXSkin.TERRACE);
      s.setWidth(1100);
      s.setInitialDate(2013, 0, 21);
      s.load("events.jsp", DHXDataFormat.JSON);
      s.views.clear();
      DHXTimelineView view = new DHXTimelineView( "event_type", "Type");
      view.setRenderMode(DHXTimelineView.RenderModes.CELL);
      view.setXScaleUnit(DHXTimelineView.XScaleUnits.MINUTE);
      view.setXStep(30);
      view.setXSize(24);// (8PM - 8AM)/30min
      view.setXLength(30);
      view.setServerList("event_type");
      s.views.add(view);
      for(int i= 0; i<15;i++){
        view.addOption(new DHXTimelineUnit(i, "Worker "+i));
      }
      s.setInitialView("event_type");
      return s.render();
    }
  %>
  </body>
</html>
