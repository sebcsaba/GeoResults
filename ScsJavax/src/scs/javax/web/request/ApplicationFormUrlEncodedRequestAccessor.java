package scs.javax.web.request;

//import java.nio.ByteBuffer;
//import java.nio.charset.Charset;
import javax.servlet.ServletRequest;
import scs.javax.io.InputStream;
import scs.javax.io.wrappers.StringAsInputStream;
//import scs.javax.utils.StringUtils;

public class ApplicationFormUrlEncodedRequestAccessor extends RequestAccessor
{

  private ServletRequest request;

  public ApplicationFormUrlEncodedRequestAccessor ( ServletRequest request )
  {
    this.request = request;
  }

  public boolean hasField ( String fieldName )
  {
    return request.getParameter( fieldName ) != null;
  }

  public String getBasicFieldString ( String fieldName ) throws InvalidRequestFieldException
  {
    String result = request.getParameter( fieldName );
    if ( result == null )throw new InvalidRequestFieldException( fieldName );
    return result;
  }

  public InputStream getFieldStream ( String fieldName ) throws InvalidRequestFieldException
  {
    return new StringAsInputStream( getFieldUtfString( fieldName ) );
  }

  public String getFieldUtfString ( String fieldName ) throws InvalidRequestFieldException
  {
    String basicString = getBasicFieldString( fieldName );
    //ByteBuffer bytebuf = Charset.forName( "ISO-8859-1" ).encode( basicString );
    //return Charset.forName( StringUtils.DEFAULT_ENCODING ).decode( bytebuf ).toString();
    return basicString;
  }

}
