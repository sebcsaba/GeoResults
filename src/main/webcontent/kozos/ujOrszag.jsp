<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <div class="backBar">&lt;&lt; <c:link action="/kozos/orszagok.jsp"><c:write key="BS_VISSZA"/></c:link></div>
      <l:form name="nyelvform" action="/kozos/ujOrszag.do" title="DC_UJ_ORSZAG">
        <l:field title="DC_KETBETUS_KOD">
          <f:text name="kod" value="" maxlength="2"/>
        </l:field>
        <l:field title="DC_ORSZAG_NEVE">
          <f:text name="self" value="" maxlength="250"/>
        </l:field>
      </l:form>
    </div>
  </body>
</html>
