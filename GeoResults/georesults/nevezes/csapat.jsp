<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <div class="backBar">&lt;&lt; <c:link action="/nevezes/csapat.do?mode=back"><c:write key="BS_VISSZA"/></c:link></div>
      <geo:error/>
      <l:form name="csapatnevezesek" action="/nevezes/csapat.do?mode=save" title="RB_CSAPATNEVEZES_ADATAI">
        <l:field title="BS_NEV">
          <f:text name="nev" value="${reszAdat.resz.nev}" maxlength="250"/>
        </l:field>
        <l:field title="RB_RAJTSZAM">
          <f:select name="rajtszam1" value="${reszAdat.resz.rajtszam1}">
            <f:items items="${verseny.nevezesek}" labelPropertyName="rajtszamSorforNavigator" valuePropertyName="rajtszam"/>
          </f:select>
        </l:field>
        <l:field title="RB_RAJTSZAM">
          <f:select name="rajtszam2" value="${reszAdat.resz.rajtszam2}">
            <f:items items="${verseny.nevezesek}" labelPropertyName="rajtszamSorforNavigator" valuePropertyName="rajtszam"/>
          </f:select>
        </l:field>
        <l:field title="RB_RAJTSZAM">
          <f:select name="rajtszam3" value="${reszAdat.resz.rajtszam3}">
            <f:items items="${verseny.nevezesek}" labelPropertyName="rajtszamSorforNavigator" valuePropertyName="rajtszam"/>
          </f:select>
        </l:field>
        <l:field title="RB_RAJTSZAM">
          <f:select name="rajtszam4" value="${reszAdat.resz.rajtszam4}" optional="true">
            <f:items items="${verseny.nevezesek}" labelPropertyName="rajtszamSorforNavigator" valuePropertyName="rajtszam"/>
          </f:select>
        </l:field>
      </l:form>
    </div>
  </body>
</html>
