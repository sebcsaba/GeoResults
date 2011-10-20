<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <div class="backBar">&lt;&lt; <c:link action="/verseny/buntetesTipusok.do?mode=back"><c:write key="BS_VISSZA"/></c:link></div>
      <geo:error/>
      <l:form name="buntetesTipus" action="/verseny/buntetesTipusok.do?mode=save" title="RA_BUNTETESTIPUS_ADATAI">
        <l:field title="BS_NEV">
          <f:label name="nev" value="${reszAdat.resz.nev}" maxlength="50"/>
        </l:field>
        <l:field title="BS_PONT">
          <f:integer name="pont" value="${reszAdat.resz.pont}"/>
        </l:field>
      </l:form>
    </div>
  </body>
</html>
