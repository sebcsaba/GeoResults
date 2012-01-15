<%@page contentType="text/html; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/jspheader.jspf"%>
<html>
  <head>
    <%@include file="/common/header.jspf"%>
    <style type="text/css">
      .panel {
        width: 354px;
      }
      .row {
        border-bottom: 1px solid azure;
      }
      .formRow {
        width: 322px;
      }
      .col {
        background: #E0F8FF;
        border-right: 1px solid azure;
      }
      .labelCol {
        width: 120px;
      }
      .popupCol {
        width: 120px;
      }
      .countCol {
        width: 40px;
      }
      .spinCol {
        width: 20px;
      }
      .spin {
        background: lightblue;
        text-align: center;
        cursor: pointer;
      }
      INPUT.count {
        width: 30px;
      }
      .buttonBar {
        margin-top: 8px;
      }
    </style>
    <script type="text/javascript">

      function doUp(div) {
        row = findParent(div.parentNode.parentNode,'DIV');
        index = findNodeIndex(row);
        container = row.parentNode;
        if (index>0) {
          swapDivs(container.childNodes[index-1],container.childNodes[index]);
        }
      }

      function doDown(div) {
        row = findParent(div.parentNode.parentNode,'DIV');
        index = findNodeIndex(row);
        container = row.parentNode;
        if (index<container.childNodes.length-1) {
          swapDivs(container.childNodes[index],container.childNodes[index+1]);
        }
      }

      function swapDivs(div1, div2)
      {
        div2.parentNode.removeChild(div2);
        div1.parentNode.insertBefore(div2,div1);
      }

      function pack()
      {
        panel = document.getElementById('panel');
        result = "";
        for ( i=0; i<panel.childNodes.length; ++i) {
          if (i>0) result += ";";
          result += getRowStr(panel.childNodes[i]);
        }
        mlf = window.opener.document.getElementById('menetlevelformula');
        mlf.value = result;
        window.opener.doChangeSimple(mlf,null,'<c:write key="BS_MODOSITVA"/>');
        window.opener.closePopup();
      }

      function getRowStr(row) {
        popupBox = document.getElementById(row.id+'_popup');
        countBox = document.getElementById(row.id+'_count');
        if (row.id=="i") return row.id;
        popupStr = ( popupBox.checked ? "p" : "i" );
        if (row.id=="b") return row.id + ":" + popupStr;
        if (row.id[0]=="d") return row.id + ":" + popupStr;
        if (row.id[0]=="s") return row.id + ":" + popupStr + ":" + countBox.value;
      }

    </script>
  </head>
  <body onunload="window.opener.closePopup();">
    <l:simpleform title="FT_MENETLEVEL_FORMULA">
      <l:tablefield>
        <geo:formulaeditor/>
        <div class="buttonBar">
          <f:button name="pack" title="BS_OK" onclick="pack();"/>
          <f:button name="back" title="BS_VISSZA" onclick="window.opener.closePopup();"/>
        </div>
      </l:tablefield>
    </l:simpleform>
  </body>
</html>
