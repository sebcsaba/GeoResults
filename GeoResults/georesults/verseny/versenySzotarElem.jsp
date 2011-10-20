<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <div class="backBar">&lt;&lt; <c:link action="/verseny/versenySzoForditasSzerkesztes.do?mode=back"><c:write key="BS_VISSZA"/></c:link></div>
      <geo:error/>
      <l:form name="vszb" action="/verseny/versenySzoForditasSzerkesztes.do?mode=save" title="DC_VERSENY_SZOTAR_BEJEGYZES_ADATAI">
        <l:field title="BS_NYELV">
          <f:text name="nyelv" value="${versenySzoForditas.nyelvNeve}" disabled="true"/>
        </l:field>
        <c:if test="${versenySzoForditas.lang!=nyelv.lang}">
          <l:field title="DC_SZO_AZ_AKTUALIS_NYELVEN">
            <f:text name="szoAzAktualisNyelven" value="${versenySzoForditas.szoAzAktualisNyelven}" disabled="true"/>
          </l:field>
        </c:if>
        <c:if test="${versenySzoForditas.lang!=verseny.alapNyelv}">
          <l:field title="DC_SZO_AZ_ALAP_NYELVEN">
            <f:text name="szoAzAlapNyelven" value="${versenySzoForditas.szoAzAlapNyelven}" disabled="true"/>
          </l:field>
        </c:if>
        <l:field title="DC_SZO">
          <f:text name="felirat" value="${versenySzoForditas.szo}"/>
        </l:field>
        <c:if test="${versenySzoForditas.lang!=verseny.alapNyelv and !versenySzoForditas.create}">
          <l:field>
            <f:submit name="delete" title="BS_TORLES"/>
          </l:field>
        </c:if>
      </l:form>
    </div>
  </body>
</html>
