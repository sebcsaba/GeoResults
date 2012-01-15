<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <div class="backBar">&lt;&lt; <c:link action="/verseny/sorrendFuggoFeladatTipusok.do?mode=back"><c:write key="BS_VISSZA"/></c:link></div>
      <geo:error />
      <l:form name="sorrendFuggoFeladatTipusok" action="/verseny/sorrendFuggoFeladatTipusok.do?mode=save" title="RA_SORRENDFUGGO_FELADAT_TIPUS_ADATAI">
        <l:field title="BS_NEV">
          <f:label name="nev" value="${reszAdat.resz.nev}" maxlength="50"/>
        </l:field>
        <l:field title="RA_HIANY_HIBAPONT">
          <f:integer name="hianyHibapont" value="${reszAdat.resz.hianyHibapont}"/>
        </l:field>
        <l:field title="RA_TOBBLET_HIBAPONT">
          <f:integer name="tobbletHibapont" value="${reszAdat.resz.tobbletHibapont}"/>
        </l:field>
        <l:field title="RB_RESZLETES_BEVITEL">
          <f:checkbox name="reszletesBevitel" value="${reszAdat.resz.reszletesBevitel}"/>
        </l:field>
        <l:field title="FT_ELLENORZES_TIPUS">

            <geo:ellenorzestipusinput 
		name="ellenorzesTipus" 
		value="${reszAdat.resz.ellenorzesTipus}"/>

          <%--f:select name="ellenorzesTipus" value="${reszAdat.resz.ellenorzesTipus}">
            <f:items items="${formUtils.ellenorzesTipusok}" labelPropertyName="label" valuePropertyName="value"/>
          </f:select--%>

        </l:field>
        <l:field title="FT_ELLENORZOPONTOK">
          <f:textarea name="ellenorzoPontok" value="${reszAdat.resz.ellenorzoPontok}" itemProperty="felirat" cols="10" rows="10"/>
        </l:field>
      </l:form>
    </div>
  </body>
</html>
