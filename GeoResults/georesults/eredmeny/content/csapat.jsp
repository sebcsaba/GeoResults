<%@page errorPage="/common/error.jsp"%>
<%@include file="/common/xmlcontentheader.jspf"%>

<x:table styleClass="results">

  <x:column width="12"/>
  <x:column width="12"/>
  <x:column width="21.5" span="6"/>
  <x:column width="18"/>
  <x:column width="20"/>

  <x:rowgroup header="true">
    <x:row height="9">
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header">
        <x:data type="String"><c:write key="BS_HELY"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header" colspan="8">
        <x:data type="String"><c:write key="BS_NEV"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header">
        <x:data type="String"><c:write key="BS_PONT"/></x:data>
      </x:cell>
    </x:row>
  </x:rowgroup>

  <c:foreach items="${csapatMegjelenito.eredmenyek}" var="csapatEredmeny" indexVar="hely">
    <x:rowgroup>
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="7">
          <x:data type="String"><c:write caption="${hely+1}"/>.</x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text" colspan="8">
          <x:data type="String"><c:write caption="${csapatEredmeny.nev}"/></x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="7">
          <x:data type="Number"><c:write caption="${csapatEredmeny.pontszam}"/></x:data>
        </x:cell>
      </x:row>
      <c:foreach limit="3" indexVar="rajtszamIndex">
        <c:expression bean="${csapatMegjelenito}" method="findNevezesByIndex" param1="${csapatEredmeny}" param2="${rajtszamIndex+1}" var="nevezes"/>
        <x:row height="9">
          <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="2" colIndex="2">
            <x:data type="Number"><c:write caption="${nevezes.rajtszam}"/></x:data>
          </x:cell>
          <r:sofor nevezes="${nevezes}" clipStyle="clip"/>
          <r:navigator nevezes="${nevezes}" clipStyle="clip"/>
          <x:cell borderStyle="lrtb" contentStyle="numeric" rowspan="2">
            <x:data type="Number"><c:write caption="${csapatMegjelenito.nevezeshezTartozoVegeredmeny.pontszam}"/></x:data>
          </x:cell>
        </x:row>
        <x:row height="9">
          <r:utasok nevezes="${nevezes}" colIndex="3" clipStyle="clip"/>
        </x:row>
      </c:foreach>
    </x:rowgroup>
  </c:foreach>
</x:table>
