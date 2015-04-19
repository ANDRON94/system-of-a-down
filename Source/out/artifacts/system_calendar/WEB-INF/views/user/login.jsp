<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="pagination-centered">

	<h1>Login</h1>

	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>

	<form name='loginForm'
		  action="<c:url value='/j_spring_security_check'></c:url>" method='POST'>
		<center>
			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='email'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2' class="pagination-centered"><input name="submit" type="submit"
										   value="submit" class="btn btn-primary" /></td>
				</tr>
			</table>
		</center>
		<input type="hidden" name="${_csrf.parameterName}"
			   value="${_csrf.token}" />
	</form>
</div>
