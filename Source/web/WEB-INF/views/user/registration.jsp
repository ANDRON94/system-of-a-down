
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<div class="pagination-centered">
    <h1>
        Registration
    </h1>
    <form:form modelAttribute="user" method="POST" enctype="utf8">
        <center>
            <table >
                <tr>
                    <td><label>First name:</label></td>
                    <td><form:input path="firstName" value="" /></td>
                    <form:errors path="firstName" element="div"/>
                </tr>
                <tr>
                    <td><label>Last name
                    </label>
                    </td>
                    <td><form:input path="lastName" value="" /></td>
                    <form:errors path="lastName" element="div" />
                </tr>
                <tr>
                    <td><label>Email:
                    </label>
                    </td>
                    <td><form:input path="email" value="" /></td>
                    <form:errors path="email" element="div" />
                </tr>
                <tr>
                    <td><label>Password:
                    </label>
                    </td>
                    <td><form:input path="password" value="" type="password" /></td>
                    <form:errors path="password" element="div" />
                </tr>
                <tr>
                    <td colspan="2" class="pagination-centered"> <input type="submit" class="btn btn-primary" value="Submit"/></td>
                </tr>
            </table>

        </center>



    </form:form>
</div>

