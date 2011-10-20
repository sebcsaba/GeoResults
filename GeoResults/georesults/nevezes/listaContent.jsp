<%@page errorPage="/common/error.jsp"%>
<%@include file="/common/xmlcontentheader.jspf"%>
<jsp:useBean id="displayUtils" scope="request" class="scs.georesults.logic.utils.DisplayUtils"/>
<jsp:setProperty name="displayUtils" property="pageContext" value="${pageContext}"/>

<x:table styleClass="results">

  <x:column width="12"/>
  <x:column width="34" span="6"/>
  <x:column width="30" span="2"/>

  <x:rowgroup header="true">
    <x:row height="9">
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
        <x:data type="String"><c:write key="BS_ORSZAG"/></x:data>
      </x:cell>
    </x:row>
    <x:row height="9">
      <x:cell header="true" borderStyle="lrb" contentStyle="bold header" colspan="6" colIndex="3">
        <x:data type="String"><c:write key="RB_UTASOK"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="lb" contentStyle="small header">
        <x:data type="String"><c:write key="RA_AUTO"/></x:data>
      </x:cell>
      <x:cell header="true" borderStyle="rb" contentStyle="small header">
        <x:data type="String"><c:write key="RA_KATEGORIA"/></x:data>
      </x:cell>
    </x:row>
  </x:rowgroup>

  <c:foreach items="${verseny.nevezesek}" var="nevezes">
    <x:rowgroup>
      <x:row height="9">
        <x:cell borderStyle="lrtb" contentStyle="important numeric" rowspan="2">
          <x:data type="Number"><c:write caption="${nevezes.rajtszam}"/></x:data>
        </x:cell>
        <r:sofor nevezes="${nevezes}" clipStyle="clip"/>
        <r:navigator nevezes="${nevezes}" clipStyle="clip"/>
        <c:expression bean="${displayUtils}" method="getOrszagNev" param1="${nevezes.orszag}" var="orszagNev"/>
        <x:cell borderStyle="lrtb" contentStyle="text" colspan="2">
          <x:clip styleClass="clip">
            <x:data type="String"><c:write caption="${orszagNev}"/></x:data>
          </x:clip>
        </x:cell>
      </x:row>
      <x:row height="9">
        <r:utasok nevezes="${nevezes}" colIndex="3" clipStyle="clip"/>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:clip styleClass="clip">
            <x:data type="String"><c:write caption="${nevezes.auto.nev}"/></x:data>
          </x:clip>
        </x:cell>
        <x:cell borderStyle="lrtb" contentStyle="text">
          <x:clip styleClass="clip">
            <x:data type="String"><c:write label="${nevezes.auto.szlalomKategoria.nev}"/></x:data>
          </x:clip>
        </x:cell>
      </x:row>
    </x:rowgroup>
  </c:foreach>

</x:table>
