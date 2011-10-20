<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<%@taglib uri="http://sebcsaba.hu/taglib/geo/importing" prefix="importing"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/importing.css" />
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <importing:wizardform percent="${importBean.stepsPercent}">
        <c:write key="IF_MSG_IMPORT_CATEGORY"/>
        <f:form action="/importing/categories.do">
          <div class="formRow">
            <f:radiogroup name="category" value="">
              <f:item value="verseny" title="IF_TELJES_VERSENY" disabled="${!importBean.sourceSubbean.teljesVersenyImportalhato}"/>
              <f:item value="alapok" title="IF_ALAPOK" disabled="${!importBean.sourceSubbean.alapImportalhato}"/>
              <f:item value="nevezesek" title="RB_NEVEZESEK" disabled="${!importBean.sourceSubbean.nevezesImportalhato}"/>
              <f:item value="etapok" title="RB_ETAPOK" disabled="${!importBean.sourceSubbean.etapImportalhato}"/>
              <f:item value="szlalomFutamok" title="RB_SZLALOM_FUTAMOK" disabled="${!importBean.sourceSubbean.szlalomFutamImportalhato}"/>
              <f:item value="menetlevelek" title="RB_MENETLEVELEK" disabled="${!importBean.sourceSubbean.menetlevelImportalhato}"/>
              <f:item value="forditas" title="DC_EGY_ADOTT_NYELVU_FORDITAS" disabled="${!importBean.sourceSubbean.forditasImportalhato}"/>
            </f:radiogroup>
          </div>
          <div class="formRow">
            <f:submit name="ok" title="BS_OK"/>
          </div>
        </f:form>
      </importing:wizardform>
    </div>
  </body>
</html>
