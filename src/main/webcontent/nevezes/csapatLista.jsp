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
      <c:if test="${!empty verseny.nevezesek}">
        <l:table title="RB_CSAPAT_NEVEZESEK" action="/nevezes/csapat.do" items="${verseny.csapatNevezesek}" var="csn" idfield="csnid">
          <l:column title="BS_NEV" value="${csn.nev}"/>
        </l:table>
      </c:if>
      <c:if test="${empty verseny.nevezesek}">
        <div class="error">
          <c:write key="ER_NEM_LEHET_ADATOT_FELVINNI_NINCS_EGYENI_NEVEZES"/>
        </div>
      </c:if>
    </div>
  </body>
</html>
