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
      <l:simpleform>
        <l:tablefield title="DC_VALASSZ_NYELVET">
          <geo:languages action="/kozos/belsoNyelvValtas.do" forward="framesreload"/>
        </l:tablefield>
      </l:simpleform>
    </div>
  </body>
</html>
