<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="reszletesMain" scope="session" class="scs.georesults.logic.beans.eredmeny.ReszletesMainBean"/>
<jsp:setProperty name="reszletesMain" property="pageContext" value="${pageContext}"/>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <c:if test="${reszletesMain.openJavascript}">
        <script type="text/javascript">
          popupResults('<c:path/>/eredmeny/reszletesEredmenyMegjelenites.jsp');
        </script>
      </c:if>
      <c:if test="${reszletesMain.barmelyEtapFrissitendo}">
        <div class="error"><c:write key="ER_NEM_MEGJELENITHETO_MERT_FRISSITENDO"/></div>
      </c:if>
      <c:if test="${!reszletesMain.barmelyEtapFrissitendo}">
        <f:form id="reszletes" action="/eredmeny/reszletesMain.do" method="post">
          <c:foreach items="${reszletesMain.etapok}" var="etap">
            <c:if test="${etap.ertekelendo}">
              <l:simpleform label="${etap.nev}">
                <c:foreach items="${etap.darabFuggoEtapFeladatok}" var="dfef">
                  <l:field label="${dfef.darabFuggoFeladatTipus.nev}">
                    <f:checkbox name="detail_${etap.eid}_d${dfef.dfftid}" value="${reszletesMain.etapFlags[etap.eid].dfef[dfef.dfftid]}"/>
                    <label for="detail_${etap.eid}_d${dfef.dfftid}"><c:write key="RB_RESZLETES"/></label>
                  </l:field>
                </c:foreach>
                <c:foreach items="${etap.sorrendFuggoEtapFeladatok}" var="sfef">
                  <l:field label="${sfef.sorrendFuggoFeladatTipus.nev}">
                    <f:checkbox name="detail_${etap.eid}_s${sfef.sfftid}" value="${sfef.reszletesBevitel && reszletesMain.etapFlags[etap.eid].sfef[sfef.sfftid]}" disabled="${!sfef.reszletesBevitel}"/>
                    <label for="detail_${etap.eid}_s${sfef.sfftid}"><c:write key="RB_RESZLETES"/></label>
                  </l:field>
                </c:foreach>
              </l:simpleform>
            </c:if>
          </c:foreach>
          <l:simpleform>
            <f:submit name="show" title="BS_MEGJELENITES"/>
          </l:simpleform>
        </f:form>
      </c:if>
    </div>
  </body>
</html>
<c:expression bean="${reszletesMain}" method="releasePageContext"/>
