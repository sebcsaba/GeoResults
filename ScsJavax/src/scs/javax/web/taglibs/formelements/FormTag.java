package scs.javax.web.taglibs.formelements;

import scs.javax.io.IOException;
import scs.javax.web.taglibs.TagBaseWithXhtmlBaseAttributes;

public class FormTag extends TagBaseWithXhtmlBaseAttributes
{

  protected String action;

  protected String method;

  protected String enctype;

  protected String onsubmit;

  protected String onreset;

  protected String accept;

  protected String acceptCharset;

  protected void doPrintHeader () throws IOException
  {
    out.startTag( "form" );
    writeExistBaseAttributes();
    out.writeParam( "action", action );
    if ( method != null ) out.writeParam( "method", method );
    if ( enctype != null ) out.writeParam( "enctype", enctype );
    if ( onsubmit != null ) out.writeParam( "onsubmit", onsubmit );
    if ( onreset != null ) out.writeParam( "onreset", onreset );
    if ( accept != null ) out.writeParam( "accept", accept );
    if ( acceptCharset != null ) out.writeParam( "acceptCharset", acceptCharset );
    out.closeOpenTag();
  }

  protected void doPrintFooter () throws IOException
  {
    out.endTag( "form" );
  }

  public void setAction ( String action )
  {
    this.action = action;
  }

  public void setMethod ( String method )
  {
    this.method = method;
  }

  public void setEnctype ( String enctype )
  {
    this.enctype = enctype;
  }

  public void setOnsubmit ( String onsubmit )
  {
    this.onsubmit = onsubmit;
  }

  public void setOnreset ( String onreset )
  {
    this.onreset = onreset;
  }

  public void setAccept ( String accept )
  {
    this.accept = accept;
  }

  public void setAcceptCharset ( String acceptCharset )
  {
    this.acceptCharset = acceptCharset;
  }

  /*
            %attrs;
            action      %URI;          #REQUIRED
            method      (get|post)     "get"
            enctype     %ContentType;  "application/x-www-form-urlencoded"
            onsubmit    %Script;       #IMPLIED
            onreset     %Script;       #IMPLIED
            accept      %ContentTypes; #IMPLIED
            accept-charset %Charsets;  #IMPLIED
   */

}
