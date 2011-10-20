<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<%@taglib uri="http://sebcsaba.hu/taglib/geo/importing" prefix="importing"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/importing.css" />
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <importing:wizardform percent="${importBean.stepsPercent}">
        <c:write key="IF_MSG_IMPORT_DEPENDENCIES"/>
	(<c:write key="${importBean.categorySubbean.importOrAssignTypenamesKey}"/>)
        <f:form action="/importing/importOrRecognise.do">
          <div class="formRow">
            <f:radiogroup name="ior" value="recognise">
              <f:item value="import" title="IF_IMPORT_SUBTYPES"/>
              <f:item value="recognise" title="IF_RECOGNISE_SUBTYPES"/>
            </f:radiogroup>
          </div>
          <div class="formRow">
            <f:submit name="ok" title="BS_OK"/>
          </div>
        </f:form>
      </importing:wizardform>
    </div>
  </body>
</html>
