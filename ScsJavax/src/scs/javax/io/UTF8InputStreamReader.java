package scs.javax.io;

import java.nio.charset.Charset;

import scs.javax.io.InputStreamReader;
import scs.javax.io.IOException;
import scs.javax.io.InputStream;

public class UTF8InputStreamReader extends InputStreamReader
{

	  private Integer first;

	  public UTF8InputStreamReader(InputStream in) throws IOException {
	      super(in, Charset.forName("UTF-8"));
	      first = new Integer(super.read());
	      if (first.intValue() == 0xFEFF) first = null;
	  }

	  public int read() throws IOException {
	      if (first != null) {
	          int result = first.intValue();
	          first = null;
	          return result;
	      }
	      return super.read();
	  }

	  public int read(char cbuf[], int offset, int length) throws IOException {
	      if (first != null) {
	          cbuf[offset] = (char) first.intValue();
	          first = null;
	          return 1;
	      }
	      return super.read(cbuf, offset, length);
	  }

}
