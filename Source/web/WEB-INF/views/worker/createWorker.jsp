<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready(function() {
        $("#id_price").keydown(function (e) {
            // Allow: backspace, delete, tab, escape, enter and .
            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1 ||
                        // Allow: Ctrl+A
                    (e.keyCode == 65 && e.ctrlKey === true) ||
                        // Allow: home, end, left, right, down, up
                    (e.keyCode >= 35 && e.keyCode <= 40)) {
                // let it happen, don't do anything
                return;
            }
            // Ensure that it is a number and stop the keypress
            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                e.preventDefault();
            }
        });

    });
</script>
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
    <c:if test="${not empty error}">
        <div class="alert alert-block alert-error fade in">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <h4 class="alert-heading">${error}</h4>
        </div>

    </c:if>
</form:form>