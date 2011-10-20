<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <script type="text/javascript">
<%
  Object id = request.getAttribute("id");
  Object label = request.getAttribute("label");
%>
     function nemJoMezo()
      {
        alert('<c:write key="ER_NINCS_SZOTARNAK_MEGFELELO_MEZO_KIJELOLVE"/>');
        return false;
      }
    function initialize()
    {
      showInputId = window.parent.activeInputId;
      if (showInputId==null) return nemJoMezo();
      dictInputId = showInputId+"_dict";
      mainDoc = window.parent.frames['main'].document;
      showInput = mainDoc.getElementById(showInputId);
      dictInput = mainDoc.getElementById(dictInputId);
      if (showInput==null || dictInput==null) return nemJoMezo();
      showInput.value = '<%= label %>';
      if (showInput.onkeypress!=null) showInput.onkeypress();
      dictInput.value = '@<%= id %>';
      showInput.style.backgroundColor='whitesmoke';
      return true;
    }

    if (initialize()) {
      document.location = '<c:path/>/addons/szotar.jsp';
    }
    </script>
  </body>
</html>
