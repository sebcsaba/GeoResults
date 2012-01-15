package scs.javax.web.taglibs;

import javax.servlet.jsp.JspException;
import scs.javax.io.IOException;

public abstract class TagBaseWithXhtmlBaseAttributes extends TagBase
{

  protected String id;

  protected String styleClass;

  protected String style;

  protected String title;

  protected String lang;

  protected String xmlLang;

  protected String dir;

  protected String onclick;

  protected String ondblclick;

  protected String onmousedown;

  protected String onmouseup;

  protected String onmouseover;

  protected String onmousemove;

  protected String onmouseout;

  protected String onkeypress;

  protected String onkeydown;

  protected String onkeyup;

  public int doStartTag () throws JspException
  {
    try {
      doPrintHeader();
      return EVAL_BODY_INCLUDE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public int doEndTag () throws JspException
  {
    try {
      doPrintFooter();
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  protected abstract void doPrintHeader () throws Exception;

  protected abstract void doPrintFooter () throws Exception;

  protected void writeExistBaseAttributes () throws IOException
  {
    if ( id != null ) out.writeParam( "id", id );
    if ( styleClass != null ) out.writeParam( "class", styleClass );
    if ( style != null ) out.writeParam( "style", style );
    if ( title != null ) out.writeParam( "title", title );

    if ( lang != null ) out.writeParam( "lang", lang );
    if ( xmlLang != null ) out.writeParam( "xmlLang", xmlLang );
    if ( dir != null ) out.writeParam( "dir", dir );

    if ( onclick != null ) out.writeParam( "onclick", onclick );
    if ( ondblclick != null ) out.writeParam( "ondblclick", ondblclick );
    if ( onmousedown != null ) out.writeParam( "onmousedown", onmousedown );
    if ( onmouseup != null ) out.writeParam( "onmouseup", onmouseup );
    if ( onmouseover != null ) out.writeParam( "onmouseover", onmouseover );
    if ( onmousemove != null ) out.writeParam( "onmousemove", onmousemove );
    if ( onmouseout != null ) out.writeParam( "onmouseout", onmouseout );
    if ( onkeypress != null ) out.writeParam( "onkeypress", onkeypress );
    if ( onkeydown != null ) out.writeParam( "onkeydown", onkeydown );
    if ( onkeyup != null ) out.writeParam( "onkeyup", onkeyup );
  }

  public void setId ( String id )
  {
    this.id = id;
  }

  public void setStyleClass ( String styleClass )
  {
    this.styleClass = styleClass;
  }

  public void setStyle ( String style )
  {
    this.style = style;
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setLang ( String lang )
  {
    this.lang = lang;
  }

  public void setXmlLang ( String xmlLang )
  {
    this.xmlLang = xmlLang;
  }

  public void setDir ( String dir )
  {
    this.dir = dir;
  }

  public void setOnclick ( String onclick )
  {
    this.onclick = onclick;
  }

  public void setOndblclick ( String ondblclick )
  {
    this.ondblclick = ondblclick;
  }

  public void setOnmousedown ( String onmousedown )
  {
    this.onmousedown = onmousedown;
  }

  public void setOnmouseup ( String onmouseup )
  {
    this.onmouseup = onmouseup;
  }

  public void setOnmouseover ( String onmouseover )
  {
    this.onmouseover = onmouseover;
  }

  public void setOnmousemove ( String onmousemove )
  {
    this.onmousemove = onmousemove;
  }

  public void setOnmouseout ( String onmouseout )
  {
    this.onmouseout = onmouseout;
  }

  public void setOnkeypress ( String onkeypress )
  {
    this.onkeypress = onkeypress;
  }

  public void setOnkeydown ( String onkeydown )
  {
    this.onkeydown = onkeydown;
  }

  public void setOnkeyup ( String onkeyup )
  {
    this.onkeyup = onkeyup;
  }


  /*
        %attrs;
           %coreattrs;
              id          ID             #IMPLIED
              class       CDATA          #IMPLIED
              style       %StyleSheet;   #IMPLIED
              title       %Text;         #IMPLIED
           %i18n;
              lang        %LanguageCode; #IMPLIED
              xml:lang    %LanguageCode; #IMPLIED
              dir         (ltr|rtl)      #IMPLIED
           %events;
              onclick     %Script;       #IMPLIED
              ondblclick  %Script;       #IMPLIED
              onmousedown %Script;       #IMPLIED
              onmouseup   %Script;       #IMPLIED
              onmouseover %Script;       #IMPLIED
              onmousemove %Script;       #IMPLIED
              onmouseout  %Script;       #IMPLIED
              onkeypress  %Script;       #IMPLIED
              onkeydown   %Script;       #IMPLIED
              onkeyup     %Script;       #IMPLIED
   */

}
