<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <script type="text/javascript">
      function doFormulaPopup() {
        url = '../common/formulaeditor.jsp';
        param = document.getElementById('menetlevelformula').value;
        popupPanelAnywhere(url+'?formula='+param,386,428);
      }
    </script>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <l:form name="etap" action="/etap/adatokMentes.do" title="RB_ETAP_ADATAI">
        <l:field title="BS_NEV">
          <f:label name="nev" value="${etap.nev}"/>
        </l:field>
        <l:field title="RA_IDOLIMIT">
          <f:integer name="idoLimit" value="${etap.idoLimit}"/>
        </l:field>
        <l:field title="FT_ERTEKELENDO">
          <f:checkbox name="ertekelendo" value="${etap.ertekelendo}"/>
        </l:field>
        <l:field title="FT_MENETLEVEL_FORMULA">
          <f:button name="formulaPopup" onclick="doFormulaPopup();" title="BS_SZERKESZTES" optional="true" value="${etap.menetlevelformula}"/>
          <f:hidden name="menetlevelformula" value="${etap.abszolutMenetlevelformula}"/>
        </l:field>
      </l:form>
      <l:table title="RA_SORRENDFUGGO_FELADATOK" action="/etap/sorrendFuggoEtapFeladatok.do" items="${etap.sorrendFuggoEtapFeladatok}" var="sfef" idfield="sfftid">
        <l:column title="BS_NEV" value="${sfef.sorrendFuggoFeladatTipus.nev}"/>
      </l:table>
      <l:table title="RA_DARABFUGGO_FELADATOK" action="/etap/darabFuggoEtapFeladatok.do" items="${etap.darabFuggoEtapFeladatok}" var="dfef" idfield="dfftid">
        <l:column title="BS_NEV" value="${dfef.darabFuggoFeladatTipus.nev}"/>
      </l:table>
    </div>
  </body>
</html>
