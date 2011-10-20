<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <link rel="stylesheet" type="text/css" href="<c:path/>/style/outer.css" />
    <style type="text/css">
      .backBar {
        margin-left: auto;
        margin-right: auto;
      }
      .panel {
        margin-left: auto;
        margin-right: auto;
      }
      #errorBox {
        width: 600px;
        margin-top: 150px;
        margin-left: auto;
        margin-right: auto;
      }
     .error {
       margin: -1px;
     }
    </style>
  </head>
  <body>
    <div id="errorBox"><geo:error/></div>
    <div class="backBar">&lt;&lt; <c:link action="/kulso/versenyValasztas.jsp"><c:write key="BS_VISSZA"/></c:link></div>
    <f:form id="verseny" action="/kulso/versenyLetrehozas.do">
      <l:simpleform title="RB_VERSENY_ADATAI">
        <l:field title="BS_NEV">
          <f:text name="nev" value=""/>
        </l:field>
        <l:field title="BS_SABLON">
          <f:select name="sablon" value="">
            <f:item value="" title="BS_VALASSZ"/>
            <f:items items="${formUtils.sablonok}" labelPropertyName="label" valuePropertyName="value"/>
          </f:select>
        </l:field>
        <l:field>
          <f:submit name="save" title="BS_LETREHOZAS"/>
        </l:field>
      </l:simpleform>
    </f:form>
    <div id="footer">
      <geo:languages action="/kulso/nyelvValtas.do" forward="versenyUj"/>
      <div class="copyright">GeoResults &copy; SebCsaba 2006 - <geo:version/></div>
    </div>
  </body>
</html>
