<%@page errorPage="/common/error.jsp"%>
<%@include file="/common/xmlcontentheader.jspf"%>

<x:table styleClass="results">

  <x:column width="12"/>
  <x:column width="12"/>
  <x:column width="21.5" span="6"/>
  <c:foreach items="${verseny.etapok}" var="etap">
    <c:if test="${etap.ertekelendo}">
      <x:column width="16"/>
    </c:if>
  </c:foreach>
  <x:column width="20"/>

  <x:rowgroup header="true">
    <x:row height="9">
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header" rowspan="2">
        <x:data type="String"><c:write key="BS_HELY"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header" rowspan="2">
        <x:data type="String"><c:write key="RB_RAJTSZAM_ROVIDITVE"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="lt" contentStyle="bold header" colspan="3">
        <x:data type="String"><c:write key="RB_SOFOR"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="rt" contentStyle="bold header" colspan="3">
        <x:data type="String"><c:write key="RB_NAVIGATOR"/></x:data>
      </x:cell>
      <c:foreach items="${verseny.etapok}" var="etap">
        <c:if test="${etap.ertekelendo}">
          <x:cell header="true" borderStyle="lrtb" contentStyle="left small header" rowspan="2">
            <x:data type="String"><c:write label="${etap.nev}"/></x:data>
          </x:cell>
        </c:if>
      </c:foreach>
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header" rowspan="2">
        <x:data type="String"><c:write key="BS_PONT"/></x:data>
      </x:cell>
    </x:row>
    <x:row height="9">
      <x:cell header="true" borderStyle="lrb" contentStyle="bold header" colspan="6" colIndex="3">
        <x:data type="String"><c:write key="RB_UTASOK"/></x:data>
      </x:cell>
    </x:row>
  </x:rowgroup>

  <c:foreach items="${mindenEtapMegjelenito.eredmenyek}" var="mindenEtapEredmeny" indexVar="hely">
    <x:rowgroup>
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="2">
          <x:data type="String"><c:write caption="${hely+1}"/>.</x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="2">
          <x:data type="Number"><c:write caption="${mindenEtapEredmeny.rajtszam}"/></x:data>
        </x:cell>
        <r:sofor nevezes="${mindenEtapEredmeny.nevezes}" clipStyle="clip"/>
        <r:navigator nevezes="${mindenEtapEredmeny.nevezes}" clipStyle="clip"/>
        <c:foreach items="${verseny.etapok}" var="etap">
          <c:if test="${etap.ertekelendo}">
            <c:expression bean="${mindenEtapEredmeny.etapEredmenyek}" method="findItem" param1="eid" param2="${etap.eid}" var="ee"/>
            <x:cell borderStyle="lrtb" contentStyle="numeric" rowspan="2">
              <x:data type="Number"><c:write caption="${ee.pontszam}"/></x:data>
            </x:cell>
          </c:if>
        </c:foreach>
        <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="2">
          <x:data type="Number"><c:write caption="${mindenEtapEredmeny.pontszam}"/></x:data>
        </x:cell>
      </x:row>
      <x:row height="9">
        <r:utasok nevezes="${mindenEtapEredmeny.nevezes}" colIndex="3" clipStyle="clip"/>
      </x:row>
    </x:rowgroup>
  </c:foreach>

</x:table>
