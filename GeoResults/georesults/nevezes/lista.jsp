<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <div class="backBar"><geo:popuplink action="/nevezes/listaMegjelenites.jsp"><c:write key="RB_NEVEZESI_LISTA"/></geo:popuplink></div>
    </div>
  </body>
</html>
