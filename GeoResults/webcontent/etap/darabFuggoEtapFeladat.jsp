<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error />
      <div class="backBar">&lt;&lt; <c:link action="/etap/darabFuggoEtapFeladatok.do?mode=back"><c:write key="BS_VISSZA"/></c:link></div>
      <l:form name="darabfuggoetapfeladat" action="/etap/darabFuggoEtapFeladatok.do?mode=save" title="RA_ETAPFELADAT_ADATAI">
        <l:field title="BS_FELADAT">
          <f:select name="dfftid" value="${reszAdat.resz.dfftid}" disabled="${!reszAdat.create}">
            <f:items items="${verseny.darabFuggoFeladatTipusok}" labelPropertyName="nev" valuePropertyName="dfftid"/>
          </f:select>
        </l:field>
        <l:tablefield title="BS_BEJEGYZESEK">
          <tf:inputtable count="${reszAdat.darab}" items="${reszAdat.resz.bejegyzesek}" default="${reszAdat.defBejegyzes}">
            <tf:inputcolumn title="BS_CIMKE" styleClass="inputCell">
              <tf:text name="cimke" property="cimke"/>
            </tf:inputcolumn>
            <tf:inputcolumn title="BS_DARAB" styleClass="inputCell">
              <tf:integer name="darab" property="darab"/>
            </tf:inputcolumn>
          </tf:inputtable>
          <div class="enlargeBox">
            <f:submit name="enlarge" title="BS_MEGNOVEL"/>
          </div>
        </l:tablefield>
      </l:form>
    </div>
  </body>
</html>
