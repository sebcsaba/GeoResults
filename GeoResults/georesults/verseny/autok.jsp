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
      <l:table title="RB_SZLALOM_KATEGORIAK" action="/verseny/szlalomKategoriak.do" items="${verseny.szlalomKategoriak}" var="szk" idfield="szkid">
        <l:column title="BS_NEV" value="${szk.nev}"/>
      </l:table>
      <l:table title="RA_AUTOTIPUSOK" action="/verseny/autoTipusok.do" items="${verseny.autoTipusok}" var="at" idfield="atid">
        <l:column title="BS_NEV" value="${at.nev}"/>
        <l:column title="RA_KATEGORIA" value="${at.szlalomKategoria.nev}"/>
      </l:table>
    </div>
  </body>
</html>
