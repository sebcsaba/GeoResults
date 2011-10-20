<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <l:form name="athelyezes" action="/szakasz/athelyezes.do" title="FT_ATHELYEZES_SZAKASZOK_KOZOTT">
        <l:tablefield>
          <table class="inputTable" style="width:100%;">
            <th class="inputCell"><c:write key="BS_NEV"/></th>
            <th class="inputCell"><c:write key="FT_JELENLEGI_SZAKASZ"/></th>
            <th class="inputCell"><c:write key="FT_UJ_SZAKASZ"/></th>
            <c:foreach items="${verseny.szakaszok}" var="szakasz">
              <c:foreach items="${szakasz.etapok}" var="etap">
                <tr>
                  <td class="inputCell"><c:write label="${etap.nev}"/></td>
                  <td class="inputCell"><c:write label="${szakasz.nev}"/></td>
                  <td class="inputCell">
                    <f:select name="etap${etap.eid}" value="${etap.szid}">
                      <f:items items="${verseny.szakaszok}" labelPropertyName="nev" valuePropertyName="szid"/>
                    </f:select>
                  </td>
                </tr>
              </c:foreach>
              <c:foreach items="${szakasz.szlalomok}" var="szlalom">
                <tr>
                  <td class="inputCell"><c:write label="${szlalom.nev}"/></td>
                  <td class="inputCell"><c:write label="${szakasz.nev}"/></td>
                  <td class="inputCell">
                    <f:select name="szlalom${szlalom.szlid}" value="${szlalom.szid}">
                      <f:items items="${verseny.szakaszok}" labelPropertyName="nev" valuePropertyName="szid"/>
                    </f:select>
                  </td>
                </tr>
              </c:foreach>
            </c:foreach>
          </table>
        </l:tablefield>
      </l:form>
    </div>
  </body>
</html>
