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
      <importing:wizardform percent="0">
        <c:write key="IF_MSG_INTRO"/>
        <c:write key="IF_MSG_ABORT"/>
        <c:write key="IF_MSG_START"/>
        <f:form action="/importing/start.do">
          <div class="formRow">
            <f:submit name="start" title="IF_START"/>
          </div>
        </f:form>
      </importing:wizardform>
    </div>
  </body>
</html>
