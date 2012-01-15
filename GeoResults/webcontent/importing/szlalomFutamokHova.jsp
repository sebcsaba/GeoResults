<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<%@taglib uri="http://sebcsaba.hu/taglib/geo/importing" prefix="importing"%>
<jsp:useBean id="importFormUtils" scope="request" class="scs.georesults.logic.utils.ImportFormUtils"/>
<jsp:setProperty name="importFormUtils" property="pageContext" value="${pageContext}"/>
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
        <c:write key="IF_MSG_SZLALOM_FUTAMOK_HOVA"/>
        <f:form action="/importing/szlalomFutamokHova.do">
          <div class="formRow">
            <f:radiogroup name="hova" value="">
              <f:items items="${importFormUtils.szlalomok}" labelPropertyName="label" valuePropertyName="value"/>
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
