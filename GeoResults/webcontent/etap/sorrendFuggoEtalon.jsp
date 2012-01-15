<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <script type="text/javascript" src="<c:path/>/common/checkUtils.js"></script>
    <style type="text/css">
      .panel {
        width: 300px;
      }
      .backBar {
        width: 290px;
      }
    </style>
  </head>
  <%
    Integer ellenorzesTipus = Integer.valueOf( request.getParameter( "ellenorzesTipus" ) );
    pageContext.setAttribute( "ellenorzesTipus", ellenorzesTipus, PageContext.REQUEST_SCOPE );
  %>
  <body onunload="window.opener.closePopup();">
    <f:form id="etalon" action="/etap/sorrendFuggoEtalon.do" onsubmit="this.parentNode.attributes['onunload'].nodeValue='';">
      <l:simpleform title="RA_ETALON">
        <l:tablefield label="${reszAdat.extra.nev}">
          <tf:inputtable count="${reszAdat.darab}" items="${reszAdat.resz.bejegyzesek}" default="${reszAdat.defBejegyzes}">
            <tf:countercolumn start="1"/>
            <tf:inputcolumn title="BS_FELIRAT">
              <tf:bejegyzes property="felirat" name="felirat" etalon="true" sfef="${reszAdat.extra.sfef}" ellenorzesTipus="${ellenorzesTipus}"> </tf:bejegyzes>
            </tf:inputcolumn>
            <tf:inputcolumn title="RA_ERVENYES">
              <tf:checkbox property="ervenyes" name="ervenyes"/>
            </tf:inputcolumn>
          </tf:inputtable>
          <div class="enlargeBox">
            <f:hidden name="ellenorzesTipus" value="${ellenorzesTipus}"/>
            <f:submit name="enlarge" title="BS_MENTES_ES_MEGNOVEL"/>
          </div>
        </l:tablefield>
      </l:simpleform>
      <geo:ellenorzopontlistak/>
      <div class="backBar">
        <f:submit name="save" title="BS_MENTES" accesskey="s" popuptitle="Alt+S"/>
        <f:button name="back" title="BS_VISSZA" onclick="window.opener.closePopup();"/>
      </div>
    </f:form>
  </body>
</html>
