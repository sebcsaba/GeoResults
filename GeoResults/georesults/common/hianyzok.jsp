<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<jsp:useBean id="hianyzoAdatok" scope="request" class="scs.georesults.logic.beans.HianyzoAdatokBean"/>
<jsp:setProperty name="hianyzoAdatok" property="pageContext" value="${pageContext}"/>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <style type="text/css">
      .panel {
        width: 548px;
      }
      .missingTable {
        border-collapse: collapse;
        color: royalblue;
      }
      .col {
        padding: 2px;
        border: 1px solid royalblue;
      }
      .rszCol {
        text-align: center;
        width: 5em;
      }
      .nameCol {
        width: 13em;
      }
      .closeButton {
        /*margin: 8px;*/
      }
      .backBar {
        width: 538px;
      }
    </style>
  </head>
  <body onunload="window.opener.closePopup();">
    <l:simpleform title="RA_HIANYZO_ADATOK">
      <l:tablefield>
        <c:if test="${!empty hianyzoAdatok.hianyzoNevezesek}">
          <table class="missingTable">
            <tr>
              <th class="col rszCol"><c:write key="RB_RAJTSZAM"/></th>
              <th class="col nameCol"><c:write key="RB_SOFOR"/></th>
              <th class="col nameCol"><c:write key="RB_NAVIGATOR"/></th>
            </tr>
            <c:foreach items="${hianyzoAdatok.hianyzoNevezesek}" var="nevezes">
              <tr>
                <td class="col rszCol"><c:write caption="${nevezes.rajtszam}"/></td>
                <td class="col nameCol"><c:write caption="${nevezes.sofor}"/></td>
                <td class="col nameCol"><c:write caption="${nevezes.navigator}"/></td>
              </tr>
            </c:foreach>
          </table>
        </c:if>
        <c:if test="${empty hianyzoAdatok.hianyzoNevezesek}">
          <c:write key="FT_NINCSENEK_HIANYZO_ADATOK"/>
        </c:if>
      </l:tablefield>
    </l:simpleform>
<div class="backBar">
    <f:button styleClass="closeButton" name="back" title="BS_VISSZA" onclick="window.opener.closePopup();"/>
</div>
  </body>
</html>
