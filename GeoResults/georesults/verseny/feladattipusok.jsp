<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <div class="backBar"><geo:popuplink action="/verseny/pontozasMegjelenites.jsp"><c:write key="FT_PONTOZAS_NYOMTATASA"/></geo:popuplink></div>
      <l:table title="RA_SORRENDFUGGO_FELADAT_TIPUSOK" action="/verseny/sorrendFuggoFeladatTipusok.do" items="${verseny.sorrendFuggoFeladatTipusok}" var="sfft" idfield="sfftid">
        <l:column title="BS_NEV" value="${sfft.nev}"/>
        <l:column title="RA_HIANY_HIBAPONT" value="${sfft.hianyHibapont}"/>
        <l:column title="RA_TOBBLET_HIBAPONT" value="${sfft.tobbletHibapont}"/>
      </l:table>
      <l:table title="RA_DARABFUGGO_FELADAT_TIPUSOK" action="/verseny/darabFuggoFeladatTipusok.do" items="${verseny.darabFuggoFeladatTipusok}" var="dfft" idfield="dfftid">
        <l:column title="BS_NEV" value="${dfft.nev}"/>
        <l:column title="RA_HIANY_HIBAPONT" value="${dfft.hianyHibapont}"/>
        <l:column title="RA_TOBBLET_HIBAPONT" value="${dfft.tobbletHibapont}"/>
      </l:table>
      <l:table title="RA_KESESI_ZONAK" action="/verseny/kesesiZonak.do" items="${verseny.kesesiZonak}" var="kz" idfield="idoLimit">
        <l:column title="RA_IDOLIMIT" value="${kz.idoLimit}"/>
        <l:column title="BS_PONT" value="${kz.pont}"/>
      </l:table>
      <l:table title="RA_BUNTETES_TIPUSOK" action="/verseny/buntetesTipusok.do" items="${verseny.buntetesTipusok}" var="bt" idfield="btid">
        <l:column title="BS_NEV" value="${bt.nev}"/>
        <l:column title="BS_PONT" value="${bt.pont}"/>
      </l:table>
      <l:table title="RB_SZLALOM_FELADATOK" action="/verseny/szlalomFeladatok.do" items="${verseny.szlalomFeladatok}" var="szf" idfield="szfid">
        <l:column title="BS_NEV" value="${szf.nev}"/>
        <l:column title="BS_PONT" value="${szf.pont}"/>
      </l:table>
    </div>
  </body>
</html>
