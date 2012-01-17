<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <script type="text/javascript" src="<c:path/>/common/checkUtils.js"></script>
    <script type="text/javascript">
      function popupBuntetesek()
      {
        popupPanelAnywhere('buntetesPopup.jsp',318,500);
      }
      function popupDfef(id)
      {
        popupPanelAnywhere('dfefPopup.jsp?id='+id,318,700);
      }
      function popupSfef(id,count)
      {
        popupPanelAnywhere('sfefPopup.jsp?id='+id+'&count='+count,318,700);
      }
      function popupHianyzok()
      {
        eid = document.getElementById('eid').value;
        popupPanelAnywhere('../common/hianyzok.jsp?mode=menetlevel&eid='+eid,582,300);
      }
    </script>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <c:if test="${!empty verseny.etapok}">
        <f:form id="kivalasztas" action="/menetlevel/betoltes.do">
          <l:simpleform title="RB_MENETLEVEL">
            <l:field title="RB_ETAP">
              <f:select name="eid" value="${menetlevelAdatok.betoltes.eid}">
                <f:items items="${verseny.etapok}" labelPropertyName="nev" valuePropertyName="eid"/>
              </f:select>
            </l:field>
            <l:field title="RB_RAJTSZAM">
              <f:integer name="rajtszam" value="${menetlevelAdatok.betoltes.rajtszam}"/>
            </l:field>
            <l:field>
              <f:submit name="load" title="BS_BETOLTES"/>
            </l:field>
            <l:field>
              <f:button name="missing" title="RA_HIANYZO_ADATOK" onclick="popupHianyzok();"/>
            </l:field>
          </l:simpleform>
        </f:form>
      </c:if>
      <c:if test="${empty verseny.etapok}">
        <div class="error">
          <c:write key="ER_NEM_LEHET_ADATOT_FELVINNI_NINCS_ETAP"/>
        </div>
      </c:if>
      <c:if test="${menetlevelAdatok.futam!=null}">
        <f:form id="menetlevel" action="/menetlevel/mentes.do">
          <ml:menetlevel menetlevel="${menetlevelAdatok.futam}"/>
          <f:hidden name="menetlevelformula" value="${menetlevelAdatok.absoluteMenetlevelFormula}"/>
          <l:simpleform title="BS_KOVETKEZO">
            <l:field title="RB_RAJTSZAM">
              <f:integer name="rajtszam" value="${menetlevelAdatok.kovetkezoRajtszam}"/>
              <f:hidden name="eid" value="${menetlevelAdatok.futam.eid}"/>
            </l:field>
            <l:field>
              <f:submit name="saveLoad" title="BS_MENTES_ES_BETOLTES" accesskey="s" popuptitle="Alt+S"/>
            </l:field>
          </l:simpleform>
        </f:form>
      </c:if>
    </div>
  </body>
</html>
