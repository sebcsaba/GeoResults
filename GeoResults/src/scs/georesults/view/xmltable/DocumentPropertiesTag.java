package scs.georesults.view.xmltable;

import java.text.SimpleDateFormat;
import javax.servlet.jsp.JspException;
import scs.javax.io.IOException;
import scs.javax.lang.Date;
import scs.javax.web.taglibs.TagBase;

public class DocumentPropertiesTag extends TagBase
{

  private String title;

  private String author;

  public int doEndTag () throws JspException
  {
    try {
      out.startTag( "DocumentProperties" );
      out.writeParam( "xmlns", "urn:schemas-microsoft-com:office:office" );
      if ( title != null ) out.writeTextContentTag( "Title", title );
      if ( author != null ) out.writeTextContentTag( "Author", author );
      SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss'Z'" );
      out.writeTextContentTag( "Created", df.format( new Date().getOldDate() ) );
      out.endTag( "DocumentProperties" );
      return EVAL_PAGE;
    }
    catch ( IOException ex ) {
      throw new JspException( ex );
    }
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
