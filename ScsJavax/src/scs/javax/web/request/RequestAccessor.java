package scs.javax.web.request;

import scs.javax.io.IOException;
import scs.javax.io.InputStream;

public abstract class RequestAccessor
{

  public abstract boolean hasField ( String fieldName );

  public abstract InputStream getFieldStream ( String fieldName ) throws InvalidRequestFieldException, IOException;

  public abstract String getBasicFieldString ( String fieldName ) throws InvalidRequestFieldException;

  public abstract String getFieldUtfString ( String fieldName ) throws InvalidRequestFieldException;

}
