package scs.georesults.view.geo;

import scs.javax.web.taglibs.TagBase;
import javax.servlet.jsp.JspException;
import scs.javax.io.*;
import scs.javax.dii.ClassUtils;
import scs.javax.io.readers.LineReader;

public class VersionTag extends TagBase
{

  public static final String VERSION_FILE = "scs/georesults/config/version.txt";

  public int doEndTag() throws JspException
  {
    try {
      out.writeString( getVersion() );
      return super.doEndTag();
    }
    catch ( IOException ex ) {
      throw new JspException(ex);
    }
  }

  public String getVersion() throws IOException
  {
    try {
      InputStream is = ClassUtils.getResourceInputStream( getClass().getClassLoader(), VERSION_FILE );
      LineReader lines = new LineReader(new UTF8InputStreamReader(is));
      return lines.readLine();
    }
    catch (NullPointerException ex) {
      return "unknown version";
    }
  }

}
