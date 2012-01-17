<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/title.css" />
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/megjelenites.css" />
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/megjelenitesTabla.css" />
  </head>
  <body>
    <%@include file="/common/eredmenyHeader.jspf"%>
    <div class="displayBlock">
      <div class="pageHeader"><r:header title="RB_NEVEZESI_LISTA"/></div>
      <jsp:include page="listaContent.jsp" flush="true">
        <jsp:param name="type" value="xhtml"/>
      </jsp:include>
      <div class="copyright">GeoResults - &copy; SebCsaba 2006</div>
    </div>
  </body>
</html>
