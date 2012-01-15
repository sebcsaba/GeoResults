package scs.javax.io.writers;

import scs.javax.io.IOException;
import scs.javax.io.Writer;

public class XmlWriter extends ExtensibleWriter
{

  private boolean tagOpened;

  public XmlWriter ( Writer out )
  {
    super( out );
    this.tagOpened = false;
  }

  public void closeOpenTag () throws IOException
  {
    if ( tagOpened ) {
      out.write( ">" );
      tagOpened = false;
    }
  }

  public void startTag ( String tagName ) throws IOException
  {
    closeOpenTag();
    out.write( "<" + tagName );
    tagOpened = true;
  }

  public void writeParam ( String paramName, String paramValue ) throws IOException
  {
    if ( !tagOpened ) {
      throw new IOException( "cannot write param: tag not opened" );
    }
    out.write( " " + paramName + "=\"" + paramValue + "\"" );
  }

  public void endTag ( String tagName ) throws IOException
  {
    if ( tagOpened ) {
      out.write( "/>" );
      tagOpened = false;
    } else {
      out.write( "</" + tagName + ">" );
    }
  }

  public void writeString ( String data ) throws IOException
  {
    closeOpenTag();
    if ( data != null ) out.write( data );
  }

  public void startTagWithParam ( String tagName, String paramName, String paramValue ) throws IOException
  {
    startTag( tagName );
    writeParam( paramName, paramValue );
  }

  public void writeEmptyTag ( String tagName ) throws IOException
  {
    startTag( tagName );
    endTag( tagName );
  }

  public void writeTextContentTag ( String tagName, String content ) throws IOException
  {
    startTag( tagName );
    writeString( content );
    endTag( tagName );
  }

}
