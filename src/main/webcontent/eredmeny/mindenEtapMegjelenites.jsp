<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="mindenEtapMegjelenito" scope="request" class="scs.georesults.logic.beans.eredmeny.MindenEtapMegjelenitoBean"/>
<jsp:setProperty name="mindenEtapMegjelenito" property="pageContext" value="${pageContext}"/>
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
      <div class="pageHeader"><r:header title="RB_MINDEN_ETAP_EREDMENY"/></div>
      <jsp:include page="content/mindenEtapEredmeny.jsp" flush="true">
        <jsp:param name="type" value="xhtml"/>
      </jsp:include>
      <div class="copyright">GeoResults - &copy; SebCsaba 2006</div>
    </div>
  </body>
</html>
