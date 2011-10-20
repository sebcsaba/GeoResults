package scs.javax.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import scs.javax.io.*;
import scs.javax.io.wrappers.NewInputStreamToOld;
import scs.javax.io.wrappers.NewOutputStreamToOld;
import scs.javax.io.wrappers.NewWriterToOld;
import scs.javax.utils.StringUtils;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DomSerialization
{

  private static DocumentBuilder documentBuilder = null;

  private DomSerialization ()
  {}

  private static DocumentBuilder getDocumentBuilder () throws XmlDomException
  {
    if ( documentBuilder == null ) {
      try {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating( true );
        documentBuilder = dbf.newDocumentBuilder();
        documentBuilder.setEntityResolver( new DTDResolver() );
        documentBuilder.setErrorHandler( new DefaultErrorHandler() );
      }
      catch ( ParserConfigurationException ex ) {
        throw new XmlDomException( ex );
      }
    }
    return documentBuilder;
  }

  public static Document newDocument () throws XmlDomException
  {
    return getDocumentBuilder().newDocument();
  }

  public static Document read ( String filename ) throws IOException, XmlDomException
  {
    try {
      return getDocumentBuilder().parse( filename );
    }
    catch ( SAXException ex ) {
      throw new XmlDomException( filename, ex );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( filename, ex );
    }
  }

  public static Document read ( Path filename ) throws IOException, XmlDomException
  {
    try {
      return getDocumentBuilder().parse( filename.toString() );
    }
    catch ( SAXException ex ) {
      throw new XmlDomException( filename.toString(), ex );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( filename.toString(), ex );
    }
  }

  public static Document read ( InputStream stream ) throws IOException, XmlDomException
  {
    try {
      return getDocumentBuilder().parse( new NewInputStreamToOld( stream ) );
    }
    catch ( SAXException ex ) {
      throw new XmlDomException( ex );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public static void write ( String filename, Document dom ) throws IOException
  {
    OutputStream stream = new FileOutputStream( filename );
    write( stream, dom );
  }

  public static void write ( Path filename, Document dom ) throws IOException
  {
    OutputStream stream = new FileOutputStream( filename );
    write( stream, dom );
  }

  public static void write ( OutputStream stream, Document dom ) throws IOException
  {
    try {
      OutputFormat format = new OutputFormat( dom, StringUtils.DEFAULT_ENCODING, true );
      XMLSerializer xs = new XMLSerializer( new NewOutputStreamToOld( stream ), format );
      xs.serialize( dom );
      stream.close();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public static void write ( Writer writer, Document dom ) throws IOException
  {
    try {
      OutputFormat format = new OutputFormat( dom, StringUtils.DEFAULT_ENCODING, true );
      XMLSerializer xs = new XMLSerializer( new NewWriterToOld( writer ), format );
      xs.serialize( dom );
      writer.close();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

}
