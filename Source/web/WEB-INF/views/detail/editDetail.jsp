<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<form:form method="post" action="/user/editRecord/${detailForm.iddetail}" commandName="detailForm" >
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
            <td colspan="2"><form:input path="name" cssStyle="width: 100%"/></td>
        </tr>
        <tr>
            <td colspan="2">Якість деталі:</td>
        </tr>

        <tr>
            <td colspan="2"><form:input path="name" cssStyle="width: 100%"/></td>
        </tr>
        <tr>
            <td colspan="2">Потужність деталі:</td>
        </tr>
        <tr>
            <td colspan="2"><form:input path="name" cssStyle="width: 100%"/></td>
        </tr>
        <tr>
            <td colspan="2">Тип деталі:</td>
        </tr>
        <tr>
            <td colspan="2"><form:input path="name" cssStyle="width: 100%"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" class="pagination-centered" value="Сохранить" />
            </td>
        </tr>
    </table>
    </form:form>
