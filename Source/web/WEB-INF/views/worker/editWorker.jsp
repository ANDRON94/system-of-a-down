<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<form:form method="post" action="/user/editRecord/${recordForm.idrecord}" commandName="recordForm" >
        <table>
            <tr>
                <td colspan="2">Назва запису:</td>
            </tr>
            <tr>
                <td colspan="2"><form:input path="title" cssStyle="width: 100%"/></td>
            </tr>
            <tr>
                <td colspan="2">Запис:</td>
            </tr>
            <tr>
                <td  colspan="2">
                    <form:textarea path="data" maxlength="5000" /><ckeditor:replace replace="data" basePath="/resources/js/ckeditor/" />
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" class="pagination-centered" value="Сохранить" />
                </td>
            </tr>
        </table>
    </form:form>

