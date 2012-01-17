<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
<jsp:useBean id="addonSzotar" scope="session" class="scs.georesults.logic.beans.addons.SzotarBean"/>
<jsp:setProperty name="addonSzotar" property="pageContext" value="${pageContext}"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>addonSzotar</title>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/szotar.css" />
    <style type="text/css">
      BODY {
        background: azure;
      }
      .addTo {
        border: 1px solid royalblue;
        padding: 8px;
      }
    </style>
    <script type="text/javascript">
      function doFocus(input)
      {}
      function nemJoMezo()
      {
        alert('<c:write key="ER_NINCS_SZOTARNAK_MEGFELELO_MEZO_KIJELOLVE"/>');
        return false;
      }
      function szoKivalasztas(id,label)
      {
        showInputId = window.parent.activeInputId;
        if (showInputId==null) return nemJoMezo();
        dictInputId = showInputId+"_dict";
        mainDoc = window.parent.frames['main'].document;
        showInput = mainDoc.getElementById(showInputId);
        dictInput = mainDoc.getElementById(dictInputId);
        if (showInput==null || dictInput==null) return nemJoMezo();
        showInput.value = label;
        if (showInput.onkeypress!=null) showInput.onkeypress();
        dictInput.value = '@'+id;
        showInput.style.backgroundColor='whitesmoke';
        return true;
      }
      function szoFelvitelElott()
      {
        showInputId = window.parent.activeInputId;
        if (showInputId==null) return nemJoMezo();
        dictInputId = showInputId+"_dict";
        mainDoc = window.parent.frames['main'].document;
        showInput = mainDoc.getElementById(showInputId);
        dictInput = mainDoc.getElementById(dictInputId);
        if (showInput==null || dictInput==null) return nemJoMezo();
        ujSzo = document.getElementById('uj_szo');
        ujSzo.value = showInput.value;
        return true;
      }
    </script>
  </head>
  <body>
    <div class="addTo">
      <f:form id="ujSzoForm" action="/verseny/versenySzoFelvitele.do" onsubmit="return szoFelvitelElott();">
        <f:submit name="ujSzoFelvitel" title="DC_SZO_FELVITELE"/>
        <f:hidden name="uj_szo" value=""/>
      </f:form>
    </div>
    <c:foreach limit="${addonSzotar.panelCount}" indexVar="panel">
      <c:expression bean="${addonSzotar}" method="setPanelIndex" param1="${panel}"/>
      <c:if test="${!addonSzotar.uresPanel}">
        <div class="szotarPanel">
          <table>
            <tr>
              <th class="word"><c:write key="DC_SZO"/></th>
              <c:foreach items="${formUtils.nyelvek}" var="nyelv">
                <th class="lang"><c:write caption="${nyelv.value}"/></th>
              </c:foreach>
            </tr>
            <c:foreach items="${addonSzotar.panelItems}" var="szoForditasokkal">
              <c:expression bean="${szoForditasokkal}" method="getFeliratAdottNyelven" param1="${nyelv.lang}" var="feliratAktualisNyelven"/>
              <tr>
                <td class="word"><span class="word" onclick="szoKivalasztas(<c:write caption="${szoForditasokkal.vszbid}"/>,'<c:write caption="${feliratAktualisNyelven}"/>');"><c:write caption="${feliratAktualisNyelven}"/></span></td>
                <c:foreach items="${formUtils.nyelvek}" var="nyelv">
                  <c:expression bean="${szoForditasokkal}" method="isVanIlyenNyelven" param1="${nyelv.value}" var="vanIlyenNyelven"/>
                  <c:if test="${vanIlyenNyelven}">
                    <td class="lang" title="<c:write key="DC_FORDITAS_ELERHETO"/> <c:write key="LG_${nyelv.value}"/> <c:write key="DC_X_NYELVEN"/>"><c:write caption="${nyelv.value}"/></td>
                  </c:if>
                  <c:if test="${!vanIlyenNyelven}">
                    <td class="lang" title="<c:write key="DC_FORDITAS_NEM_ELERHETO"/> <c:write key="LG_${nyelv.value}"/> <c:write key="DC_X_NYELVEN"/>">&nbsp;</td>
                  </c:if>
                </c:foreach>
              </tr>
            </c:foreach>
          </table>
        </div>
      </c:if>
    </c:foreach>
  </body>
</html>
<c:expression bean="${addonSzotar}" method="releasePageContext"/>
