<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <div class="error">
        <c:write key="ER_LEZARAS_MIATT_NEM_HAJTHATO_VEGRE"/>
      </div>
    </div>
  </body>
</html>
