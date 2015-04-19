<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 29.03.15
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/json"%>
<%= getEvents(request)%>
        <%@ page import="com.dhtmlx.planner.*"%>
<%@ page import="com.EventsManager"%>
<%!
String getEvents(HttpServletRequest request) throws Exception{
EventsManager eventsManager = new EventsManager(request);
return eventsManager.run();
}
%>
