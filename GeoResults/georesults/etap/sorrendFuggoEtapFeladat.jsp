<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <script type="text/javascript" src="<c:path/>/common/ellenorzesTipusUtils.js"></script>
    <script type="text/javascript">
      function changeReszletesBevitel(input)
      {
        reszletes = input.checked;
        document.getElementById('darab').disabled = reszletes;
        setEllenorzesTipusDisabled('ellenorzesTipus',!reszletes);
        document.getElementById('bejegyzesek').disabled = !reszletes;
        doModifyFormBy(input,'<c:write key="BS_MODOSITVA"/>');
      }
      function popupBejegyzesek()
      {
        et = "ellenorzesTipus="+getEllenorzesTipusValue('ellenorzesTipus');
        popupPanelAnywhere('sorrendFuggoEtalon.jsp?'+et,334,700);
      }
    </script>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <div class="backBar">&lt;&lt; <c:link action="/etap/sorrendFuggoEtapFeladatok.do?mode=back"><c:write key="BS_VISSZA"/></c:link></div>
      <l:form name="sorrendfuggoetapfeladat" action="/etap/sorrendFuggoEtapFeladatok.do?mode=save" title="RA_ETAPFELADAT_ADATAI">
        <l:field title="BS_FELADAT">
          <f:select name="sfftid" value="${reszAdat.resz.sfftid}" disabled="${!reszAdat.create}">
            <f:items items="${verseny.sorrendFuggoFeladatTipusok}" labelPropertyName="nev" valuePropertyName="sfftid"/>
          </f:select>
        </l:field>
        <c:if test="${!reszAdat.create}">
          <l:field title="RB_RESZLETES_BEVITEL">
            <f:checkbox name="reszletesBevitel" value="${reszAdat.resz.reszletesBevitel}" onchange="changeReszletesBevitel(this);" disabled="${reszAdat.extra.vanMenetlevel}"/>
          </l:field>
          <l:field title="BS_DARAB">
            <f:integer name="darab" value="${reszAdat.resz.darab}" disabled="${reszAdat.resz.reszletesBevitel}"/>
          </l:field>
          <l:field title="FT_ELLENORZES_TIPUS">
            <geo:ellenorzestipusinput name="ellenorzesTipus" value="${reszAdat.extra.ellenorzesTipus}" parentvalue="${reszAdat.extra.ellenorzesTipusParent}" disabled="${!reszAdat.resz.reszletesBevitel}"/>
          </l:field>
          <l:field title="BS_BEJEGYZESEK">
            <f:button name="bejegyzesek" onclick="popupBejegyzesek();" title="BS_BEVITEL" disabled="${!reszAdat.resz.reszletesBevitel}"/>
          </l:field>
        </c:if>
      </l:form>
    </div>
  </body>
</html>
