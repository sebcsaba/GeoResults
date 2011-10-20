<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp" import="scs.georesults.view.*"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="addonCharmap" scope="session" class="scs.georesults.logic.beans.addons.CharmapBean"/>
<jsp:setProperty name="addonCharmap" property="pageContext" value="${pageContext}"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>addonCharmap</title>
    <style type="text/css">
      BODY {
        background: azure;
      }
      .paneSelect {
        border: 1px solid royalblue;
        padding: 8px;
        float: right;
      }
      .small {
        text-align: right;
        font-size: 9pt;
      }
      .charTable {
        float: left;
        background: azure;
      }
      div.cell {
        border: 1px solid royalblue;
        text-align: center;
      }
      .charCell {
        width: 30px;
        height: 30px;
        font-size: 12pt;
        color: royalblue;
      }
      #pane {
        width: 50px;
        height: 50px;
        left: 0px;
        top: 0px;
        display: none;
        position: absolute;
        font-size: 24pt;
        background: #d0d0ff;
      }
      FORM {
        margin: 0px;
      }
    </style>
    <script type="text/javascript" src="../common/styleUtils.js"></script>
    <script type="text/javascript">
      function showPane(div) {
        pane = document.getElementById('pane');
        pane.style.left = findPosX(div)-10 + "px";
        pane.style.top = findPosY(div)-10 + "px";
        pane.style.display = 'block';
        pane.title = div.title;
        pane.innerHTML = div.innerHTML;
        pane.onclick = div.onclick;
      }

      function hidePane(pane) {
        pane.style.display = 'none';
      }

      function pushback(ch) {
        mainDoc = window.parent.frames['main'].document;
        inputId = window.parent.activeInputId;
        input = mainDoc.getElementById(inputId);
        if (input!=null) {
          input.value = input.value+ch;
          if (input.onkeypress!=null) input.onkeypress();
        }
      }

      function doUnFocus()
      {}
    </script>
  </head>
  <body>
    <div class="paneSelect">
      <f:form id="addonCharmap" action="/addons/charmap.jsp" method="get">
        <f:select name="page" value="${addonCharmap.page}" onchange="this.form.submit();">
          <f:items items="${addonCharmap.charMaps}" labelPropertyName="name" valuePropertyName="first"/>
        </f:select>
      </f:form>
      <div class="small">Based on The Unicode Standard 4.0</div>
    </div>
    <div class="charTable">
      <geo:charmap bean="${addonCharmap}"/>
    </div>
    <div id="pane" class="cell" onmouseout="hidePane(this);"></div>
  </body>
</html>
<c:expression bean="${addonCharmap}" method="releasePageContext"/>
