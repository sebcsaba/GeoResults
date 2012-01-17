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
      <div class="backBar">&lt;&lt; <c:link action="/kozos/orszag.do?mode=back"><c:write key="BS_VISSZA"/></c:link></div>
      <l:form name="orszag" action="/kozos/orszag.do?mode=save" title="DC_ORSZAG_FORDITASA">
        <l:field title="DC_MELYIK_ORSZAG_NEVE">
          <f:text name="melyik" value="${reszAdat.keyValue}" disabled="true"/>
        </l:field>
        <l:field title="DC_MELYIK_NYELVEN">
          <f:text name="melyik" value="${reszAdat.langValue}" disabled="true"/>
        </l:field>
        <l:field title="DC_FORDITAS">
          <f:text name="value" value="${reszAdat.value}" maxlength="250"/>
        </l:field>
      </l:form>
    </div>
  </body>
</html>
