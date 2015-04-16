<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form method="post" action="/user/saveWorker" commandName="workerForm" >
    <table>
        <tr>
            <td colspan="2">Ім’я робочого:</td>
        </tr>

        <tr>
            <td colspan="2"><form:input path="name" cssStyle="width: 100%"/></td>
        </tr>
        <tr>
            <td colspan="2">Список спеціалізацій:</td>
        </tr>
        <tr>
            <td colspan="2">
                <a class="btn ">
                <i class="  icon-plus"></i>
            </a>

                <a class="btn ">
                    <i class="icon-minus"></i>
                </a>
            </td>
        </tr>

        <tr>
            <td>Деталь: <form:input path="name" cssStyle="width: 100%"/></td>
            <td colspan="2">Час обробки:  <form:input path="name" cssStyle="width: 100%"/></td>
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