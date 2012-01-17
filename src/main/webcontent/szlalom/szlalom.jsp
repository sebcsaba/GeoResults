<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <script type="text/javascript">
      function popupHianyzok()
      {
        szlid = document.getElementById('szlid').value;
        popupPanelAnywhere('../common/hianyzok.jsp?mode=szlalomfutam&szlid='+szlid,582,300);
      }
    </script>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <c:if test="${!empty verseny.szlalomok}">
        <f:form id="kivalasztas" action="/szlalom/betoltes.do">
          <l:simpleform title="RB_SZLALOM_FUTAM">
            <l:field title="RB_SZLALOM">
              <f:select name="szlid" value="${szlalomAdatok.betoltes.szlid}">
                <f:items items="${szlalomAdatok.verseny.szlalomok}" labelPropertyName="nev" valuePropertyName="szlid"/>
              </f:select>
            </l:field>
            <l:field title="RB_RAJTSZAM">
              <f:integer name="rajtszam" value="${szlalomAdatok.betoltes.rajtszam}"/>
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
      <c:if test="${empty verseny.szlalomok}">
        <div class="error">
          <c:write key="ER_NEM_LEHET_ADATOT_FELVINNI_NINCS_SZLALOM"/>
        </div>
      </c:if>
      <c:if test="${szlalomAdatok.futam!=null}">
        <f:form id="szlalom" action="/szlalom/mentes.do">
          <l:simpleform title="RB_SZLALOM_FUTAM_ADATOK">
            <c:foreach items="${szlalomAdatok.verseny.szlalomFeladatok}" var="szf">
              <c:expression var="szfDarab" bean="${szlalomAdatok}" method="getDarab" param1="${szf}"/>
              <l:field label="${szf.nev}">
                <f:integer name="szlalomFeladat_${szf.szfid}" value="${szfDarab}"/>
              </l:field>
            </c:foreach>
          </l:simpleform>
          <l:simpleform title="BS_KOVETKEZO">
            <l:field title="RB_RAJTSZAM">
              <f:integer name="rajtszam" value="${szlalomAdatok.kovetkezoRajtszam}"/>
              <f:hidden name="szlid" value="${szlalomAdatok.futam.szlid}"/>
            </l:field>
            <l:field>
              <f:submit name="saveLoad" title="BS_MENTES_ES_BETOLTES" accesskey="s" popuptitle="Alt+S"/>
            </l:field>
          </l:simpleform>
        </f:form>
        <script type="text/javascript">
          szlstart=document.getElementById('szlalomFeladat_<c:write caption="${szlalomAdatok.firstInputId}"/>');
          szlstart.focus();
          szlstart.setSelectionRange(0,szlstart.value.length);
        </script>
      </c:if>
    </div>
  </body>
</html>
