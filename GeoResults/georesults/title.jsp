<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <title>GeoResults 2006</title>
    <script type="text/javascript" src="<c:path/>/common/commonUtils.js"></script>
    <script type="text/javascript" src="<c:path/>/common/addonUtils.js"></script>
    <script type="text/javascript" src="<c:path/>/common/popupUtils.js"></script>
    <script type="text/javascript" src="<c:path/>/common/prototype.js"></script>
    <link rel="stylesheet" type="text/css" href="style/base.css">
    <link rel="stylesheet" type="text/css" href="style/title.css">
    <style type="text/css">
      BODY {
        background: aliceblue;
      }
    </style>
    <script type="text/javascript">
      function kezdolap()
      {
        window.parent.frames['main'].location = 'main.jsp';
      }
      function updateBackupTitle(bk,mode)
      {
        new Ajax.Request('<c:path/>/kozos/doBackupInfo?mode='+mode,{
          method: 'get',
          onSuccess: function(transport) {
            bk.title = transport.responseText;
          },
          onFailure: function(transport) {
            bk.title = '<c:write key="ER_BACKUP"/>';
          }
        });
      }
    </script>
  </head>
  <body>
    <div class="title"><img class="title" src="<c:path/>/style/logo2.png" alt="GeoResults2006 logo" onclick="kezdolap();"/></div>
    <div class="addons">
      <div class="addonButton" id="charmap" onclick="versenyValasztas('${pageContext.request.contextPath}');"><c:write key="RB_VERSENY_KIVALASZTASA"/></div>
      <div class="addonButton" id="charmap" onclick="updateBackupTitle(this,'action');" onmouseover="updateBackupTitle(this,'info');"><c:write key="FT_BACKUP"/></div>
    </div>
    <div class="addons">
      <div class="addonButton" id="charmap" onclick="openAddon('addons/charmap.jsp');"><c:write key="FT_KARAKTERTABLA"/></div>
      <div class="addonButton" id="szotar" onclick="openAddon('addons/szotar.jsp');"><c:write key="DC_SZOTAR"/></div>
    </div>
  </body>
</html>
