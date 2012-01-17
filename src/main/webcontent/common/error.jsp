<%@page contentType="text/html; charset=UTF-8" isErrorPage="true" import="scs.javax.lang.ExceptionBase,scs.javax.web.SessionTimeoutException,scs.georesults.logic.GeoMessageException,scs.georesults.common.szotar.GlobalSzotar"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <style type="text/css">
      .error {
        width: 97%;
      }
      PRE {
        font-size: 10pt;
        text-align: left;
      }
    </style>
  </head>
  <body>
    <div class="error">
<%
  Throwable t = (Throwable) request.getAttribute("error");
  if (t==null) {
    t = (Throwable) request.getSession().getAttribute("error");
    if (t!=null) request.getSession().removeAttribute("error");
  }
  if (t==null) t = exception;
  if (ExceptionBase.hasRootExceptionOf(t,java.sql.SQLException.class)) {
    out.println("Database error. Check the database-server! (Is it working, accessible, and is it well-configured?)");
    t.printStackTrace();
  } else if (ExceptionBase.hasRootExceptionOf(t,GeoMessageException.class)) {
    GeoMessageException root = (GeoMessageException) ExceptionBase.getRootExceptionOf(t,GeoMessageException.class);
    out.println(GlobalSzotar.resolve(pageContext,root.getKey()));
  } else if (ExceptionBase.hasRootExceptionOf(t,SessionTimeoutException.class)) {
    request.getSession().setAttribute("error",ExceptionBase.getRootExceptionOf(t,SessionTimeoutException.class));
    %><script type="text/javascript">
      window.opener.closePopup();
      window.opener.parent.location = '../init.do';
    </script><%
  } else {
    if (t != null) {
      out.println("<pre>");
      t.printStackTrace(new java.io.PrintWriter(out));
      out.println("</pre>");
    }
    else out.println("Unknown error.");
  }
%>
    </div>
  </body>
</html>
