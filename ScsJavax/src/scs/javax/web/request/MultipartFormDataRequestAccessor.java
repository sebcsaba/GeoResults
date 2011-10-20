package scs.javax.web.request;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import scs.javax.io.IOException;
import scs.javax.io.InputStream;
import scs.javax.io.wrappers.OldInputStreamToNew;
import scs.javax.utils.StringUtils;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MultipartFormDataRequestAccessor extends RequestAccessor
{

  public static final int MAXIMUM_FILE_SIZE = 1024 * 1024 * 10;

  public static final int MINIMUM_FILE_SIZE = 100;

  private Map fields;

  public MultipartFormDataRequestAccessor ( HttpServletRequest request ) throws FileUploadException
  {
    FileItemFactory factory = new DiskFileItemFactory( MINIMUM_FILE_SIZE, null );
    ServletFileUpload upload = new ServletFileUpload( factory );
    upload.setSizeMax( MAXIMUM_FILE_SIZE );
    fields = new HashMap();
    for ( Iterator iter = upload.parseRequest( request ).iterator(); iter.hasNext(); ) {
      FileItem item = ( FileItem ) iter.next();
      fields.put( item.getFieldName(), item );
    }
  }

  public boolean hasField ( String fieldName )
  {
    return fields.keySet().contains( fieldName );
  }

  public String getBasicFieldString ( String fieldName ) throws InvalidRequestFieldException
  {
    FileItem item = ( FileItem ) fields.get( fieldName );
    if ( item == null )throw new InvalidRequestFieldException( fieldName );
    return item.getString();
  }

  public InputStream getFieldStream ( String fieldName ) throws InvalidRequestFieldException, IOException
  {
    try {
      FileItem item = ( FileItem ) fields.get( fieldName );
      if ( item == null )throw new InvalidRequestFieldException( fieldName );
      return new OldInputStreamToNew( item.getInputStream() );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public String getFieldUtfString ( String fieldName ) throws InvalidRequestFieldException
  {
    try {
      InputStream in = getFieldStream( fieldName );
      byte[] bytes = new byte[ ( int ) in.available()];
      for ( int b, i = 0; ( b = in.read() ) > -1; ++i ) {
        bytes[i] = ( byte ) b;
      }
      ByteBuffer bb = ByteBuffer.wrap( bytes );
      return Charset.forName( StringUtils.DEFAULT_ENCODING ).decode( bb ).toString();
    }
    catch ( IOException ex ) {
      throw new InvalidRequestFieldException( ex );
    }
  }

}
