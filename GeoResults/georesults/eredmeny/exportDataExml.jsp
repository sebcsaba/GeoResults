<%@page contentType="text/xml; charset=UTF-8" errorPage="/common/error.jsp"%>
<%@include file="/common/xmlcontentheader.jspf"%>
<% response.setHeader( "Content-Disposition", "attachment; filename=\"" + "export.xml" + "\"" ); %>
<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet" xmlns:html="http://www.w3.org/TR/REC-html40" xmlns:u1="urn:schemas-microsoft-com:office:spreadsheet ">
  <x:documentProperties title="${verseny.nev}" author="GeoResults 2006"/>
  <Styles>
    <Style ss:ID="s_lrtb_important_numeric">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
      <Font ss:Bold="1"/>
    </Style>
    <Style ss:ID="s_lrtb_numeric">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_ltb_numeric">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_tb_numeric">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_rtb_numeric">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_lrtb_text">
      <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_ltb_text">
      <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_rtb_text">
      <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_lt_text">
      <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Dot" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_rt_text">
      <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Dot" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_lrb_text">
      <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_lb_text">
      <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_b_text">
      <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_rb_text">
      <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_lrtb_bold_header">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
      <Font ss:Bold="1"/>
    </Style>
    <Style ss:ID="s_lt_bold_header">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Dot" ss:Weight="1"/>
      </Borders>
      <Font ss:Bold="1"/>
    </Style>
    <Style ss:ID="s_rt_bold_header">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Dot" ss:Weight="1"/>
      </Borders>
      <Font ss:Bold="1"/>
    </Style>
    <Style ss:ID="s_lrb_bold_header">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
      <Font ss:Bold="1"/>
    </Style>
    <Style ss:ID="s_lrtb_header">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_lrt_header">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Dot" ss:Weight="1"/>
      </Borders>
    </Style>
    <Style ss:ID="s_lrtb_left_small_header">
      <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
      <Font ss:Size="9"/>
    </Style>
    <Style ss:ID="s_lrb_small_header">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
      <Font ss:Size="9"/>
    </Style>
    <Style ss:ID="s_lb_small_header">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
      <Font ss:Size="9"/>
    </Style>
    <Style ss:ID="s_rb_small_header">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
      <Font ss:Size="9"/>
    </Style>
    <Style ss:ID="s_b_small_header">
      <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
      <Borders>
        <Border ss:Position="Left" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Right" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Top" ss:LineStyle="Dot" ss:Weight="1"/>
        <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
      </Borders>
      <Font ss:Size="9"/>
    </Style>
  </Styles>

  <c:foreach items="${verseny.szlalomok}" var="szlalom">
    <jsp:useBean id="szlalomMegjelenito" scope="request" class="scs.georesults.logic.beans.eredmeny.SzlalomMegjelenitoBean"/>
    <jsp:setProperty name="szlalomMegjelenito" property="request" value="${pageContext.request}"/>
    <jsp:setProperty name="szlalomMegjelenito" property="id" value="${szlalom.szlid}"/>
    <c:foreach items="${szlalomMegjelenito.eredmenyek}" var="kategoriaEredmeny">
      <Worksheet ss:Name="<c:write label="${szlalom.nev}"/> - <c:write label="${kategoriaEredmeny.kategoria.nev}"/>">
        <jsp:include page="content/szlalom.jsp" flush="true">
          <jsp:param name="type" value="xml"/>
        </jsp:include>
        <x:pageSetup/>
      </Worksheet>
    </c:foreach>
  </c:foreach>

  <c:foreach items="${verseny.etapok}" var="etap">
    <jsp:useBean id="etapMegjelenito" scope="request" class="scs.georesults.logic.beans.eredmeny.EtapMegjelenitoBean"/>
    <jsp:setProperty name="etapMegjelenito" property="request" value="${pageContext.request}"/>
    <jsp:setProperty name="etapMegjelenito" property="id" value="${etap.eid}"/>
    <Worksheet ss:Name="<c:write label="${etap.nev}"/>">
      <jsp:include page="content/etap.jsp" flush="true">
        <jsp:param name="type" value="xml"/>
      </jsp:include>
      <x:pageSetup/>
    </Worksheet>
  </c:foreach>

  <c:foreach items="${verseny.szakaszok}" var="szakasz">
    <jsp:useBean id="szakaszMegjelenito" scope="request" class="scs.georesults.logic.beans.eredmeny.SzakaszMegjelenitoBean"/>
    <jsp:setProperty name="szakaszMegjelenito" property="request" value="${pageContext.request}"/>
    <jsp:setProperty name="szakaszMegjelenito" property="id" value="${szakasz.szid}"/>
    <Worksheet ss:Name="<c:write label="${szakasz.nev}"/>">
      <jsp:include page="content/szakasz.jsp" flush="true">
        <jsp:param name="type" value="xml"/>
      </jsp:include>
      <x:pageSetup/>
    </Worksheet>
  </c:foreach>

  <c:foreach items="${verseny.szakaszok}" var="szakasz">
    <jsp:useBean id="szlalomOsszesitettMegjelenito" scope="request" class="scs.georesults.logic.beans.eredmeny.SzlalomOsszesitettMegjelenitoBean"/>
    <jsp:setProperty name="szlalomOsszesitettMegjelenito" property="request" value="${pageContext.request}"/>
    <jsp:setProperty name="szlalomOsszesitettMegjelenito" property="id" value="${szakasz.szid}"/>
    <c:foreach items="${szlalomOsszesitettMegjelenito.eredmenyek}" var="kategoriaEredmeny">
      <Worksheet ss:Name="<c:write label="${szakasz.nev}"/> - <c:write label="${kategoriaEredmeny.kategoria.nev}"/> - <c:write key="RB_SZLALOM_OSSZESITETT_ROVID"/>">
        <jsp:include page="content/szlalomOsszesites.jsp" flush="true">
          <jsp:param name="type" value="xml"/>
        </jsp:include>
        <x:pageSetup/>
      </Worksheet>
    </c:foreach>
  </c:foreach>

  <jsp:useBean id="mindenEtapMegjelenito" scope="request" class="scs.georesults.logic.beans.eredmeny.MindenEtapMegjelenitoBean"/>
  <jsp:setProperty name="mindenEtapMegjelenito" property="pageContext" value="${pageContext}"/>
  <Worksheet ss:Name="<c:write key="RB_MINDEN_ETAP_EREDMENY"/>">
    <jsp:include page="content/mindenEtapEredmeny.jsp" flush="true">
      <jsp:param name="type" value="xml"/>
    </jsp:include>
    <x:pageSetup/>
  </Worksheet>

  <jsp:useBean id="mindenSzlalomMegjelenito" scope="request" class="scs.georesults.logic.beans.eredmeny.MindenSzlalomMegjelenitoBean"/>
  <jsp:setProperty name="mindenSzlalomMegjelenito" property="pageContext" value="${pageContext}"/>
  <c:foreach items="${mindenSzlalomMegjelenito.eredmenyek}" var="kategoriaEredmeny">
    <Worksheet ss:Name="<c:write key="RB_MINDEN_SZLALOM_EREDMENY"/> - <c:write label="${kategoriaEredmeny.kategoria.nev}"/>">
      <jsp:include page="content/mindenSzlalomEredmeny.jsp" flush="true">
        <jsp:param name="type" value="xml"/>
      </jsp:include>
      <x:pageSetup/>
    </Worksheet>
  </c:foreach>

  <jsp:useBean id="versenyMegjelenito" scope="request" class="scs.georesults.logic.beans.eredmeny.VersenyMegjelenitoBean"/>
  <jsp:setProperty name="versenyMegjelenito" property="request" value="${pageContext.request}"/>
  <Worksheet ss:Name="<c:write key="RB_VEGEREDMENY"/>">
    <jsp:include page="content/vegeredmeny.jsp" flush="true">
      <jsp:param name="type" value="xml"/>
    </jsp:include>
    <x:pageSetup/>
  </Worksheet>

  <jsp:useBean id="csapatMegjelenito" scope="request" class="scs.georesults.logic.beans.eredmeny.CsapatMegjelenitoBean"/>
  <jsp:setProperty name="csapatMegjelenito" property="request" value="${pageContext.request}"/>
  <Worksheet ss:Name="<c:write key="RB_CSAPAT_EREDMENY"/>">
    <jsp:include page="content/csapat.jsp" flush="true">
      <jsp:param name="type" value="xml"/>
    </jsp:include>
    <x:pageSetup/>
  </Worksheet>

</Workbook>
