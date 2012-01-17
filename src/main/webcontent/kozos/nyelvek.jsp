<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/crossTable.css" />
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <div class="backBar">&gt;&gt; <c:link action="/kozos/ujNyelv.jsp"><c:write key="DC_UJ_NYELV"/></c:link></div>
      <geo:crossTable title="DC_NYELVEK" keys="${formUtils.nyelvKodok}" action="/kozos/nyelv.do?mode=edit"/>
    </div>
  </body>
</html>
