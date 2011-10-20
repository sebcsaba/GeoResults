<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <div class="backBar">&lt;&lt; <c:link action="/verseny/kesesiZonak.do?mode=back"><c:write key="BS_VISSZA"/></c:link></div>
      <geo:error/>
      <l:form name="kesesiZona" action="/verseny/kesesiZonak.do?mode=save" title="RA_KESESI_ZONA_ADATAI">
        <l:field title="RA_IDOLIMIT">
          <f:integer name="idoLimit" value="${reszAdat.resz.idoLimit}" disabled="${!reszAdat.create}"/>
        </l:field>
        <l:field title="BS_PONT">
          <f:integer name="pont" value="${reszAdat.resz.pont}"/>
        </l:field>
      </l:form>
    </div>
  </body>
</html>
