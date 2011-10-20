<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<%@taglib uri="http://sebcsaba.hu/taglib/geo/importing" prefix="importing"%>
<jsp:useBean id="importFormUtils" scope="request" class="scs.georesults.logic.utils.ImportFormUtils"/>
<jsp:setProperty name="importFormUtils" property="pageContext" value="${pageContext}"/>
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
        <c:write key="IF_MSG_ETAPOK_MELYIK_SZAKASZBA"/>
        <f:form action="/importing/etapokMelyikSzakaszba.do">
          <table class="recog">
            <c:foreach items="${importBean.categorySubbean.sourceEsemenyek}" var="etapVL">
              <tr>
                <td class="recogName">
                  <c:write caption="${etapVL.label}"/>
                </td>
                <td class="recogList">
                  <f:select name="szakasz_for_${etapVL.value}" value="">
                    <f:items items="${importFormUtils.szakaszok}" labelPropertyName="label" valuePropertyName="value"/>
                  </f:select>
                </td>
              </tr>
            </c:foreach>
          </table>
          <div class="formRow">
            <f:submit name="ok" title="BS_OK"/>
          </div>
        </f:form>
      </importing:wizardform>
    </div>
  </body>
</html>
