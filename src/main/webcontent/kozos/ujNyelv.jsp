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
      <div class="backBar">&lt;&lt; <c:link action="/kozos/nyelvek.jsp"><c:write key="BS_VISSZA"/></c:link></div>
      <l:form name="nyelvform" action="/kozos/ujNyelv.do" title="DC_UJ_NYELV">
        <l:field title="DC_KETBETUS_KOD">
          <f:text name="kod" value="" maxlength="2"/>
        </l:field>
        <l:field title="DC_A_NYELV_ONMAGA">
          <f:text name="self" value="" maxlength="250"/>
        </l:field>
        <l:field title="DC_MELYIK_NYELVRE_ALAPOZZA_A_FORDITAST">
          <f:select name="alapnyelv" value="${nyelv.lang}">
            <f:items items="${formUtils.nyelvek}" labelPropertyName="label" valuePropertyName="value"/>
          </f:select>
        </l:field>
        <l:tablefield title="DC_VALTSON_NYELVET_A_FORDITASHOZ"/>
      </l:form>
    </div>
  </body>
</html>
