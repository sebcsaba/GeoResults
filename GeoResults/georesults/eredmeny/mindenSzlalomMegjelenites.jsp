<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="mindenSzlalomMegjelenito" scope="request" class="scs.georesults.logic.beans.eredmeny.MindenSzlalomMegjelenitoBean"/>
<jsp:setProperty name="mindenSzlalomMegjelenito" property="pageContext" value="${pageContext}"/>
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
      <div class="pageHeader"><r:header title="RB_MINDEN_SZLALOM_EREDMENY"/></div>
      <c:foreach items="${mindenSzlalomMegjelenito.eredmenyek}" var="kategoriaEredmeny">
        <div class="katHeader"><c:write key="RA_KATEGORIA"/>: <c:write label="${kategoriaEredmeny.kategoria.nev}"/></div>
        <jsp:include page="content/mindenSzlalomEredmeny.jsp" flush="true">
          <jsp:param name="type" value="xhtml"/>
        </jsp:include>
      </c:foreach>
      <div class="copyright">GeoResults - &copy; SebCsaba 2006</div>
    </div>
  </body>
</html>

