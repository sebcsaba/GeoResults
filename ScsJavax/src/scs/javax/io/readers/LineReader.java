package scs.javax.io.readers;

import scs.javax.io.IOException;
import scs.javax.io.Reader;
import scs.javax.utils.StringUtils;

public class LineReader extends ExtensibleReader
{

  private char[] cbuf;

  private int cbufstart;

  private int cbufend;

  private boolean eof;

  public LineReader ( Reader in )
  {
    super( in );
    cbuf = new char[256];
    resetBuf();
    eof = false;
  }

  public String readLine () throws IOException
  {
    if ( eof ) {
      return null;
    } else {
      StringBuffer sb = new StringBuffer();
      while ( fillFromBuffer( sb ) ) fillBuffer();
      return sb.toString();
    }
  }

  private boolean fillFromBuffer ( StringBuffer sb )
  {
    if ( eof )return false;
    while ( cbufstart < cbufend ) {
      if ( StringUtils.isLineEnd( cbuf[cbufstart] ) ) {
        while ( cbufstart < cbufend && StringUtils.isLineEnd( cbuf[cbufstart] ) )++cbufstart;
        return false;
      } else {
        sb.append( cbuf[cbufstart] );
        ++cbufstart;
      }
    }
    return true;
  }

  private void resetBuf ()
  {
    cbufstart = 0;
    cbufend = 0;
  }

  private void fillBuffer () throws IOException
  {
    if ( !eof ) {
      if ( cbufstart == cbuf.length ) resetBuf();
      int count = in.read( cbuf, cbufstart, cbuf.length - cbufstart );
      if ( count == -1 ) {
        eof = true;
      } else {
        cbufend = cbufstart + count;
      }
    }
  }

}
