package scs.georesults;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import scs.javax.io.*;
import scs.javax.io.readers.LineReader;

/**
 * <p>Egy olyan segédprogram osztálya, amely végignézi az aktuális könyvtár 'src' és 'georesults' alkönyvtárait '.java', '.jsp' és '.jspf' fájlokat keresve. A talált fájlokban szótári szavak
 * azonosítóit keresi. A talált azonosítókat a 'keys.txt' fájlba kilistázza.</p>
 */
public class KeyFinder
{

  /**
   * Az eddig megtalált kulcsok halmaza.
   */
  private Set keys;

  /**
   * A kulcsokra illeszkedő reguláris kifejezés.
   */
  private Pattern pattern;

  /**
   * A segédprogram inidializálása.
   */
  public KeyFinder ()
  {
    keys = new HashSet();
    pattern = Pattern.compile( ".*\"([A-Z][A-Z_0-9]+)\".*" );
  }

  /**
   * Egy fájl egy során keresi a kulcsokat.
   *
   * @param str A fájl egy sora
   */
  public void processLine ( String str )
  {
    Matcher m = pattern.matcher( str );
    while ( m.matches() ) {
      keys.add( m.group( 1 ) );
      str = str.replaceAll( m.group( 1 ), "" );
      m = pattern.matcher( str );
    }
  }

  /**
   * Egy bementei adatfolyamot olvas végig, kulcsokat keresve.
   *
   * @param in A bemeneti adatfolyam
   * @throws IOException Ha az olvasásnál hiba lép fel
   */
  public void processStream ( InputStream in ) throws IOException
  {
    LineReader lr = new LineReader( new UTF8InputStreamReader( in ) );
    String str = lr.readLine();
    int i = 0;
    while ( str != null ) {
      processLine( str );
      str = lr.readLine();
    }
  }

  /**
   * A megadott fájlt dolgozza fel, kulcsokat keresve.
   *
   * @param p A fájl eléeéri útja
   * @throws IOException Ha az olvasásnál hiba lép fel
   */
  public void processFile ( Path p ) throws IOException
  {
    FileInputStream fin = new FileInputStream( p );
    processStream( fin );
    fin.close();
  }

  /**
   * A megadott alkönyvtárat dolgozza fel, rekurzívan.
   *
   * @param dir Az alkönyvtár elérési útja
   * @throws IOException Ha az olvasásnál hiba lép fel
   */
  public void processDir ( Path dir ) throws IOException
  {
    Path[] files = dir.listFiles();
    for ( int i = 0; i < files.length; ++i ) {
      if ( files[i].isDirectory() ) {
        if ( !skipDir( files[i] ) ) processDir( files[i] );
      } else {
        if ( seeFile( files[i] ) ) processFile( files[i] );
      }
    }
  }

  /**
   * Eldönti hogy a paraméterben kapott fájlt meg kell-e vizsgálni.
   *
   * @param file A fájl elérési útja
   * @return Igaz, ha a megadott fájlt meg kell vizsgálni
   */
  private boolean seeFile ( Path file )
  {
    if ( file.getName().endsWith( ".java" ) )return true;
    if ( file.getName().endsWith( ".jsp" ) )return true;
    if ( file.getName().endsWith( ".jspf" ) )return true;
    return false;
  }

  /**
   * Eldönti, hogy a megadott könyvtárat ki kell-e hagyni a vizsgálatból.
   *
   * @param dir A könyvtár elérési útja
   * @return Igaz, ha a megadott könyvtárat ki kell hagyni a vizsgálatból.
   */
  private boolean skipDir ( Path dir )
  {
    return ( dir.getName().equals( "." ) || dir.getName().equals( ".." ) );
  }

  /**
   * Kiírja a megadott kimeneti adatfolyamra a talált kulcs-azonosítókat.
   *
   * @param out A kimeneti adatfolyam
   * @throws IOException Ha az írásnál hiba lép fel
   */
  public void printKeys ( DataWriter out ) throws IOException
  {
    for ( Iterator it = keys.iterator(); it.hasNext(); ) {
      out.println( it.next() );
    }
  }

  /**
   * A segédprogramot futtató metódus.
   *
   * @throws IOException Ha az olvasásnál vagy az írásnál hiba lép fel
   */
  public static void main ( String[] args ) throws IOException
  {
    KeyFinder kf = new KeyFinder();
    for(int i=0; i<args.length; ++i) {
      kf.processDir( new Path( args[i] ) );
    }
    OutputStream outStream = new FileOutputStream( "keys.txt" );
    DataWriter outWriter = new OutputStreamWriter( outStream );
    kf.printKeys( outWriter );
    outWriter.close();
  }

}
