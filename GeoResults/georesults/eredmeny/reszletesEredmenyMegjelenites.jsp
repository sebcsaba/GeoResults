<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="reszletesMegjelenito" scope="request" class="scs.georesults.logic.beans.eredmeny.ReszletesMegjelenitoBean"/>
<jsp:setProperty name="reszletesMegjelenito" property="pageContext" value="${pageContext}"/>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/title.css" />
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/megjelenites.css" />
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/megjelenitesReszletes.css" />
  </head>
  <body>
    <%@include file="/common/eredmenyHeader.jspf"%>
    <c:foreach items="${verseny.nevezesek}" var="nevezes">
      <div class="autoPage">
        <div class="pageHeader"><r:header title="RB_RESZLETES_EREDMENYEK"/></div>
        <div class="pageHeader autoHeader"><c:write caption="${nevezes.rajtszamEsMindenki}"/></div>
        <c:foreach items="${verseny.etapok}" var="etap">
          <c:if test="${etap.ertekelendo}">
            <c:expression bean="${reszletesMegjelenito}" method="getForEtap" param1="${etap.eid}" var="etapAdat"/>
            <c:expression bean="${reszletesMegjelenito}" method="getMenetlevel" param1="${etap.eid}" param2="${nevezes.rajtszam}" var="menetlevel"/>
            <c:expression bean="${reszletesMegjelenito}" method="getEtapEredmeny" param1="${menetlevel.eid}" param2="${menetlevel.rajtszam}" var="etapEredmeny"/>
            <c:expression bean="${reszletesMegjelenito}" method="getHelyezes" param1="${etap.eid}" param2="${nevezes.rajtszam}" var="helyezes"/>
            <div class="etapData">
              <table class="etapTable">
                <tr>
                  <td class="etapHeader"><c:write label="${etap.nev}"/></td>
                  <td class="etapHeader"><c:write key="BS_HELY"/>: <c:write caption="${helyezes}"/>.</td>
                  <td class="etapHeader"><c:write key="BS_PONT"/>: <c:write caption="${etapEredmeny.pontszam}"/></td>
                </tr>
                <tr>
                  <td class="etapData" colspan="3">
                    <div class="resultBox">
                      <table class="resultTable">
                        <tr>
                          <th class="resultCell" colspan="4"><c:write key="BS_IDO"/></th>
                        </tr>
                        <tr>
                          <th class="resultCell"><c:write key="RA_INDULAS"/></th>
                          <th class="resultCell"><c:write key="RA_ERKEZES"/></th>
                          <th class="resultCell"><c:write key="RA_KESES"/></th>
                          <th class="resultCell"><c:write key="BS_PONT"/></th>
                        </tr>
                        <tr>
                          <td class="resultCell"><c:write caption="${menetlevel.indulasiIdo.hourMinuteString}"/></td>
                          <td class="resultCell"><c:write caption="${menetlevel.erkezesiIdo.hourMinuteString}"/></td>
                          <td class="resultCell"><c:write caption="${etapEredmeny.kesesPerc}"/></td>
                          <td class="resultCell"><c:write caption="${etapEredmeny.kesesPont}"/></td>
                        </tr>
                      </table>
                    </div>
                    <div class="resultBox">
                      <table class="resultTable">
                        <tr>
                          <th class="resultCell" colspan="<c:write caption="${menetlevel.nemNullaBuntetesekSzama+1}"/>"><c:write key="RA_BUNTETES"/></th>
                        </tr>
                        <tr>
                          <c:foreach items="${menetlevel.buntetesek}" var="buntetes">
                            <c:if test="${buntetes.darab!=0}">
                              <th class="resultCell"><c:write label="${buntetes.buntetesTipus.nev}"/></th>
                            </c:if>
                          </c:foreach>
                          <th class="resultCell"><c:write key="BS_PONT"/></th>
                        </tr>
                        <tr>
                          <c:foreach items="${menetlevel.buntetesek}" var="buntetes">
                            <c:if test="${buntetes.darab!=0}">
                              <td class="resultCell"><c:write caption="${buntetes.darab}"/></td>
                            </c:if>
                          </c:foreach>
                          <td class="resultCell"><c:write caption="${etapEredmeny.buntetoPont}"/></td>
                        </tr>
                      </table>
                    </div>
                    <c:foreach items="${etapAdat.nemReszletesDfefk}" var="dfef">
                      <c:expression bean="${etapEredmeny.darabFuggoEredmenyek}" method="findItem" param1="dfftid" param2="${dfef.dfftid}" var="dffe"/>
                      <div class="resultBox">
                        <table class="resultTable">
                          <tr>
                            <th class="resultCell" colspan="3"><c:write label="${dfef.darabFuggoFeladatTipus.nev}"/></th>
                          </tr>
                          <tr>
                            <th class="resultCell"><c:write key="RA_HIANY"/></th>
                            <th class="resultCell"><c:write key="RA_TOBBLET"/></th>
                            <th class="resultCell"><c:write key="BS_PONT"/></th>
                          </tr>
                          <tr>
                            <td class="resultCell"><c:write caption="${dffe.hiany}"/></td>
                            <td class="resultCell"><c:write caption="${dffe.tobblet}"/></td>
                            <td class="resultCell"><c:write caption="${dffe.pontszam}"/></td>
                          </tr>
                        </table>
                      </div>
                    </c:foreach>
                    <c:foreach items="${etapAdat.nemReszletesSfefk}" var="sfef">
                      <c:expression bean="${etapEredmeny.sorrendFuggoEredmenyek}" method="findItem" param1="sfftid" param2="${sfef.sfftid}" var="sffe"/>
                      <div class="resultBox">
                        <table class="resultTable">
                          <tr>
                            <th class="resultCell" colspan="3"><c:write label="${sfef.sorrendFuggoFeladatTipus.nev}"/></th>
                          </tr>
                          <tr>
                            <th class="resultCell"><c:write key="RA_HIANY"/></th>
                            <th class="resultCell"><c:write key="RA_TOBBLET"/></th>
                            <th class="resultCell"><c:write key="BS_PONT"/></th>
                          </tr>
                          <tr>
                            <td class="resultCell"><c:write caption="${sffe.hiany}"/></td>
                            <td class="resultCell"><c:write caption="${sffe.tobblet}"/></td>
                            <td class="resultCell"><c:write caption="${sffe.pontszam}"/></td>
                          </tr>
                        </table>
                      </div>
                    </c:foreach>
                  </td>
                </tr>
                <c:foreach items="${etapAdat.reszletesDfefk}" var="dfef">
                  <c:expression bean="${etapEredmeny.darabFuggoEredmenyek}" method="findItem" param1="dfftid" param2="${dfef.dfftid}" var="dffe"/>
                  <c:expression bean="${menetlevel.darabFuggoMegoldasok}" method="findItem" param1="dfftid" param2="${dfef.dfftid}" var="dffmo"/>
                  <tr>
                    <td class="etapData" colspan="3">
                      <div class="resultBox">
                        <table class="resultTable">
                          <tr>
                            <th class="resultCell" colspan="3"><c:write label="${dfef.darabFuggoFeladatTipus.nev}"/></th>
                          </tr>
                          <tr>
                            <th class="resultCell"><c:write key="RA_HIANY"/></th>
                            <th class="resultCell"><c:write key="RA_TOBBLET"/></th>
                            <th class="resultCell"><c:write key="BS_PONT"/></th>
                          </tr>
                          <tr>
                            <td class="resultCell"><c:write caption="${dffe.hiany}"/></td>
                            <td class="resultCell"><c:write caption="${dffe.tobblet}"/></td>
                            <td class="resultCell"><c:write caption="${dffe.pontszam}"/></td>
                          </tr>
                        </table>
                      </div>
                      <div class="detailResultBox">
                        <table class="resultTable">
                          <r:darabFuggoReszletes bejegyzesek="${dffmo.bejegyzesek}" dfef="${dfef}"/>
                        </table>
                      </div>
                    </td>
                  </tr>
                </c:foreach>
                <c:foreach items="${etapAdat.reszletesSfefk}" var="sfef">
                  <c:expression bean="${etapEredmeny.sorrendFuggoEredmenyek}" method="findItem" param1="sfftid" param2="${sfef.sfftid}" var="sffe"/>
                  <tr>
                    <td class="etapData" colspan="3">
                      <div class="resultBox">
                        <table class="resultTable">
                          <tr>
                            <th class="resultCell" colspan="3"><c:write label="${sfef.sorrendFuggoFeladatTipus.nev}"/></th>
                          </tr>
                          <tr>
                            <th class="resultCell"><c:write key="RA_HIANY"/></th>
                            <th class="resultCell"><c:write key="RA_TOBBLET"/></th>
                            <th class="resultCell"><c:write key="BS_PONT"/></th>
                          </tr>
                          <tr>
                            <td class="resultCell"><c:write caption="${sffe.hiany}"/></td>
                            <td class="resultCell"><c:write caption="${sffe.tobblet}"/></td>
                            <td class="resultCell"><c:write caption="${sffe.pontszam}"/></td>
                          </tr>
                        </table>
                      </div>
                      <div class="detailResultBox">
                        <table class="resultTable">
                          <r:sorrendFuggoReszletes kiertekeles="${sffe.kiertekeles}"/>
                        </table>
                      </div>
                    </td>
                  </tr>
                </c:foreach>
              </table>
            </div>
          </c:if>
        </c:foreach>
        <div class="copyright">GeoResults - &copy; SebCsaba 2006</div>
      </div>
    </c:foreach>
  </body>
</html>
