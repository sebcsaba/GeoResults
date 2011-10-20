package scs.javax.web.taglibs;

import scs.javax.io.IOException;

public abstract class TagBaseWithXhtmlFocusAttributes extends TagBaseWithXhtmlBaseAttributes
{

  protected String accesskey;

  protected Integer tabindex;

  protected String onfocus;

  protected String onblur;

  protected void writeExistFocusAttributes () throws IOException
  {
    writeExistFocusAttributes( true );
  }

  protected void writeExistFocusAttributes ( boolean alsoBaseAttributes ) throws IOException
  {
    if ( accesskey != null ) out.writeParam( "accesskey", accesskey );
    if ( tabindex != null ) out.writeParam( "tabindex", tabindex.toString() );
    if ( onfocus != null ) out.writeParam( "onfocus", onfocus );
    if ( onblur != null ) out.writeParam( "onblur", onblur );
    if ( alsoBaseAttributes ) writeExistBaseAttributes();
  }

  public void setAccesskey ( String accesskey )
  {
    this.accesskey = accesskey;
  }

  public void setTabindex ( Integer tabindex )
  {
    this.tabindex = tabindex;
  }

  public void setOnfocus ( String onfocus )
  {
    this.onfocus = onfocus;
  }

  public void setOnblur ( String onblur )
  {
    this.onblur = onblur;
  }

  /*
                %focus;
                   accesskey   %Character;    #IMPLIED
                   tabindex    %Number;       #IMPLIED
                   onfocus     %Script;       #IMPLIED
                   onblur      %Script;       #IMPLIED
   */

}
