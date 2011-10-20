package scs.javax.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DefaultErrorHandler implements ErrorHandler
{

  public DefaultErrorHandler ()
  {}

  public void error ( SAXParseException exception ) throws SAXException
  {
    throw exception;
  }

  public void fatalError ( SAXParseException exception ) throws SAXException
  {
    throw exception;
  }

  public void warning ( SAXParseException exception ) throws SAXException
  {
    throw exception;
  }

}
