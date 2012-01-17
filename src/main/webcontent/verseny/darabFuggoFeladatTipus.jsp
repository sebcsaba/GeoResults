<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="../common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <div class="backBar">&lt;&lt; <c:link action="/verseny/darabFuggoFeladatTipusok.do?mode=back"><c:write key="BS_VISSZA"/></c:link></div>
      <geo:error/>
      <l:form name="darabFuggoFeladatTipusok" action="/verseny/darabFuggoFeladatTipusok.do?mode=save" title="RA_DARABFUGGO_FELADAT_TIPUS_ADATAI">
        <l:field title="BS_NEV">
          <f:label name="nev" value="${reszAdat.resz.nev}" maxlength="50"/>
        </l:field>
        <l:field title="RA_HIANY_HIBAPONT">
          <f:integer name="hianyHibapont" value="${reszAdat.resz.hianyHibapont}"/>
        </l:field>
        <l:field title="RA_TOBBLET_HIBAPONT">
          <f:integer name="tobbletHibapont" value="${reszAdat.resz.tobbletHibapont}"/>
        </l:field>
      </l:form>
    </div>
  </body>
</html>
