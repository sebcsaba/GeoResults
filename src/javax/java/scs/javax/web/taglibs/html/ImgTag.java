package scs.javax.web.taglibs.html;

import scs.javax.web.taglibs.TagBaseWithXhtmlBaseAttributes;

public class ImgTag extends TagBaseWithXhtmlBaseAttributes
{

  protected String src;

  protected void doPrintHeader () throws Exception
  {
    out.startTag( "img" );
    writeExistBaseAttributes();
    out.writeParam( "src", src );
    out.writeParam( "alt", title );
    out.endTag( "img" );
  }

  protected void doPrintFooter () throws Exception
  {}

  public void setSrc ( String src )
  {
    this.src = src;
  }

}
