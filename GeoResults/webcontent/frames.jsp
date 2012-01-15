<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%
  String main = request.getParameter("main");
  if (main!=null) {
    main = request.getContextPath() + main;
  } else {
    main = "main.jsp";
  }
  String openedMenu = request.getParameter("openedMenu");
  if (openedMenu==null) {
    openedMenu = "null";
  } else {
    openedMenu = "'"+openedMenu+"'";
  }
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="shortcut icon" href="style/favicon.ico" />
    <title>GeoResults 2006</title>
    <script type="text/javascript">
      var activeInputId = null;
      var addonShown = false;
      var addonPrevSize = 120;
      var addonUrl = null;
      var openedMenu = <%= openedMenu %>;
      var popupWindow = null;
      var titlePrevSize = 0;
    </script>
  </head>
  <frameset id="frameset" rows="63,*,0" border="1">
    <frame id="frame_title" name="title" src="title.jsp" noresize="noresize" scrolling="no"/>
    <frame id="frame_main" name="main" src="<%=main%>"/>
    <frame id="frame_addon" name="addon" src="empty.jsp" noresize="noresize"/>
  </frameset>
</html>
