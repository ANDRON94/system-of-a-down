<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<li>
  <form:select path="workerForm.specializations[${index}].id" items="${specializationTypes}">
</form:select>
</li>
