<%@page errorPage="/common/error.jsp"%>
<%@include file="/common/xmlcontentheader.jspf"%>

<x:table styleClass="results">

  <x:column width="12"/>
  <x:column width="12"/>
  <x:column width="64.5"/>
  <x:column width="64.5"/>
  <c:foreach items="${szlalomMegjelenito.szlalomFeladatok}" var="szlalomFeladat">
    <x:column width="16"/>
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
      <c:foreach items="${szlalomMegjelenito.szlalomFeladatok}" var="szlalomFeladat">
        <x:cell header="true" borderStyle="lrtb" contentStyle="left small header">
          <x:clip styleClass="clip">
            <x:data type="String"><c:write label="${szlalomFeladat.nev}"/></x:data>
          </x:clip>
        </x:cell>
      </c:foreach>
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header">
        <x:data type="String"><c:write key="BS_PONT"/></x:data>
      </x:cell>
    </x:row>
  </x:rowgroup>

  <c:foreach items="${kategoriaEredmeny.eredmenyek}" var="szlalomEredmeny" indexVar="hely">
    <x:rowgroup>
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="important numeric">
          <x:data type="String"><c:write caption="${hely+1}"/>.</x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="important numeric">
          <x:data type="Number"><c:write caption="${szlalomEredmeny.rajtszam}"/></x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:clip styleClass="clip">
            <x:data type="String"><c:write caption="${szlalomEredmeny.nevezes.sofor}"/></x:data>
          </x:clip>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:clip styleClass="clip">
            <x:data type="String"><c:write caption="${szlalomEredmeny.nevezes.auto.nev}"/></x:data>
          </x:clip>
        </x:cell>
        <c:foreach items="${szlalomMegjelenito.szlalomFeladatok}" var="szlalomFeladat">
          <c:expression bean="${szlalomEredmeny.szlalomFutam.bejegyzesek}" method="findItem" param1="szfid" param2="${szlalomFeladat.szfid}" var="szlalomBejegyzes"/>
          <x:cell borderStyle="lrtb" contentStyle="numeric">
            <x:data type="Number"><c:write caption="${szlalomBejegyzes.darab}"/></x:data>
          </x:cell>
        </c:foreach>
        <x:cell borderStyle="lrtb" contentStyle="important numeric">
          <x:data type="Number"><c:write caption="${szlalomEredmeny.pontszam}"/></x:data>
        </x:cell>
      </x:row>
    </x:rowgroup>
  </c:foreach>

</x:table>
