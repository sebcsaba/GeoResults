package scs.georesults.logic.beans.addons;

import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import scs.javax.collections.List;
import scs.javax.web.DynamicForm;
import scs.javax.web.RequestBeanBase;
import scs.javax.web.request.InvalidRequestFieldException;

/**
 * <p>A karaktertábla kezelését segítő osztály.</p>
 */
public class CharmapBean extends RequestBeanBase
{

  /**
   * Az aktuálisan kiválaszott karakterlap azonosítója, vagyis első elemének Unicode kódja.
   */
  private long page;

  /**
   * A Unicode által definiált karakterlapok (charts) listája,
   * {@link CharRange} típusú objektumokat tartalmaz.
   */
  private static List charMaps = new List( 120 );

  static
  {
    charMaps.add( new CharRange( "Basic Latin", 0x0020, 0x007f ) );
    charMaps.add( new CharRange( "Latin-1 Supplement", 0x0080, 0x00ff ) );
    charMaps.add( new CharRange( "Latin Extended-A", 0x0100, 0x017f ) );
    charMaps.add( new CharRange( "Latin Extended-B", 0x0180, 0x024f ) );
    charMaps.add( new CharRange( "IPA Extensions", 0x0250, 0x02af ) );
    charMaps.add( new CharRange( "Spacing Modifier Letters", 0x02b0, 0x02ff ) );
    charMaps.add( new CharRange( "Combining Diacritical Marks", 0x0300, 0x036f ) );
    charMaps.add( new CharRange( "Greek and Coptic", 0x0370, 0x03ff ) );
    charMaps.add( new CharRange( "Cyrillic", 0x0400, 0x04ff ) );
    charMaps.add( new CharRange( "Cyrillic Supplementary", 0x0500, 0x052f ) );
    charMaps.add( new CharRange( "Armenian", 0x0530, 0x058f ) );
    charMaps.add( new CharRange( "Hebrew", 0x0590, 0x05ff ) );
    charMaps.add( new CharRange( "Arabic", 0x0600, 0x06ff ) );
    charMaps.add( new CharRange( "Syriac", 0x0700, 0x074f ) );
    charMaps.add( new CharRange( "Thaana", 0x0780, 0x07bf ) );
    charMaps.add( new CharRange( "Devanagari", 0x0900, 0x097f ) );
    charMaps.add( new CharRange( "Bengali", 0x0980, 0x09ff ) );
    charMaps.add( new CharRange( "Gurmukhi", 0x0a00, 0x0a7f ) );
    charMaps.add( new CharRange( "Gujarati", 0x0a80, 0x0aff ) );
    charMaps.add( new CharRange( "Oriya", 0x0b00, 0x0b7f ) );
    charMaps.add( new CharRange( "Tamil", 0x0b80, 0x0bff ) );
    charMaps.add( new CharRange( "Telugu", 0x0c00, 0x0c7f ) );
    charMaps.add( new CharRange( "Kannada", 0x0c80, 0x0cff ) );
    charMaps.add( new CharRange( "Malayalam", 0x0d00, 0x0d7f ) );
    charMaps.add( new CharRange( "Sinhala", 0x0d80, 0x0dff ) );
    charMaps.add( new CharRange( "Thai", 0x0e00, 0x0e7f ) );
    charMaps.add( new CharRange( "Lao", 0x0e80, 0x0eff ) );
    charMaps.add( new CharRange( "Tibetan", 0x0f00, 0x0fff ) );
    charMaps.add( new CharRange( "Myanmar", 0x1000, 0x109f ) );
    charMaps.add( new CharRange( "Georgian", 0x10a0, 0x10ff ) );
    charMaps.add( new CharRange( "Hangul Jamo", 0x1100, 0x11ff ) );
    charMaps.add( new CharRange( "Ethiopic", 0x1200, 0x137f ) );
    charMaps.add( new CharRange( "Cherokee", 0x13a0, 0x13ff ) );
    charMaps.add( new CharRange( "Unified Canadian Aboriginal Syllabics", 0x1400, 0x167f ) );
    charMaps.add( new CharRange( "Ogham", 0x1680, 0x169f ) );
    charMaps.add( new CharRange( "Runic", 0x16a0, 0x16ff ) );
    charMaps.add( new CharRange( "Tagalog", 0x1700, 0x171f ) );
    charMaps.add( new CharRange( "Hanunoo", 0x1720, 0x173f ) );
    charMaps.add( new CharRange( "Buhid", 0x1740, 0x175f ) );
    charMaps.add( new CharRange( "Tagbanwa", 0x1760, 0x177f ) );
    charMaps.add( new CharRange( "Khmer", 0x1780, 0x17ff ) );
    charMaps.add( new CharRange( "Mongolian", 0x1800, 0x18af ) );
    charMaps.add( new CharRange( "Limbu", 0x1900, 0x194f ) );
    charMaps.add( new CharRange( "Tai Le", 0x1950, 0x197f ) );
    charMaps.add( new CharRange( "Khmer Symbols", 0x19e0, 0x19ff ) );
    charMaps.add( new CharRange( "Phonetic Extensions", 0x1d00, 0x1d7f ) );
    charMaps.add( new CharRange( "Latin Extended Additional", 0x1e00, 0x1eff ) );
    charMaps.add( new CharRange( "Greek Extended", 0x1f00, 0x1fff ) );
    charMaps.add( new CharRange( "General Punctuation", 0x2000, 0x206f ) );
    charMaps.add( new CharRange( "Superscripts and Subscripts", 0x2070, 0x209f ) );
    charMaps.add( new CharRange( "Currency Symbols", 0x20a0, 0x20cf ) );
    charMaps.add( new CharRange( "Combining Diacritical Marks for Symbols", 0x20d0, 0x20ff ) );
    charMaps.add( new CharRange( "Letterlike Symbols", 0x2100, 0x214f ) );
    charMaps.add( new CharRange( "Number Forms", 0x2150, 0x218f ) );
    charMaps.add( new CharRange( "Arrows", 0x2190, 0x21ff ) );
    charMaps.add( new CharRange( "Mathematical Operators", 0x2200, 0x22ff ) );
    charMaps.add( new CharRange( "Miscellaneous Technical", 0x2300, 0x23ff ) );
    charMaps.add( new CharRange( "Control Pictures", 0x2400, 0x243f ) );
    charMaps.add( new CharRange( "Optical Character Recognition", 0x2440, 0x245f ) );
    charMaps.add( new CharRange( "Enclosed Alphanumerics", 0x2460, 0x24ff ) );
    charMaps.add( new CharRange( "Box Drawing", 0x2500, 0x257f ) );
    charMaps.add( new CharRange( "Block Elements", 0x2580, 0x259f ) );
    charMaps.add( new CharRange( "Geometric Shapes", 0x25a0, 0x25ff ) );
    charMaps.add( new CharRange( "Miscellaneous Symbols", 0x2600, 0x26ff ) );
    charMaps.add( new CharRange( "Dingbats", 0x2700, 0x27bf ) );
    charMaps.add( new CharRange( "Miscellaneous Mathematical Symbols-A", 0x27c0, 0x27ef ) );
    charMaps.add( new CharRange( "Supplemental Arrows-A", 0x27f0, 0x27ff ) );
    charMaps.add( new CharRange( "Braille Patterns", 0x2800, 0x28ff ) );
    charMaps.add( new CharRange( "Supplemental Arrows-B", 0x2900, 0x297f ) );
    charMaps.add( new CharRange( "Miscellaneous Mathematical Symbols-B", 0x2980, 0x29ff ) );
    charMaps.add( new CharRange( "Supplemental Mathematical Operators", 0x2a00, 0x2aff ) );
    charMaps.add( new CharRange( "Miscellaneous Symbols and Arrows", 0x2b00, 0x2bff ) );
    charMaps.add( new CharRange( "CJK Radicals Supplement", 0x2e80, 0x2eff ) );
    charMaps.add( new CharRange( "Kangxi Radicals", 0x2f00, 0x2fdf ) );
    charMaps.add( new CharRange( "Ideographic Description Characters", 0x2ff0, 0x2fff ) );
    charMaps.add( new CharRange( "CJK Symbols and Punctuation", 0x3000, 0x303f ) );
    charMaps.add( new CharRange( "Hiragana", 0x3040, 0x309f ) );
    charMaps.add( new CharRange( "Katakana", 0x30a0, 0x30ff ) );
    charMaps.add( new CharRange( "Bopomofo", 0x3100, 0x312f ) );
    charMaps.add( new CharRange( "Hangul Compatibility Jamo", 0x3130, 0x318f ) );
    charMaps.add( new CharRange( "Kanbun", 0x3190, 0x319f ) );
    charMaps.add( new CharRange( "Bopomofo Extended", 0x31a0, 0x31bf ) );
    charMaps.add( new CharRange( "Katakana Phonetic Extensions", 0x31f0, 0x31ff ) );
    charMaps.add( new CharRange( "Enclosed CJK Letters and Months", 0x3200, 0x32ff ) );
    charMaps.add( new CharRange( "CJK Compatibility", 0x3300, 0x33ff ) );
    charMaps.add( new CharRange( "CJK Unified Ideographs Extension A", 0x3400, 0x4dbf ) );
    charMaps.add( new CharRange( "Yijing Hexagram Symbols", 0x4dc0, 0x4dff ) );
    charMaps.add( new CharRange( "CJK Unified Ideographs", 0x4e00, 0x9f0f ) );
    charMaps.add( new CharRange( "Yi Syllables", 0xa000, 0xa48f ) );
    charMaps.add( new CharRange( "Yi Radicals", 0xa490, 0xa4cf ) );
    charMaps.add( new CharRange( "Hangul Syllables", 0xac00, 0xd7af ) );
    //charMaps.add(new CharRange( "High Surrogate Area", 0xd800, 0xdbff) );
    //charMaps.add(new CharRange( "Low Surrogate Area", 0xdc00, 0xdfff) );
    //charMaps.add(new CharRange( "Private Use Area", 0xe000, 0xf8ff) );
    charMaps.add( new CharRange( "CJK Compatibility Ideographs", 0xf900, 0xfaff ) );
    charMaps.add( new CharRange( "Alphabetic Presentation Forms", 0xfb00, 0xfb4f ) );
    charMaps.add( new CharRange( "Arabic Presentation Forms-A", 0xfb50, 0xfdff ) );
    charMaps.add( new CharRange( "Variation Selectors", 0xfe00, 0xfe0f ) );
    charMaps.add( new CharRange( "Combining Half Marks", 0xfe20, 0xfe2f ) );
    charMaps.add( new CharRange( "CJK Compatibility Forms", 0xfe30, 0xfe4f ) );
    charMaps.add( new CharRange( "Small Form Variants", 0xfe50, 0xfe6f ) );
    charMaps.add( new CharRange( "Arabic Presentation Forms-B", 0xfe70, 0xfeff ) );
    charMaps.add( new CharRange( "Halfwidth and Fullwidth Forms", 0xff00, 0xffef ) );
    charMaps.add( new CharRange( "Specials", 0xfff0, 0xffff ) );
    /*
        charMaps.add( new CharRange( "Linear B Syllabary", 0x10000, 0x1007f ) );
        charMaps.add( new CharRange( "Linear B Ideograms", 0x10080, 0x100ff ) );
        charMaps.add( new CharRange( "Aegean Numbers", 0x10100, 0x1013f ) );
        charMaps.add( new CharRange( "Old Italic", 0x10300, 0x1032f ) );
        charMaps.add( new CharRange( "Gothic", 0x10330, 0x1034f ) );
        charMaps.add( new CharRange( "Ugaritic", 0x10380, 0x1039f ) );
        charMaps.add( new CharRange( "Deseret", 0x10400, 0x1044f ) );
        charMaps.add( new CharRange( "Shavian", 0x10450, 0x1047f ) );
        charMaps.add( new CharRange( "Osmanya", 0x10480, 0x104af ) );
        charMaps.add( new CharRange( "Cypriot Syllabary", 0x10800, 0x1083f ) );
        charMaps.add( new CharRange( "Byzantine Musical Symbols", 0x1d000, 0x1d0ff ) );
        charMaps.add( new CharRange( "Musical Symbols", 0x1d100, 0x1d1ff ) );
        charMaps.add( new CharRange( "Tai Xuan Jing Symbols", 0x1d300, 0x1d35f ) );
        charMaps.add( new CharRange( "Mathematical Alphanumeric Symbols", 0x1d400, 0x1d7ff ) );
        charMaps.add( new CharRange( "CJK Unified Ideographs Extension B", 0x20000, 0x2a6df ) );
     charMaps.add( new CharRange( "CJK Compatibility Ideographs Supplement", 0x2f800, 0x2fa1f ) );
        charMaps.add( new CharRange( "Tags", 0xe0000, 0xe007f ) );
        charMaps.add( new CharRange( "Variation Selectors Supplement", 0xe0100, 0xe01ef ) );
        //charMaps.add(new CharRange( "Supplementary Private Use Area-A", 0xf0000, 0xfffff) );
        //charMaps.add(new CharRange( "Supplementary Private Use Area-B", 0x100000, 0x10ffff) );
     */
  }

