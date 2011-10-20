<%@page errorPage="/common/error.jsp"%>
<%@include file="/common/xmlcontentheader.jspf"%>

<x:table styleClass="results">

  <x:column width="12"/>
  <x:column width="12"/>
  <x:column width="21.5" span="6"/>
  <x:column width="10"/>
  <x:column width="10"/>
  <c:foreach items="${etapMegjelenito.etap.sorrendFuggoEtapFeladatok}" var="sfef">
    <x:column width="6"/>
    <x:column width="6"/>
    <x:column width="10"/>
  </c:foreach>
  <c:foreach items="${etapMegjelenito.etap.darabFuggoEtapFeladatok}" var="dfef">
    <x:column width="6"/>
    <x:column width="6"/>
    <x:column width="10"/>
  </c:foreach>
  <x:column width="18"/>
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
      <x:cell header="true" borderStyle="lrt" contentStyle="header" colspan="2">
        <x:data type="String"><c:write key="BS_IDO"/></x:data>
      </x:cell>
      <c:foreach items="${etapMegjelenito.etap.sorrendFuggoEtapFeladatok}" var="sfef">
        <x:cell header="true" borderStyle="lrt" contentStyle="header" colspan="3">
          <x:data type="String"><c:write label="${sfef.sorrendFuggoFeladatTipus.nev}"/></x:data>
        </x:cell>
      </c:foreach>
      <c:foreach items="${etapMegjelenito.etap.darabFuggoEtapFeladatok}" var="dfef">
        <x:cell header="true" borderStyle="lrt" contentStyle="header" colspan="3">
          <x:data type="String"><c:write label="${dfef.darabFuggoFeladatTipus.nev}"/></x:data>
        </x:cell>
      </c:foreach>
      <x:cell header="true" borderStyle="lrt" contentStyle="header">
        <x:data type="String"><c:write key="RA_BUNTETES"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="lrtb" contentStyle="bold header" rowspan="2">
        <x:data type="String"><c:write key="BS_PONT"/></x:data>
      </x:cell>
    </x:row>
    <x:row height="9">
      <x:cell header="true" borderStyle="lrb" contentStyle="bold header" colspan="6" colIndex="3">
        <x:data type="String"><c:write key="RB_UTASOK"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="lb" contentStyle="small header">
        <x:data type="String"><c:write key="RA_KESES"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="rb" contentStyle="small header">
        <x:data type="String"><c:write key="BS_PONT"/></x:data>
      </x:cell>
      <c:foreach items="${etapMegjelenito.etap.sorrendFuggoEtapFeladatok}" var="sfef">
        <x:cell header="true" borderStyle="lb" contentStyle="small header">
          <x:data type="String">(-)</x:data>
        </x:cell>
        <x:cell header="true" borderStyle="b" contentStyle="small header">
          <x:data type="String">(+)</x:data>
        </x:cell>
        <x:cell header="true" borderStyle="rb" contentStyle="small header">
          <x:data type="String"><c:write key="BS_PONT"/></x:data>
        </x:cell>
      </c:foreach>
      <c:foreach items="${etapMegjelenito.etap.darabFuggoEtapFeladatok}" var="dfef">
        <x:cell header="true" borderStyle="lb" contentStyle="small header">
          <x:data type="String">(-)</x:data>
        </x:cell>
        <x:cell header="true" borderStyle="b" contentStyle="small header">
          <x:data type="String">(+)</x:data>
        </x:cell>
        <x:cell header="true" borderStyle="rb" contentStyle="small header">
          <x:data type="String"><c:write key="BS_PONT"/></x:data>
        </x:cell>
      </c:foreach>
      <x:cell header="true" borderStyle="lrb" contentStyle="small header">
        <x:data type="String"><c:write key="BS_PONT"/></x:data>
      </x:cell>
    </x:row>
  </x:rowgroup>

  <c:foreach items="${etapMegjelenito.eredmenyek}" var="etapEredmeny" indexVar="hely">
    <x:rowgroup>
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="2">
          <x:data type="String"><c:write caption="${hely+1}"/>.</x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="2">
          <x:data type="Number"><c:write caption="${etapEredmeny.rajtszam}"/></x:data>
        </x:cell>
        <r:sofor nevezes="${etapEredmeny.nevezes}" clipStyle="clip"/>
        <r:navigator nevezes="${etapEredmeny.nevezes}" clipStyle="clip"/>
        <x:cell borderStyle="ltb" contentStyle="numeric" rowspan="2">
          <x:data type="String"><c:write caption="${etapEredmeny.kesesPerc}"/>'</x:data>
        </x:cell>
        <x:cell borderStyle="rtb" contentStyle="numeric" rowspan="2">
          <x:data type="Number"><c:write caption="${etapEredmeny.kesesPont}"/></x:data>
        </x:cell>
        <c:foreach items="${etapMegjelenito.etap.sorrendFuggoEtapFeladatok}" var="sfef">
          <c:expression bean="${etapEredmeny.sorrendFuggoEredmenyek}" method="findItem" param1="sfftid" param2="${sfef.sfftid}" var="sffe"/>
          <x:cell borderStyle="ltb" contentStyle="numeric" rowspan="2">
            <x:data type="Number"><c:write caption="${sffe.hiany}"/></x:data>
          </x:cell>
          <x:cell borderStyle="tb" contentStyle="numeric" rowspan="2">
            <x:data type="Number"><c:write caption="${sffe.tobblet}"/></x:data>
          </x:cell>
          <x:cell borderStyle="rtb" contentStyle="numeric" rowspan="2">
            <x:data type="Number"><c:write caption="${sffe.pontszam}"/></x:data>
          </x:cell>
        </c:foreach>
        <c:foreach items="${etapMegjelenito.etap.darabFuggoEtapFeladatok}" var="dfef">
          <c:expression bean="${etapEredmeny.darabFuggoEredmenyek}" method="findItem" param1="dfftid" param2="${dfef.dfftid}" var="dffe"/>
          <x:cell borderStyle="ltb" contentStyle="numeric" rowspan="2">
            <x:data type="Number"><c:write caption="${dffe.hiany}"/></x:data>
          </x:cell>
          <x:cell borderStyle="tb" contentStyle="numeric" rowspan="2">
            <x:data type="Number"><c:write caption="${dffe.tobblet}"/></x:data>
          </x:cell>
          <x:cell borderStyle="rtb" contentStyle="numeric" rowspan="2">
            <x:data type="Number"><c:write caption="${dffe.pontszam}"/></x:data>
          </x:cell>
        </c:foreach>
        <x:cell borderStyle="lrtb" contentStyle="numeric" rowspan="2">
          <x:data type="Number"><c:write caption="${etapEredmeny.buntetoPont}"/></x:data>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="2">
          <x:data type="Number"><c:write caption="${etapEredmeny.pontszam}"/></x:data>
        </x:cell>
      </x:row>
      <x:row height="9">
        <r:utasok nevezes="${etapEredmeny.nevezes}" colIndex="3" clipStyle="clip"/>
      </x:row>
    </x:rowgroup>
  </c:foreach>

</x:table>
