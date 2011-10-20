<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <c:if test="${!verseny.leVanZarva}">
        <div class="warning">
          <c:write key="FT_LEZARASI_FIGYELMEZTETES"/>
          <f:form id="lezaras" action="/verseny/lezaras.do" style="margin-top:16px;">
            <f:submit name="lezaras" title="FT_LEZARAS"/>
          </f:form>
        </div>
      </c:if>
      <c:if test="${verseny.leVanZarva}">
        <div class="warning">
          <c:write key="FT_LEZARAS_MEGTORTENT"/>
        </div>
      </c:if>
    </div>
  </body>
</html>
