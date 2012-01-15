<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<%@taglib uri="http://sebcsaba.hu/taglib/form" prefix="form"%>
<jsp:useBean id="configBean" scope="request" class="scs.georesults.logic.beans.kulso.ConfigBean"/>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/outer.css" />
    <style type="text/css">
      .panel {
        margin-left: auto;
        margin-right: auto;
      }
      #welcome {
        margin-top: 150px;
        height: 20px;
        font-size: 12pt;
        padding: 8px;
        width: 584px;
      }
      #errorBox {
        width: 600px;
        margin-left: auto;
        margin-right: auto;
      }
     .error {
       margin: -1px;
     }
    </style>
  </head>
  <body>
    <div id="welcome" class="panel">Welcome to the Install Wizard of the GeoResult 2006</div>
    <div id="errorBox"><geo:error/></div>
    <f:form id="config" action="/kulso/config.do">
      <l:simpleform caption="Database connection and environment parameters">
        <l:field caption="Hostname">
          <form:text name="hostname" value="${configBean.hostname}"/>
        </l:field>
        <l:field caption="Database name">
          <form:text name="database" value="${configBean.database}"/>
        </l:field>
        <l:field caption="Username">
          <form:text name="username" value="${configBean.username}"/>
        </l:field>
        <l:field caption="Password">
          <form:text name="password" value="${configBean.password}"/>
        </l:field>
        <l:field caption="Backup path">
          <form:text name="backuppath" value="${configBean.backuppath}"/>
        </l:field>
        <l:tablefield>
          <form:submit value="Save"/>
        </l:tablefield>
      </l:simpleform>
    </f:form>
    <div id="footer">
      <div class="copyright">GeoResults &copy; SebCsaba 2006 - <geo:version/></div>
    </div>
  </body>
</html>
