<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <script type="text/javascript">
<%
  String id = request.getParameter("id");
  if (id!=null) {
%>
      elem = window.opener.document.getElementById('<%=id%>');
      window.opener.doChangeSimple(elem,null,'<c:write key="BS_MODOSITVA"/>');
<%
  }
%>
      window.opener.closePopup();
    </script>
  </body>
</html>
