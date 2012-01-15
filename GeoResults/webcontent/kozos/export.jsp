<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <f:form id="export" action="/kozos/doAdatokExport">
        <l:simpleform title="FT_ADATOK_EXPORTALASA">
          <l:field title="FT_MIT_EXPORTALJON">
            <f:radiogroup name="mit" value="">
              <f:item value="kozos" title="DC_NYELVI_ADATOK"/>
              <f:items items="${formUtils.versenyek}" labelPropertyName="nev" valuePropertyName="vid"/>
            </f:radiogroup>
          </l:field>
          <l:field title="FT_MILYEN_FORMABA">
            <f:radiogroup name="mibe" value="">
              <f:item value="geoxml" caption="GeoResults 2006 XML"/>
              <f:item value="sql" caption="SQL Insert"/>
              <f:item value="exml" caption="Ms Excel 2003 XML"/>
            </f:radiogroup>
          </l:field>
          <l:field>
            <f:submit name="send" title="FT_EXPORT"/>
          </l:field>
        </l:simpleform>
      </f:form>
    </div>
  </body>
</html>
