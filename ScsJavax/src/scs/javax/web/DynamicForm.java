package scs.javax.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import scs.javax.collections.List;
import scs.javax.io.IOException;
import scs.javax.io.InputStream;
import scs.javax.lang.Date;
import scs.javax.lang.Time;
import scs.javax.utils.StringUtils;
import scs.javax.web.request.*;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

public class DynamicForm
{

  private RequestAccessor ra;

  public DynamicForm ( HttpServletRequest request ) throws InvalidRequestFieldException
  {
    try {
      request.setCharacterEncoding( StringUtils.DEFAULT_ENCODING );
      if ( FileUpload.isMultipartContent( /*new ServletRequestContext(*/ request /*)*/ ) ) {
        this.ra = new MultipartFormDataRequestAccessor( request );
      } else {
        this.ra = new ApplicationFormUrlEncodedRequestAccessor( request );
      }
    }
    catch ( FileUploadException ex ) {
      throw new InvalidRequestFieldException( ex );
    }
    catch ( UnsupportedEncodingException ex ) {
      throw new Error( ex );
    }
  }

  public boolean has ( String name )
  {
    return ra.hasField( name );
  }

  // ha nincs megadva: kivétel, különben maga a string.
  public String getString ( String name ) throws InvalidRequestFieldException
  {
    return ra.getFieldUtfString( name );
  }

  // ha nincs megadva: kivétel, különben a többsoros string minden sora egy-egy elem.
  public List getStringList ( String name ) throws InvalidRequestFieldException
  {
    String base = StringUtils.changeLineEndsTo( getString( name ), StringUtils.LINE_SEPARATOR_LF );
    return StringUtils.tokenize( base, StringUtils.LINE_SEPARATOR_LF, false );
  }

  // ha nincs megadva: kivétel, ha üres sztring lenne akkor null.
  public String getOptionalString ( String name ) throws InvalidRequestFieldException
  {
    String result = getString( name );
    if ( result.length() == 0 ) {
      return null;
    } else {
      return result;
    }
  }

  // ha checkbox, akkor getOptionalString(), különben null.
  public String getOptionalStringWithCheckbox ( String name ) throws InvalidRequestFieldException
  {
    if ( getBoolean( name + "_exists" ) ) {
      return getOptionalString( name );
    } else {
      return null;
    }
  }

  // ha a *_dict nem üres akkor azt, különben a * mezőt adja vissza getString()-gel
  public String getDictionaryString ( String name ) throws InvalidRequestFieldException
  {
    String dictFieldName = name + "_dict";
    if ( has( dictFieldName ) ) {
      String dict = getOptionalString( dictFieldName );
      if ( dict != null )return dict;
    }
    return getString( name );
  }

  // true, ha meg van adva és "on", különben false.
  public boolean getBoolean ( String name ) throws InvalidRequestFieldException
  {
    if ( ra.hasField( name ) ) {
      String result = ra.getBasicFieldString( name );
      return result != null && ( result.equals( "on" ) || result.equals( "false" ) );
    } else return false;
  }

  public Boolean getOptionalBoolean ( String name ) throws InvalidRequestFieldException
  {
    if ( getBoolean( name + "_exists" ) ) {
      return Boolean.valueOf( getBoolean( name ) );
    } else {
      return null;
    }
  }

  // ha nincs megadva: kivétel, ha üres string akkor 0, különben parse.
  public int getInteger ( String name ) throws InvalidRequestFieldException
  {
    String result = ra.getBasicFieldString( name );
    if ( result.length() == 0 ) {
      return 0;
    } else {
      return Integer.parseInt( result );
    }
  }

  // ha checkbox, akkor getInteger(), különben null.
  public Integer getOptionalInteger ( String name ) throws InvalidRequestFieldException
  {
    if ( getBoolean( name + "_exists" ) ) {
      return new Integer( getInteger( name ) );
    } else {
      return null;
    }
  }

  // ha nincs megadva: kivétel, ha üres string: null, különben parse.
  public Integer getOptionalIntegerWithoutCheckbox ( String name ) throws InvalidRequestFieldException
  {
    String result = ra.getBasicFieldString( name );
    if ( result.length() == 0 ) {
      return null;
    } else {
      return new Integer( result );
    }
  }

  // ha nincs megadva: kivétel, ha üres string akkor 0, különben parse.
  public long getLong ( String name ) throws InvalidRequestFieldException
  {
    String result = ra.getBasicFieldString( name );
    if ( result.length() == 0 ) {
      return 0;
    } else {
      return Long.parseLong( result );
    }
  }

  // ha checkbox, akkor getLong(), különben null.
  public Long getOptionalLong ( String name ) throws InvalidRequestFieldException
  {
    if ( getBoolean( name + "_exists" ) ) {
      return new Long( getLong( name ) );
    } else {
      return null;
    }
  }

  // ha nincs megadva: kivétel, ha üres string: null, különben parse.
  public Long getOptionalLongWithoutCheckbox ( String name ) throws InvalidRequestFieldException
  {
    String result = ra.getBasicFieldString( name );
    if ( result.length() == 0 ) {
      return null;
    } else {
      return new Long( result );
    }
  }

  // ha nincs megadva: kivétel, különben parse.
  public Date getDate ( String name ) throws InvalidRequestFieldException
  {
    int year = getInteger( name + "_year" );
    int month = getInteger( name + "_month" );
    int day = getInteger( name + "_day" );
    return new Date( year, month, day );
  }

  // ha checkbox, akkor getDate(), különben null.
  public Date getOptionalDate ( String name ) throws InvalidRequestFieldException
  {
    if ( getBoolean( name + "_exists" ) ) {
      return getDate( name );
    } else {
      return null;
    }
  }

  // ha nincs megadva: kivétel, különben parse.
  public Time getTime ( String name ) throws InvalidRequestFieldException
  {
    int hour = getInteger( name + "_hour" );
    int min = getInteger( name + "_min" );
    return new Time( hour, min );
  }

  // ha checkbox, akkor getTime(), különben null.
  public Time getOptionalTime ( String name ) throws InvalidRequestFieldException
  {
    if ( getBoolean( name + "_exists" ) ) {
      return getTime( name );
    } else {
      return null;
    }
  }

  public InputStream getInputStream ( String name ) throws IOException, InvalidRequestFieldException
  {
    return ra.getFieldStream( name );
  }

  // ha nincs megadva: kivétel.
  /*public String[] getStrings ( String name ) throws InvalidRequestFieldException
     {
    String result[] = request.getParameterValues( name );
    if ( result == null )throw new InvalidRequestFieldException( name );
    return result;
     }*/

}
