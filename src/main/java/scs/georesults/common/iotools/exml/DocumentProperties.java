package scs.georesults.common.iotools.exml;

import java.text.SimpleDateFormat;
import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;
import scs.javax.lang.Date;

public class DocumentProperties implements XmlContent
{

  private String title;

  private String author;

  public DocumentProperties ()
  {}

  public DocumentProperties ( String title, String author )
  {
    this.title = title;
    this.author = author;
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "DocumentProperties" );
    out.writeParam( "xmlns", "urn:schemas-microsoft-com:office:office" );
    if ( title != null ) out.writeTextContentTag( "Title", title );
    if ( author != null ) out.writeTextContentTag( "Author", author );
    SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss'Z'" );
    out.writeTextContentTag( "Created", df.format( new Date().getOldDate() ) );
    out.endTag( "DocumentProperties" );
  }

  public void setAuthor ( String author )
  {
    this.author = author;
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

}
