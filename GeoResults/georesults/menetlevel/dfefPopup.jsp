<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <script type="text/javascript" src="<c:path/>/common/checkUtils.js"></script>
    <style type="text/css">
      .panel {
        width: 284px;
      }
      .backBar {
        width: 274px;
      }
    </style>
  </head>
  <body onunload="window.opener.closePopup();">
    <f:form id="dfef" action="/menetlevel/darabPopup.do" onsubmit="this.parentNode.attributes['onunload'].nodeValue='';">
      <ml:loadEtapFeladat etapok="${verseny.etapok}" eid="${menetlevelAdatok.futam.eid}" listname="darabFuggoEtapFeladatok" idname="dfftid" var="dfef"/>
      <f:hidden name="dfftid" value="${dfef.dfftid}"/>
      <ml:darabInline ef="${dfef}" value="${menetlevelAdatok.futam}"/>
      <div class="backBar">
        <f:submit name="save" title="BS_MENTES" accesskey="s" popuptitle="Alt+S"/>
        <f:button name="back" title="BS_VISSZA" onclick="window.opener.closePopup();"/>
      </div>
    </f:form>
  </body>
</html>
