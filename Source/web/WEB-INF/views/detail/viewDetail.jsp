<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
      <h1>
        ${detail.name}
      </h1>
      <ul class="nav nav-list well">
        <li>Price : ${detail.price}</li>
        <li>Power : ${detail.power}</li>
        <li>Quality : ${detail.quality}</li>
        <li>DetailType : ${detail.detailType.name}</li>
      </ul>
</div>