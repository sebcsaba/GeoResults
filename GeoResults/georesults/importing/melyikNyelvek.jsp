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
        <c:write key="IF_MSG_MELYIK_NYELVEK"/>
        <f:form action="/importing/melyikNyelvek.do">
          <c:foreach items="${importBean.categorySubbean.lehetsegesNyelvek}" var="nyelvVL">
            <div class="formRow">
              <f:checkbox name="${nyelvVL.value}" value="false"/><label for="${nyelvVL.value}"><c:write caption="${nyelvVL.label}"/></label>
            </div>
          </c:foreach>
          <div class="formRow">
            <f:submit name="ok" title="BS_OK"/>
          </div>
        </f:form>
      </importing:wizardform>
    </div>
  </body>
</html>
