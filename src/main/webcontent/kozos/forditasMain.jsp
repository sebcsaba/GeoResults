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
      <l:simpleform title="DC_VALASSZ_CSOPORTOT">
        <l:tablefield>
          <p>- <c:link action="/kozos/forditas.do?group=BS"><c:write key="DC_BS_TITLE"/></c:link></p>
          <p>- <c:link action="/kozos/forditas.do?group=DC"><c:write key="DC_DC_TITLE"/></c:link></p>
          <p>- <c:link action="/kozos/forditas.do?group=ER"><c:write key="DC_ER_TITLE"/></c:link></p>
          <p>- <c:link action="/kozos/forditas.do?group=FT"><c:write key="DC_FT_TITLE"/></c:link></p>
          <p>- <c:link action="/kozos/forditas.do?group=IF"><c:write key="DC_IF_TITLE"/></c:link></p>
          <p>- <c:link action="/kozos/forditas.do?group=RA"><c:write key="DC_RA_TITLE"/></c:link></p>
          <p>- <c:link action="/kozos/forditas.do?group=RB"><c:write key="DC_RB_TITLE"/></c:link></p>
          <p>- <c:link action="/kozos/forditas.do?group=LG"><c:write key="DC_LG_TITLE"/></c:link></p>
          <p>- <c:link action="/kozos/forditas.do?group=CY"><c:write key="DC_CY_TITLE"/></c:link></p>
        </l:tablefield>
      </l:simpleform>
    </div>
  </body>
</html>
