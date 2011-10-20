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
      <div class="backBar">&lt;&lt; <c:link action="/nevezes/egyeni.do?mode=back"><c:write key="BS_VISSZA"/></c:link></div>
      <geo:error/>
      <l:form name="nevezes" action="/nevezes/egyeni.do?mode=save" title="RB_NEVEZES_ADATAI">
        <l:field title="RB_RAJTSZAM">
          <f:integer name="rajtszam" value="${reszAdat.resz.rajtszam}" disabled="${!reszAdat.create}"/>
        </l:field>
        <l:field title="RB_SOFOR">
          <f:text name="sofor" value="${reszAdat.resz.sofor}"/>
        </l:field>
        <l:field title="RB_NAVIGATOR">
          <f:text name="navigator" value="${reszAdat.resz.navigator}"/>
        </l:field>
        <l:field title="RB_UTAS">
          <f:text name="utas1" value="${reszAdat.resz.utas1}"/>
        </l:field>
        <l:field title="RB_UTAS">
          <f:text name="utas2" value="${reszAdat.resz.utas2}"/>
        </l:field>
        <l:field title="RB_UTAS">
          <f:text name="utas3" value="${reszAdat.resz.utas3}"/>
        </l:field>
        <l:field title="BS_ORSZAG">
          <f:select name="orszag" value="${reszAdat.resz.orszag}">
            <f:items items="${formUtils.orszagok}" labelPropertyName="label" valuePropertyName="value"/>
          </f:select>
        </l:field>
        <l:field title="RA_AUTOTIPUS">
          <f:select name="autoTipus" value="${reszAdat.resz.autoTipus}">
            <f:items items="${verseny.autoTipusok}" labelPropertyName="nev" valuePropertyName="atid"/>
          </f:select>
        </l:field>
      </l:form>
    </div>
  </body>
</html>
