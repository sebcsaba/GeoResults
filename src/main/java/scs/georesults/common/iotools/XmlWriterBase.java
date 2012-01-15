package scs.georesults.common.iotools;

import java.util.Iterator;
import scs.javax.io.IOException;

/**
 * <p>Olyan export-kimenetet kiíró osztályok őse, amelyek XML-t adnak eredményül</p>
 */
public abstract class XmlWriterBase extends DatabaseWriterBase
{

  protected XmlWriterBase ( Iterator dbiter ) throws IOException
  {
    super( dbiter );
  }

  protected void writeHeaderCommentLine () throws IOException
  {
  }

  public String getContentType ()
  {
    return "text/xml";
  }

  public String getExtension ()
  {
    return "xml";
  }

}
