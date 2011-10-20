<%@page errorPage="/common/error.jsp"%>
<%@include file="/common/xmlcontentheader.jspf"%>
<jsp:useBean id="displayUtils" scope="request" class="scs.georesults.logic.utils.DisplayUtils"/>
<jsp:setProperty name="displayUtils" property="pageContext" value="${pageContext}"/>

<x:table styleClass="results">

  <x:column width="50"/>
  <x:column width="50"/>

  <x:rowgroup>
    <x:row height="9">
      <x:cell borderStyle="lrtb" contentStyle="important" colspan="2">
        <x:data type="String"><c:write key="FT_KESES_ETAPOKON"/></x:data>
      </x:cell>
    </x:row>
    <c:foreach items="${verseny.kesesiZonak}" var="kesesiZona">
      <c:expression bean="${displayUtils}" method="getKesesiZonaIntervallum" param1="${kesesiZona}" var="kzIntervallum"/>
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write caption="${kzIntervallum}"/> <c:write key="BS_PERC"/></x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write caption="${kesesiZona.pont}"/> <c:write key="BS_PONT"/> / <c:write key="BS_PERC"/></x:data>
        </x:cell>
      </x:row>
    </c:foreach>
  </x:rowgroup>

  <x:rowgroup>
    <x:row height="9">
      <x:cell borderStyle="lrtb" contentStyle="important" colspan="2">
        <x:data type="String"><c:write key="FT_FELADATOK_ETAPOKON"/></x:data>
      </x:cell>
    </x:row>
    <c:foreach items="${verseny.sorrendFuggoFeladatTipusok}" var="sfft">
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write label="${sfft.nev}"/> <c:write key="RA_HIANY"/></x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write caption="${sfft.hianyHibapont}"/> <c:write key="BS_PONT"/> / <c:write key="BS_DARAB"/></x:data>
        </x:cell>
      </x:row>
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write label="${sfft.nev}"/> <c:write key="RA_TOBBLET"/></x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write caption="${sfft.tobbletHibapont}"/> <c:write key="BS_PONT"/> / <c:write key="BS_DARAB"/></x:data>
        </x:cell>
      </x:row>
    </c:foreach>
    <c:foreach items="${verseny.darabFuggoFeladatTipusok}" var="dfft">
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write label="${dfft.nev}"/> <c:write key="RA_HIANY"/></x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write caption="${dfft.hianyHibapont}"/> <c:write key="BS_PONT"/> / <c:write key="BS_DARAB"/></x:data>
        </x:cell>
      </x:row>
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write label="${dfft.nev}"/> <c:write key="RA_TOBBLET"/></x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write caption="${dfft.tobbletHibapont}"/> <c:write key="BS_PONT"/> / <c:write key="BS_DARAB"/></x:data>
        </x:cell>
      </x:row>
    </c:foreach>
  </x:rowgroup>

  <x:rowgroup>
    <x:row height="9">
      <x:cell borderStyle="lrtb" contentStyle="important" colspan="2">
        <x:data type="String"><c:write key="FT_BUNTETESEK_ETAPOKON"/></x:data>
      </x:cell>
    </x:row>
    <c:foreach items="${verseny.buntetesTipusok}" var="buntetesTipus">
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write label="${buntetesTipus.nev}"/></x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write caption="${buntetesTipus.pont}"/> <c:write key="BS_PONT"/> / <c:write key="BS_DARAB"/></x:data>
        </x:cell>
      </x:row>
    </c:foreach>
  </x:rowgroup>

  <x:rowgroup>
    <x:row height="9">
      <x:cell borderStyle="lrtb" contentStyle="important" colspan="2">
        <x:data type="String"><c:write key="FT_SZLALOMOK_PONTOZASA"/></x:data>
      </x:cell>
    </x:row>
    <c:foreach items="${verseny.szlalomFeladatok}" var="szlalomFeladat">
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write label="${szlalomFeladat.nev}"/></x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:data type="String"><c:write caption="${szlalomFeladat.pont}"/> <c:write key="BS_PONT"/> / <c:write key="BS_DARAB"/></x:data>
        </x:cell>
      </x:row>
    </c:foreach>
  </x:rowgroup>

</x:table>
