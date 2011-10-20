package scs.javax.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.StringTokenizer;
import scs.javax.collections.List;

public class StringUtils
{

  public static final String LINE_SEPARATOR_LF = Character.toString( ( char ) 10 );

  public static final String LINE_SEPARATOR_CR = Character.toString( ( char ) 13 );

  public static final String DEFAULT_ENCODING = "UTF-8";

  private StringUtils ()
  {}

  public static boolean isLineEnd ( char c )
  {
    return ( c == 10 ) || ( c == 13 ) || ( c == 0 );
  }

  public static String changeLineEndsTo ( String base, String destLineEnd )
  {
    base = base.replaceAll( LINE_SEPARATOR_CR + LINE_SEPARATOR_LF, LINE_SEPARATOR_LF );
    base = base.replaceAll( LINE_SEPARATOR_CR, LINE_SEPARATOR_LF );
    if ( !LINE_SEPARATOR_LF.equals( destLineEnd ) ) {
      base = base.replaceAll( LINE_SEPARATOR_LF, destLineEnd );
    }
    return base;
  }

  public static String[] appendStringArrays ( String[] a, String[] b )
  {
    String[] result = new String[a.length + b.length];
    for ( int i = 0; i < a.length; ++i ) result[i] = a[i];
    for ( int i = 0; i < b.length; ++i ) result[a.length + i] = b[i];
    return result;
  }

  public static List tokenize ( String src, String delim, boolean returnDelims )
  {
    List result = new List();
    StringTokenizer stok = new StringTokenizer( src, delim, returnDelims );
    while ( stok.hasMoreElements() ) result.add( stok.nextToken() );
    return result;
  }

  public static String[] tokenizeToArray ( String src, String delim, boolean returnDelims )
  {
    List source = tokenize( src, delim, returnDelims );
    String[] result = new String[source.size()];
    return ( String[] ) source.toArray( result );
  }

  public static String joinStrings ( String[] values, String separator )
  {
    StringBuffer result = new StringBuffer();
    for ( int i = 0; i < values.length; ++i )if ( values[i] != null ) {
      if ( i > 0 ) result.append( separator );
      result.append( values[i] );
    }
    return result.toString();
  }

  public static String upcaseFirst ( String str )
  {
    return Character.toUpperCase( str.charAt( 0 ) ) + str.substring( 1 );
  }

  public static String removeQuotes ( String str )
  {
    if ( str.charAt( 0 ) == '"' && str.charAt( str.length() - 1 ) == '"' ) {
      return str.substring( 1, str.length() - 1 );
    } else return str;
  }

  public static String escapeXml ( String str )
  {
    return str.replaceAll( "&", "&amp;" ).replaceAll( "\"", "&quot;" ).replaceAll( "<", "&lt;" ).replaceAll( ">", "&gt;" );
  }

  public static String urlDecode ( String str )
  {
    try {
      return URLDecoder.decode( str, DEFAULT_ENCODING );
    }
    catch ( UnsupportedEncodingException ex ) {
      throw new Error( ex );
    }
  }

}
