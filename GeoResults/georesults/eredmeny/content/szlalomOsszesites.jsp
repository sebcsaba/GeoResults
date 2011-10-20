<%@page errorPage="/common/error.jsp"%>
<%@include file="/common/xmlcontentheader.jspf"%>

<x:table styleClass="results">

  <x:column width="12"/>
  <x:column width="12"/>
  <x:column width="64.5"/>
  <x:column width="64.5"/>
  <c:foreach items="${szlalomOsszesitettMegjelenito.szlalomok}" var="szlalom">
    <x:column width="20"/>
  </c:foreach>
  <x:column width="20"/>
  <c:if test="${szlalomOsszesitettMegjelenito.szakasz.szlalomRedukaltPontokkal}">
    <x:column width="20"/>
  </c:if>

  <x:rowgroup header="true">
    <x:row height="9">
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header">
        <x:data type="String"><c:write key="BS_HELY"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header">
        <x:data type="String"><c:write key="RB_RAJTSZAM_ROVIDITVE"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header">
        <x:data type="String"><c:write key="RB_SOFOR"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header">
        <x:data type="String"><c:write key="RA_AUTO"/></x:data>
      </x:cell>
      <c:foreach items="${szlalomOsszesitettMegjelenito.szlalomok}" var="szlalom">
        <c:if test="${szlalom.ertekelendo}">
          <x:cell header="true" borderStyle="lrtb" contentStyle="left small header">
            <x:clip styleClass="clip">
              <x:data type="String"><c:write label="${szlalom.nev}"/></x:data>
            </x:clip>
          </x:cell>
        </c:if>
      </c:foreach>
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header">
        <x:data type="String"><c:write key="BS_PONT"/></x:data>
      </x:cell>
      <c:if test="${szlalomOsszesitettMegjelenito.szakasz.szlalomRedukaltPontokkal}">
        <x:cell header="true" borderStyle="lrtb" contentStyle="bold header">
          <x:data type="String"><c:write key="RA_ATVITT_PONT"/></x:data>
        </x:cell>
      </c:if>
    </x:row>
  </x:rowgroup>

  <c:foreach items="${kategoriaEredmeny.eredmenyek}" var="szlalomOsszesitettEredmeny" indexVar="hely">
    <x:rowgroup>
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="important numeric">
          <x:data type="String"><c:write caption="${hely+1}"/>.</x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="important numeric">
          <x:data type="Number"><c:write caption="${szlalomOsszesitettEredmeny.rajtszam}"/></x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:clip styleClass="clip">
            <x:data type="String"><c:write caption="${szlalomOsszesitettEredmeny.nevezes.sofor}"/></x:data>
          </x:clip>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:clip styleClass="clip">
            <x:data type="String"><c:write caption="${szlalomOsszesitettEredmeny.nevezes.auto.nev}"/></x:data>
          </x:clip>
        </x:cell>
        <c:foreach items="${szlalomOsszesitettMegjelenito.szlalomok}" var="szlalom">
          <c:if test="${szlalom.ertekelendo}">
            <c:expression bean="${szlalomOsszesitettEredmeny.reszeredmenyek}" method="findItem" param1="szlid" param2="${szlalom.szlid}" var="szlalomEredmeny"/>
            <x:cell borderStyle="lrtb" contentStyle="numeric">
              <x:data type="Number"><c:write caption="${szlalomEredmeny.pontszam}"/></x:data>
            </x:cell>
          </c:if>
        </c:foreach>
        <x:cell borderStyle="lrtb" contentStyle="important numeric">
          <x:data type="Number"><c:write caption="${szlalomOsszesitettEredmeny.pontszam}"/></x:data>
        </x:cell>
        <c:if test="${szlalomOsszesitettMegjelenito.szakasz.szlalomRedukaltPontokkal}">
          <x:cell borderStyle="lrtb" contentStyle="important numeric">
            <x:data type="Number"><c:write caption="${szlalomOsszesitettEredmeny.atvittPontszam}"/></x:data>
          </x:cell>
        </c:if>
      </x:row>
    </x:rowgroup>
  </c:foreach>

</x:table>
