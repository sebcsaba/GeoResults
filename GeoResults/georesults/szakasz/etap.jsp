<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <div class="backBar">&lt;&lt; <c:link action="/szakasz/etapok.do?mode=back"><c:write key="BS_VISSZA"/></c:link></div>
      <geo:error/>
      <l:form name="etapok" action="/szakasz/etapok.do?mode=save" title="RB_ETAP_ADATAI">
        <l:field title="BS_NEV">
          <f:label name="nev" value="${reszAdat.resz.nev}" maxlength="250"/>
        </l:field>
      </l:form>
    </div>
  </body>
</html>
