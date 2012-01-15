<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/outer.css" />
    <style type="text/css">
      .error {
        width: 400px;
        margin: 0px;
        margin-bottom: 8px;
      }
    </style>
  </head>
  <body>
    <div id="login">
      <div class="loginBar"><img src="<c:path/>/style/logo.png"/></div>
      <geo:error/>
      <div class="loginBar panel">
        <div class="panelContent">
          <table class="formTable">
            <l:tablefield>
              <div class="choose"><c:write key="RB_VERSENY_KIVALASZTASA"/></div>
              <f:form id="versenyValasztas" action="/kulso/versenyValasztas.do">
                <f:select name="verseny" onchange="this.form.submit();" value="">
                  <f:item value="" title="BS_VALASSZ"/>
                  <f:items items="${formUtils.versenyek}" labelPropertyName="nev" valuePropertyName="vid"/>
                </f:select>
              </f:form>
            </l:tablefield>
            <l:tablefield>
              &middot; <c:link action="/kulso/ujVerseny.jsp"><c:write key="RB_UJ_VERSENY_LETREHOZASA"/></c:link>
            </l:tablefield>
          </table>
        </div>
      </div>
    </div>
    <div id="footer">
      <geo:languages action="/kulso/nyelvValtas.do" forward="versenyValasztas"/>
      <div class="copyright">GeoResults &copy; SebCsaba 2006 - <geo:version/></div>
    </div>
  </body>
</html>
