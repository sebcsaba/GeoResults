<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<%@taglib uri="http://sebcsaba.hu/taglib/geo/importing" prefix="importing"%>
<%@taglib uri="http://sebcsaba.hu/taglib/form" prefix="hf"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/importing.css" />
    <script type="text/javascript">
      function setEnabled(isDb) {
        document.getElementById('src_file_filename').disabled = isDb;
        document.getElementById('src_db_verseny').disabled = !isDb;
      }
    </script>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <importing:wizardform percent="${importBean.stepsPercent}">
        <c:write key="IF_MSG_VALASSZON_ADATFORRAST"/>
        <f:form action="/importing/chooseSource.do" method="post" enctype="multipart/form-data">
          <div class="formRow">
            <hf:radio name="srcType" value="db" id="srcType_db" onchange="setEnabled(true);" checked="true"/>
            <label for="srcType_db"><c:write key="IF_ADATBAZISBOL"/></label>
            <f:select name="src_db_verseny" value="">
              <f:items items="${formUtils.versenyek}" labelPropertyName="nev" valuePropertyName="vid"/>
            </f:select>
          </div>
          <div class="formRow">
            <hf:radio name="srcType" value="file" id="srcType_file" onchange="setEnabled(false);"/>
            <label for="srcType_file"><c:write key="IF_FAJLBOL"/></label>
            <hf:file name="src_file_filename" id="src_file_filename" disabled="true"/>
            <div class="fieldInfo"><c:write key="IF_MSG_CSAK_XML2006"/></div>
          </div>
          <div class="formRow">
            <f:submit name="ok" title="BS_OK"/>
          </div>
        </f:form>
      </importing:wizardform>
    </div>
  </body>
</html>
