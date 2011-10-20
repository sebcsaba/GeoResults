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
        <c:write key="IF_MSG_VERSENY_FIGYELMEZTETES"/>
        <f:form action="/importing/accept.do">
          <div class="formRow">
            <f:submit name="ok" title="BS_OK"/>
          </div>
        </f:form>
      </importing:wizardform>
    </div>
  </body>
</html>
