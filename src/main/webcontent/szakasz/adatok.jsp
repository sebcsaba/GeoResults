<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>

      <l:form name="szakaszok" action="/szakasz/adatokMentes.do" title="RB_SZAKASZ_ADATAI">
        <l:field title="BS_NEV">
          <f:label name="nev" value="${szakasz.nev}" maxlength="250"/>
        </l:field>
        <l:field title="RA_MEGENGEDETT_KESES_ETAPONKENT">
          <f:integer name="megengedettKesesEtaponkent" value="${szakasz.megengedettKesesEtaponkent}"/>
        </l:field>
        <l:field title="RA_KESESERT_BUNTETOPONT">
          <f:integer name="kesesertBuntetoPont" value="${szakasz.kesesertBuntetoPont}"/>
        </l:field>
        <l:field title="RB_SZLALOM_REDUKALT_PONTOKKAL">
          <f:checkbox name="szlalomRedukaltPontokkal" value="${szakasz.szlalomRedukaltPontokkal}"/>
        </l:field>
        <l:field title="FT_ERTEKELENDO">
          <f:checkbox name="ertekelendo" value="${szakasz.ertekelendo}"/>
        </l:field>
      </l:form>

      <l:table title="RB_ETAPOK" action="/szakasz/etapok.do" items="${szakasz.etapok}" var="etap" idfield="eid">
        <l:column title="BS_NEV" value="${etap.nev}"/>
      </l:table>

      <l:table title="RB_SZLALOMOK" action="/szakasz/szlalomok.do" items="${szakasz.szlalomok}" var="szlalom" idfield="szlid">
        <l:column title="BS_NEV" value="${szlalom.nev}"/>
      </l:table>

    </div>
  </body>
</html>
