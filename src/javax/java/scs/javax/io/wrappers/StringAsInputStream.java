package scs.javax.io.wrappers;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import scs.javax.io.RandomAccessArray;
import scs.javax.utils.StringUtils;

public class StringAsInputStream extends RandomAccessArray
{

  public StringAsInputStream ( String base )
  {
    super( allocateBuffer( base ) );
  }

  private static byte[] allocateBuffer ( String base )
  {
    ByteBuffer byteBuf = Charset.forName( StringUtils.DEFAULT_ENCODING ).encode( base );
    if ( byteBuf.hasArray() ) {
      return byteBuf.array();
    } else {
      byte[] result = new byte[byteBuf.limit()];
      byteBuf.get( result );
      return result;
    }
  }

}
