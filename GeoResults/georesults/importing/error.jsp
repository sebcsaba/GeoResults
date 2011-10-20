<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<%@taglib uri="http://sebcsaba.hu/taglib/geo/importing" prefix="importing"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/importing.css" />
    <style type="text/css">
      .error {
        width: 566px;
      }
    </style>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <importing:wizardform>
        <c:write key="IF_MSG_HIBA"/>
        <geo:error/>
      </importing:wizardform>
    </div>
  </body>
</html>
