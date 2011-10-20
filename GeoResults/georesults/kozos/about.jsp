<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<jsp:useBean id="formUtils" scope="request" class="scs.georesults.logic.utils.FormUtils"/>
<jsp:setProperty name="formUtils" property="pageContext" value="${pageContext}"/>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <style type="text/css">
      .progTitle {
        font-size: 18pt;
        font-style: italic;
      }
      TD {
        padding: 3px;
      }
    </style>
  </head>
  <body>
    <%@include file="/common/menu.jspf"%>
    <div class="main">
      <geo:error/>
      <l:simpleform>
        <p class="progTitle">GeoResults 2006</p>
        <p><c:write key="FT_ABOUT_TEXT"/></p>
        <p>&copy; 2006 Sebestyén Csaba. <c:write key="FT_ABOUT_COPYRIGHT"/></p>
        <table>
          <tr><td><c:write key="FT_ABOUT_PROGRAM_BY"/></td><td>Sebestyén Csaba</td></tr>
          <tr><td><c:write key="FT_ABOUT_DESIGN_BY"/></td><td>Pék Ágnes</td></tr>
          <tr><td><c:write key="FT_ABOUT_TEST_BY"/></td><td>Lőki Dániel</td></tr>
        </table>
      </l:simpleform>
    </div>
  </body>
</html>
