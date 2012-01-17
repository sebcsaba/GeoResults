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
        title="RB_SZAKASZOK"
        frissitesAction="/eredmeny/szakaszokFrissites.do"
        displayAction="/eredmeny/szakaszMegjelenites.jsp"
        items="${verseny.szakaszok}"
        displayProperty="nev"
        idProperty="szid"
        frissitendoProperty="eredmenyFrissitendo"
        ertekelendoProperty="ertekelendo"/>
      <r:table
        title="RB_SZLALOM_OSSZESITETT"
        frissitesAction="/eredmeny/szakaszokFrissites.do"
        displayAction="/eredmeny/szlalomOsszesitettMegjelenites.jsp"
        items="${verseny.szakaszok}"
        displayProperty="nev"
        idProperty="szid"
        frissitendoProperty="eredmenyFrissitendo"
        ertekelendoProperty="ertekelendo"/>
    </div>
  </body>
</html>
