<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <c:if test="${!empty verseny.autoTipusok}">
        <l:table title="RB_NEVEZESEK" action="/nevezes/egyeni.do" items="${verseny.nevezesek}" var="n" idfield="rajtszam">
          <l:column title="RB_RAJTSZAM" value="${n.rajtszam}"/>
          <l:column title="RB_SOFOR" value="${n.sofor}"/>
          <l:column title="RB_NAVIGATOR" value="${n.navigator}"/>
        </l:table>
      </c:if>
      <c:if test="${empty verseny.autoTipusok}">
        <div class="error">
          <c:write key="ER_NEM_LEHET_ADATOT_FELVINNI_NINCS_AUTOTIPUS"/>
        </div>
      </c:if>
    </div>
  </body>
</html>
