<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<jsp:useBean id="statusBean" scope="page" class="scs.georesults.logic.beans.StatusBean"/>
<jsp:setProperty name="statusBean" property="pageContext" value="${pageContext}"/>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <style type="text/css">
      .statusTable {
        width: 100%
      }
      .statusTitle {
        width: 100px;
      }
      .statusBar {
      }
      .statusPercent {
        width: 50px;
      }
      .bar {
        background: cornflowerblue;
      }
      .barOk {
        background: royalblue;
      }
    </style>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <div class="caption panel"><c:write label="${verseny.nev}"/></div>
      <div class="panel">
        <table class="statusTable">
          <c:foreach items="${verseny.etapok}" var="etap">
            <c:expression bean="${statusBean}" method="getPercentage" param1="${etap}" var="percent"/>
            <tr>
              <td class="statusTitle"><c:write label="${etap.nev}"/></td>
              <td class="statusBar"><div class="bar<c:if test="${percent==100}">Ok</c:if>" style="width:<c:write caption="${percent}"/>%;">&nbsp;</div></td>
              <td class="statusPercent"><c:write caption="${percent}"/>%</td>
            </tr>
          </c:foreach>
          <c:foreach items="${verseny.szlalomok}" var="szlalom">
            <c:expression bean="${statusBean}" method="getPercentage" param1="${szlalom}" var="percent"/>
            <tr>
              <td class="statusTitle"><c:write label="${szlalom.nev}"/></td>
              <td class="statusBar"><div class="bar<c:if test="${percent==100}">Ok</c:if>" style="width:<c:write caption="${percent}"/>%;">&nbsp;</div></td>
              <td class="statusPercent"><c:write caption="${percent}"/>%</td>
            </tr>
          </c:foreach>
        </table>
      </div>
    </div>
  </body>
</html>