  /**
   * Az osztály egy új példányát hozza létre.
   */
  public CharmapBean ()
  {
    this.page = ( ( CharRange ) charMaps.get( 0 ) ).getFirst();
  }

  /**
   * Az aktuális kontextus alapján beállítja a kiválasztott karakterlapot.
   */
  public void init () throws InvalidRequestFieldException
  {
    DynamicForm form = new DynamicForm( ( HttpServletRequest ) pageContext.getRequest() );
    if ( form.has( "page" ) ) page = form.getLong( "page" );
  }

  /**
   * A karakterlapok listáját adja vissza
   */
  public List getCharMaps ()
  {
    return charMaps;
  }

  /**
   * Az aktuális karakterlap azonosítóját adja vissza
   */
  public long getPage ()
  {
    return page;
  }

  /**
   * Az aktuális karakterlapot adja vissza
   */
  public CharRange getRange ()
  {
    int index = Collections.binarySearch( charMaps, new CharRange( null, page, 0 ) );
    return ( CharRange ) charMaps.get( index );
  }

  /**
   * <p>A karakterlapok osztálya.</p>
   */
  public static class CharRange implements Comparable
  {

    /**
     * A karakterlap neve
     */
    private String name;

    /**
     * A karakterlap első elemének Unicode kódja. Egyben a karakterlap azonosítójaként is használjuk.
     */
    private long first;

    /**
     * A karakterlap utolsó elemének Unicode kódja.
     */
    private long last;

    /**
     * Létrehoz egy új karakterlap-objektumot a megadott paraméterek alapján.
     */
    public CharRange ( String name, long first, long last )
    {
      this.name = name;
      this.first = first;
      this.last = last;
    }

    public void setName ( String name )
    {
      this.name = name;
    }

    public String getName ()
    {
      return name;
    }

    public long getLast ()
    {
      return last;
    }

    public long getFirst ()
    {
      return first;
    }

    /**
     * A Comparable osztályban meghatározott szabályok szerint összehasonlítja a paraméterben kapott karakterlap-objektumot önmagával.
     */
    public int compareTo ( Object o )
    {
      CharRange cr = ( CharRange ) o;
      return ( int ) ( this.first - cr.first );
    }

  }

}
