package scs.javax.io.writers;

import scs.javax.io.IOException;
import scs.javax.io.Writer;

public class HtmlWriter extends XmlWriter
{

  public HtmlWriter ( Writer out )
  {
    super( out );
  }

  public void startDivWithClass ( String className ) throws IOException
  {
    startTag( "div" );
    writeParam( "class", className );
  }

  public void endDiv () throws IOException
  {
    endTag( "div" );
  }

  public void writeDivWithClassAndText ( String className, String text ) throws IOException
  {
    startDivWithClass( className );
    writeString( text );
    endDiv();
  }

}
