<%@page errorPage="/common/error.jsp"%>
<%@include file="/common/xmlcontentheader.jspf"%>

<x:table styleClass="results">

  <x:column width="12"/>
  <x:column width="12"/>
  <x:column width="64.5"/>
  <x:column width="64.5"/>
  <c:foreach items="${verseny.szlalomok}" var="szlalom">
    <c:if test="${szlalom.ertekelendo}">
      <x:column width="16"/>
    </c:if>
  </c:foreach>
  <x:column width="20"/>

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
      <c:foreach items="${verseny.szlalomok}" var="szlalom">
        <c:if test="${szlalom.ertekelendo}">
          <x:cell header="true" borderStyle="lrtb" contentStyle="left small header">
            <x:data type="String"><c:write label="${szlalom.nev}"/></x:data>
          </x:cell>
        </c:if>
      </c:foreach>
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header">
        <x:data type="String"><c:write key="BS_PONT"/></x:data>
      </x:cell>
    </x:row>
  </x:rowgroup>

  <c:foreach items="${kategoriaEredmeny.eredmenyek}" var="mindenSzlalomEredmeny" indexVar="hely">
    <x:rowgroup>
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="important numeric">
          <x:data type="String"><c:write caption="${hely+1}"/>.</x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="important numeric">
          <x:data type="Number"><c:write caption="${mindenSzlalomEredmeny.rajtszam}"/></x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:clip styleClass="clip">
            <x:data type="String"><c:write caption="${mindenSzlalomEredmeny.nevezes.sofor}"/></x:data>
          </x:clip>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:clip styleClass="clip">
            <x:data type="String"><c:write caption="${mindenSzlalomEredmeny.nevezes.auto.nev}"/></x:data>
          </x:clip>
        </x:cell>
        <c:foreach items="${verseny.szlalomok}" var="szlalom">
          <c:if test="${szlalom.ertekelendo}">
            <c:expression bean="${mindenSzlalomEredmeny.szlalomEredmenyek}" method="findItem" param1="szlid" param2="${szlalom.szlid}" var="szlalomEredmeny"/>
            <x:cell borderStyle="lrtb" contentStyle="numeric">
              <x:data type="Number"><c:write caption="${szlalomEredmeny.pontszam}"/></x:data>
            </x:cell>
          </c:if>
        </c:foreach>
        <x:cell borderStyle="lrtb" contentStyle="important numeric">
          <x:data type="Number"><c:write caption="${mindenSzlalomEredmeny.pontszam}"/></x:data>
        </x:cell>
      </x:row>
    </x:rowgroup>
  </c:foreach>

</x:table>
