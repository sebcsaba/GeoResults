package scs.javax.web.taglibs.html;

import scs.javax.web.taglibs.TagBaseWithXhtmlFocusAttributes;

public class ATag extends TagBaseWithXhtmlFocusAttributes
{

  protected String href;

  protected String hreflang;

  protected String charset;

  protected String type;

  protected String name;

  protected String rel;

  protected String rev;

  protected String shape;

  protected String coords;

  protected void doPrintHeader () throws Exception
  {
    out.startTag( "a" );
    writeExistFocusAttributes();
    if ( href != null ) out.writeParam( "href", href );
    if ( hreflang != null ) out.writeParam( "hreflang", hreflang );
    if ( charset != null ) out.writeParam( "charset", charset );
    if ( type != null ) out.writeParam( "type", type );
    if ( name != null ) out.writeParam( "name", name );
    if ( rel != null ) out.writeParam( "rel", rel );
    if ( rev != null ) out.writeParam( "rev", rev );
    if ( shape != null ) out.writeParam( "shape", shape );
    if ( coords != null ) out.writeParam( "coords", coords );
    out.closeOpenTag();
  }

  protected void doPrintFooter () throws Exception
  {
    out.endTag( "a" );
  }

  public void setCharset ( String charset )
  {
    this.charset = charset;
  }

  public void setType ( String type )
  {
    this.type = type;
  }

  public void setShape ( String shape )
  {
    this.shape = shape;
  }

  public void setRev ( String rev )
  {
    this.rev = rev;
  }

  public void setRel ( String rel )
  {
    this.rel = rel;
  }

  public void setName ( String name )
  {
    this.name = name;
  }

  public void setHreflang ( String hreflang )
  {
    this.hreflang = hreflang;
  }

  public void setHref ( String href )
  {
    this.href = href;
  }

  public void setCoords ( String coords )
  {
    this.coords = coords;
  }

}
