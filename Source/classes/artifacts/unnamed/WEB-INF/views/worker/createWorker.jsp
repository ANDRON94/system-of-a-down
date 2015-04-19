<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form:form method="post" action="/manager/saveWorker" commandName="workerForm" >
    <table>
        <tr>
            <td colspan="2">Ім’я робочого:</td>
        </tr>

        <tr>
            <td colspan="2"><form:input path="name" cssStyle="width: 100%"/></td>
        </tr>
        <tr>
            <td colspan="2">Прізвище робочого:</td>
        </tr>

        <tr>
            <td colspan="2"><form:input path="sename" cssStyle="width: 100%"/></td>
        </tr>
        <tr>
            <td colspan="2">Погодинна оплата робочого:</td>
        </tr>

        <tr>
            <td colspan="2"><form:input path="cash" cssStyle="width: 100%"/></td>
        </tr>
        <tr>
            <td colspan="2">Cпеціалізації:</td>
        </tr>
        <tr>
            <td>
              <form:select path="specializations" multiple="true" items="${specializationTypes}" itemLabel="name" itemValue="id"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" class="btn btn-primary pagination-centered" value="Сохранить" />
            </td>
        </tr>
    </table>
</form:form>