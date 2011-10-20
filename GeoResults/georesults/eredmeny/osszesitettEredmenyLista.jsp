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
      <l:simpleform title="RB_OSSZESITETT_EREDMENYEK">
        <r:osszesitoTablaSor title="RB_MINDEN_ETAP_EREDMENY" frissitendo="${verseny.eredmenyFrissitendoMindenEtap}" frissitesAction="/eredmeny/mindenEtapFrissites.do" megjelenitesAction="/eredmeny/mindenEtapMegjelenites.jsp"/>
        <r:osszesitoTablaSor title="RB_MINDEN_SZLALOM_EREDMENY" frissitendo="${verseny.eredmenyFrissitendoMindenSzlalom}" frissitesAction="/eredmeny/mindenSzlalomFrissites.do" megjelenitesAction="/eredmeny/mindenSzlalomMegjelenites.jsp"/>
        <r:osszesitoTablaSor title="RB_VEGEREDMENY" frissitendo="${verseny.eredmenyFrissitendoVerseny}" frissitesAction="/eredmeny/versenyFrissites.do" megjelenitesAction="/eredmeny/vegeredmenyMegjelenites.jsp"/>
        <r:osszesitoTablaSor title="RB_CSAPAT_EREDMENY" frissitendo="${verseny.eredmenyFrissitendoCsapat}" frissitesAction="/eredmeny/csapatFrissites.do" megjelenitesAction="/eredmeny/csapatMegjelenites.jsp"/>
      </l:simpleform>
    </div>
  </body>
</html>
