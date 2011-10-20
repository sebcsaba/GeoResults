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
      <r:table
        title="RB_ETAPOK"
        frissitesAction="/eredmeny/etapokFrissites.do"
        displayAction="/eredmeny/etapMegjelenites.jsp"
        items="${verseny.etapok}"
        displayProperty="nev"
        idProperty="eid"
        frissitendoProperty="eredmenyFrissitendo"
        ertekelendoProperty="ertekelendo"/>
    </div>
  </body>
</html>
