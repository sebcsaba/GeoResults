<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="../common/header.jspf"%>
    <script type="text/javascript" src="<c:path/>/common/checkUtils.js"></script>
    <style type="text/css">
      .panel {
        width: 300px;
      }
      .backBar {
        width: 290px;
      }
    </style>
  </head>
  <body onunload="window.opener.closePopup();">
    <f:form id="buntetes" action="/menetlevel/buntetesPopup.do" onsubmit="this.parentNode.attributes['onunload'].nodeValue='';">
      <ml:buntetesInline value="${menetlevelAdatok.futam}"/>
      <%--geo:ellenorzopontlistak/--%>
      <div class="backBar">
        <f:submit name="save" title="BS_MENTES" accesskey="s" popuptitle="Alt+S"/>
        <f:button name="back" title="BS_VISSZA" onclick="window.opener.closePopup();"/>
      </div>
    </f:form>
  </body>
</html>
