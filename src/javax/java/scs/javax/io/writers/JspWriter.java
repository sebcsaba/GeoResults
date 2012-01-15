package scs.javax.io.writers;

import scs.javax.io.Writer;
import scs.javax.io.wrappers.OldWriterToNew;

public class JspWriter extends HtmlWriter
{

  public JspWriter ( java.io.Writer writer )
  {
    super( new OldWriterToNew( writer ) );
  }

  public JspWriter ( Writer writer )
  {
    super( writer );
  }

}
