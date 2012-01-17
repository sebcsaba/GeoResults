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
        title="RB_SZLALOMOK"
        frissitesAction="/eredmeny/szlalomokFrissites.do"
        displayAction="/eredmeny/szlalomMegjelenites.jsp"
        items="${verseny.szlalomok}"
        displayProperty="nev"
        idProperty="szlid"
        frissitendoProperty="eredmenyFrissitendo"
        ertekelendoProperty="ertekelendo"/>
    </div>
  </body>
</html>
