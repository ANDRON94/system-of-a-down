<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form:form method="post" action="/manager/saveDetail" commandName="detailForm" >
    <table>
        <tr>
            <td colspan="2">Назва деталі:</td>
        </tr>
        <tr>
            <td colspan="2"><form:input path="name" cssStyle="width: 100%"/></td>
        </tr>
        <tr>
            <td colspan="2">Ціна деталі:</td>
        </tr>
        <tr>
            <td colspan="2"><form:input path="price" cssStyle="width: 100%"/></td>
        </tr>
        <tr>
            <td colspan="2">Якість деталі:</td>
        </tr>

        <tr>
            <td colspan="2"><form:input path="quality" cssStyle="width: 100%"/></td>
        </tr>
        <tr>
            <td colspan="2">Потужність деталі:</td>
        </tr>
        <tr>
            <td colspan="2"><form:input path="power" cssStyle="width: 100%"/></td>
        </tr>
        <tr>
            <td colspan="2">Тип деталі:</td>
        </tr>
        <tr>
            <td colspan="2">
                <form:select path="detailType.id" items="${detailTypes}">
                </form:select> </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" class="btn btn-primary pagination-centered" value="Сохранить" />
            </td>
        </tr>
    </table>
</form:form>