<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<%@taglib uri="http://sebcsaba.hu/taglib/geo/importing" prefix="importing"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/importing.css" />
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <importing:wizardform percent="${importBean.stepsPercent}">
        <c:write key="IF_MSG_MELYIK_ALAPOK"/>
        <f:form action="/importing/melyikAlapok.do">
          <div class="formRow">
            <p><f:checkbox name="sorrendFuggoFeladatTipusok" value="false"/><label for="sorrendFuggoFeladatTipusok"><c:write key="RA_SORRENDFUGGO_FELADAT_TIPUSOK"/></label></p>
            <p><f:checkbox name="darabFuggoFeladatTipusok" value="false"/><label for="darabFuggoFeladatTipusok"><c:write key="RA_DARABFUGGO_FELADAT_TIPUSOK"/></label></p>
            <p><f:checkbox name="kesesiZonak" value="false"/><label for="kesesiZonak"><c:write key="RA_KESESI_ZONAK"/></label></p>
            <p><f:checkbox name="buntetesTipusok" value="false"/><label for="buntetesTipusok"><c:write key="RA_BUNTETES_TIPUSOK"/></label></p>
            <p><f:checkbox name="szlalomFeladatok" value="false"/><label for="szlalomFeladatok"><c:write key="RB_SZLALOM_FELADATOK"/></label></p>
            <p><f:checkbox name="szlalomKategoriak" value="false"/><label for="szlalomKategoriak"><c:write key="RB_SZLALOM_KATEGORIAK"/></label></p>
            <p><f:checkbox name="autoTipusok" value="false"/><label for="autoTipusok"><c:write key="RA_AUTOTIPUSOK"/></label></p>
          </div>
          <div class="formRow">
            <f:submit name="ok" title="BS_OK"/>
          </div>
        </f:form>
      </importing:wizardform>
    </div>
  </body>
</html>
