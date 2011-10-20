<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <div class="backBar">&lt;&lt; <c:link action="/verseny/autoTipusok.do?mode=back"><c:write key="BS_VISSZA"/></c:link></div>
      <geo:error/>
      <l:form name="autoTipus" action="/verseny/autoTipusok.do?mode=save" title="RA_AUTOTIPUS_ADATAI">
        <l:field title="BS_NEV">
          <f:text name="nev" value="${reszAdat.resz.nev}" maxlength="50"/>
        </l:field>
        <l:field title="RA_KATEGORIA">
          <f:select name="kategoria" value="${reszAdat.resz.kategoria}">
            <f:items items="${verseny.szlalomKategoriak}" labelPropertyName="nev" valuePropertyName="szkid"/>
          </f:select>
        </l:field>
      </l:form>
    </div>
  </body>
</html>
