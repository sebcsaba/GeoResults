<%@page errorPage="/common/error.jsp"%>
<%@include file="/common/xmlcontentheader.jspf"%>

<x:table styleClass="results">

  <x:column width="12"/>
  <x:column width="12"/>
  <x:column width="21.5" span="6"/>
  <c:foreach items="${verseny.szakaszok}" var="szakasz">
    <c:if test="${szakasz.ertekelendo}">
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
      <c:foreach items="${verseny.szakaszok}" var="szakasz">
        <c:if test="${szakasz.ertekelendo}">
          <x:cell header="true" borderStyle="lrtb" contentStyle="left small header" rowspan="2">
            <x:data type="String"><c:write label="${szakasz.nev}"/></x:data>
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

  <c:foreach items="${versenyMegjelenito.eredmenyek}" var="versenyEredmeny" indexVar="hely">
    <x:rowgroup>
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="2">
          <x:data type="String"><c:write caption="${hely+1}"/>.</x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="2">
          <x:data type="Number"><c:write caption="${versenyEredmeny.rajtszam}"/></x:data>
        </x:cell>
        <r:sofor nevezes="${versenyEredmeny.nevezes}" clipStyle="clip"/>
        <r:navigator nevezes="${versenyEredmeny.nevezes}" clipStyle="clip"/>
        <c:foreach items="${verseny.szakaszok}" var="szakasz">
          <c:if test="${szakasz.ertekelendo}">
            <c:expression bean="${versenyEredmeny.szakaszEredmenyek}" method="findItem" param1="szid" param2="${szakasz.szid}" var="sze"/>
            <x:cell borderStyle="lrtb" contentStyle="numeric" rowspan="2">
              <x:data type="Number"><c:write caption="${sze.pontszam}"/></x:data>
            </x:cell>
          </c:if>
        </c:foreach>
        <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="2">
          <x:data type="Number"><c:write caption="${versenyEredmeny.pontszam}"/></x:data>
        </x:cell>
      </x:row>
      <x:row height="9">
        <r:utasok nevezes="${versenyEredmeny.nevezes}" colIndex="3" clipStyle="clip"/>
      </x:row>
    </x:rowgroup>
  </c:foreach>

</x:table>
