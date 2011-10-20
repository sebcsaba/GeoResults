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
      <f:form id="export" action="/eredmeny/export.do">
        <l:simpleform title="FT_EREDMENYEK_EXPORTALASA">
          <l:field title="FT_MILYEN_FORMABA">
            <f:radiogroup name="mibe" value="exml">
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
