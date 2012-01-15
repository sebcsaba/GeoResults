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
      <div class="backBar">&lt;&lt; <c:link action="/kozos/forditasMain.jsp"><c:write key="BS_VISSZA"/></c:link></div>
      <f:form action="/kozos/forditas.do">
        <l:simpleform>
          <l:field title="DC_MELYIK_NYELVRE_ALAPOZZA_A_FORDITAST">
            <f:select name="alapnyelv" value="${forditasBean.alapnyelv}" onchange="this.form.submit();">
              <f:items items="${formUtils.nyelvek}" labelPropertyName="label" valuePropertyName="value"/>
            </f:select>
            <%--f:hidden name="celnyelv" value="${forditasBean.celnyelv}"/--%>
          </l:field>
        </l:simpleform>
      </f:form>
      <l:form name="forditas" action="/kozos/forditas.do?mode=save" title="DC_FORDITASOK">
        <%--f:hidden name="celnyelv" value="${forditasBean.celnyelv}"/--%>
        <c:foreach items="${forditasBean.bejegyzesek}" var="szb">
          <c:expression bean="${forditasBean}" method="setAktBejegyzes" param1="${szb}"/>
          <l:field caption="${forditasBean.aktBejegyzesAlapnyelven}">
            <f:text name="${szb.keystr}" value="${szb.valuestr}"/>
          </l:field>
        </c:foreach>
      </l:form>
    </div>
  </body>
</html>
