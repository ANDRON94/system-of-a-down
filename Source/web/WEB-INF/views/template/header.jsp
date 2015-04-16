<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<header>
  <nav class="navbar navbar-inverse">
    <div class="navbar-inner">
      <div class="pull-left">
         <h2 class="text-info">Calendar System</h2>
      </div>
      <ul class="nav pull-right">
        <sec:authorize access="isAuthenticated()">
          <!-- For login user -->
          <c:url value="/j_spring_security_logout" var="logoutUrl" />
          <form style=" margin: 0 0 0 0;" action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
          </form>
          <script>
            function formSubmit() {
              document.getElementById("logoutForm").submit();
            }
          </script>
              <li>
                  <a href="javascript:formSubmit()">
                    <h4>
                    Logout
                    </h4>
                  </a>

              </li>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
          <li>

              <a  href="/login">
                <h4>
                Login
               </h4>
              </a>

          </li>
          <li>

             <a href="/registration">
               <h4>
               Registration
               </h4>
             </a>

          </li>
        </sec:authorize>


      </ul>
    </div>
  </nav>
</header>