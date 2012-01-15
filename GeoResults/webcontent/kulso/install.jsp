<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="installBean" scope="request" class="scs.georesults.logic.beans.kulso.InstallBean"/>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/outer.css" />
    <style type="text/css">
      #welcome {
        margin-top: 150px;
        height: 20px;
        font-size: 12pt;
        padding: 8px;
        width: 584px;
      }
      .info {
        font-size: 13pt;
        padding: 8px;
        text-align: center;
      }
      .warning {
        width: 574px;
      }
      .panel {
        margin-left: auto;
        margin-right: auto;
      }
    </style>
  </head>
  <body>
    <div id="welcome" class="panel">Welcome to the Install Wizard of the GeoResult 2006</div>
    <div class="panel">
      <c:if test="${!installBean.installSuccessful}">
        <div class="info">Initialize database</div>
        <c:if test="${installBean.ugynezkiTelepitveVan}">
          <div class="warning">The database probably have been initialised yet. If you initialize it again, the data currently contained in the database will be lost.</div>
        </c:if>
        <c:if test="${!installBean.ugynezkiTelepitveVan && installBean.vannakTablak}">
          <div class="warning">The initialization is necessary, but there are some data currently in the database. Start the initialization only if you're sure that the current data can be deleted.</div>
        </c:if>
        <div class="info">
          <c:link action="/kulso/install.do">Start the initializing process</c:link>
        </div>
      </c:if>
      <c:if test="${installBean.installSuccessful}">
        <div class="info">The initialization finished succesfully.</div>
        <div class="info">
          <c:link action="/">Go to the frontpage</c:link>
        </div>
      </c:if>
    </div>
    <div id="footer">
      <div class="copyright">GeoResults &copy; SebCsaba 2006 - <geo:version/></div>
    </div>
  </body>
</html>
