<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <style type="text/css">
      .nonExists {
        font-style: italic;
      }
      .crudIconCol {
        text-align: center;
      }
    </style>
    <script type="text/javascript" src="<c:path/>/common/addonUtils.js"></script>
    <script type="text/javascript">
      function szotarBecsuk()
      {
        if (window.parent.addonUrl=='addons/szotar.jsp') {
          loadAddon('../empty.jsp');
          if (window.parent.addonShown) showOrHideAddonPanel();
        }
      }
    </script>
  </head>
  <body onload="szotarBecsuk();">
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <l:simpleform title="DC_SZOTAR">
        <l:tablefield>
          <table class="crudTable">
            <tr>
              <th class="crudHeader"><c:write key="DC_SZO"/></th>
              <c:foreach items="${formUtils.nyelvek}" var="nyelv">
                <th class="crudIconCol crudHeader"><c:write caption="${nyelv.value}"/></th>
              </c:foreach>
            </tr>
            <c:foreach items="${versenySzotar.allItems}" var="szoForditasokkal">
              <tr>
                <td class="crudCell">
                  <c:expression bean="${szoForditasokkal}" method="isVanIlyenNyelven" param1="${nyelv.lang}" var="vanFeliratAktualisNyelven"/>
                  <c:if test="${vanFeliratAktualisNyelven}">
                    <c:expression bean="${szoForditasokkal}" method="getFeliratAdottNyelven" param1="${nyelv.lang}" var="feliratAktualisNyelven"/>
                    <c:write caption="${feliratAktualisNyelven}"/>
                  </c:if>
                  <c:if test="${!vanFeliratAktualisNyelven}">
                    <c:expression bean="${szoForditasokkal}" method="getFeliratAdottNyelven" param1="${verseny.alapNyelv}" var="feliratAlapNyelven"/>
                    (<c:write caption="${feliratAlapNyelven}"/>)
                  </c:if>
                </td>
                <c:foreach items="${formUtils.nyelvek}" var="nyelv">
                  <c:expression bean="${szoForditasokkal}" method="isVanIlyenNyelven" param1="${nyelv.value}" var="vanIlyenNyelven"/>
                  <c:if test="${vanIlyenNyelven}">
                    <td class="crudIconCol">
                      <a href="<c:path/>/verseny/versenySzoForditasSzerkesztes.do?mode=edit&lang=<c:write caption="${nyelv.value}"/>&vszbid=<c:write caption="${szoForditasokkal.vszbid}"/>" title="<c:write key="DC_FORDITAS_MODOSITASA"/> <c:write key="LG_${nyelv.value}"/> <c:write key="DC_X_NYELVEN"/>"><c:write caption="${nyelv.value}"/></a>
                    </td>
                  </c:if>
                  <c:if test="${!vanIlyenNyelven}">
                    <td class="crudIconCol nonExists">
                      <a href="<c:path/>/verseny/versenySzoForditasSzerkesztes.do?mode=create&lang=<c:write caption="${nyelv.value}"/>&vszbid=<c:write caption="${szoForditasokkal.vszbid}"/>" title="<c:write key="DC_FORDITAS_LETREHOZASA"/> <c:write key="LG_${nyelv.value}"/> <c:write key="DC_X_NYELVEN"/>"><c:write caption="${nyelv.value}"/></a>
                    </td>
                  </c:if>
                </c:foreach>
              </tr>
            </c:foreach>
          </table>
        </l:tablefield>
      </l:simpleform>
    </div>
  </body>
</html>
