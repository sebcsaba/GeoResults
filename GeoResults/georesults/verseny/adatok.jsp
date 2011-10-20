<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
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

      <l:form name="verseny" action="/verseny/adatokMentes.do" title="RB_VERSENY_ADATAI">
        <l:field title="BS_NEV">
          <f:text name="nev" value="${verseny.nev}"/>
        </l:field>
        <l:field title="RA_KEZDETE_DATUM">
          <f:date name="kezdeteDatum" value="${verseny.kezdeteDatum}"/>
        </l:field>
        <l:field title="RA_VEGE_DATUM">
          <f:date name="vegeDatum" value="${verseny.vegeDatum}"/>
        </l:field>
        <l:field title="DC_ALAP_NYELV">
          <f:select name="alapNyelv" value="${verseny.alapNyelv}">
            <f:items items="${formUtils.nyelvek}" labelPropertyName="label" valuePropertyName="value"/>
          </f:select>
        </l:field>
        <l:field title="FT_MENETLEVEL_FORMULA">
          <f:button name="formulaPopup" onclick="doFormulaPopup();" title="BS_SZERKESZTES"/>
          <f:hidden name="menetlevelformula" value="${verseny.menetlevelformula}"/>
        </l:field>
        <c:if test="${verseny.leVanZarva}">
          <l:field title="FT_LEZARVA_DATUM">
            <f:date name="lezarva" value="${verseny.lezarva}" disabled="true"/>
          </l:field>
        </c:if>
      </l:form>

      <l:table title="RB_SZAKASZOK" action="/verseny/szakaszok.do" items="${verseny.szakaszok}" var="sz" idfield="szid">
        <l:column title="BS_NEV" value="${sz.nev}"/>
      </l:table>

    </div>
  </body>
</html>
