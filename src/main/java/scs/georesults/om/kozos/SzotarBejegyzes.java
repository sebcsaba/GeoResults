package scs.georesults.om.kozos;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class SzotarBejegyzes extends StorableEntityBase
{

  private String lang;

  private String keystr;

  private String valuestr;

  protected SzotarBejegyzes ()
  {
  }

  protected SzotarBejegyzes ( String lang, String keystr )
  {
    this.lang = lang;
    this.keystr = keystr;
  }

  protected SzotarBejegyzes ( String lang, String keystr, String valuestr )
  {
    this.lang = lang;
    this.keystr = keystr;
    this.valuestr = valuestr;
  }

  public String getLang ()
  {
    return lang;
  }

  public void setLang ( String lang )
  {
    this.lang = lang;
  }

  public String getKeystr ()
  {
    return keystr;
  }

  public void setKeystr ( String keystr )
  {
    this.keystr = keystr;
  }

  public String getValuestr ()
  {
    return valuestr;
  }

  public void setValuestr ( String valuestr )
  {
    this.valuestr = valuestr;
  }

  public static class Lista extends StorableObjectList
  {

    private SzotarBejegyzes keyEntity = new SzotarBejegyzesImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      SzotarBejegyzes entity = ( SzotarBejegyzes ) o;
      entity.setLang( getLang () );
      return super.add( entity );
    }

    public void setLang ( String lang )
    {
      keyEntity.setLang( lang );
      for ( int i = 0; i < size(); ++i ) {
        SzotarBejegyzes entity = ( SzotarBejegyzes ) get( i );
        entity.setLang( lang );
      }
    }

    public String getLang ()
    {
      return keyEntity.getLang();
    }

  }

  public static List loadAllForNyelv ( RdbSession session, Nyelv nyelv ) throws RdbException
  {
    return loadAll( session, SzotarBejegyzes.class, "lang", nyelv );
  }

  public static SzotarBejegyzes newInstance ()
  {
    return new SzotarBejegyzesImpl();
  }

  public static SzotarBejegyzes newInstance ( String lang, String keystr )
  {
    return new SzotarBejegyzesImpl( lang, keystr );
  }

  public static SzotarBejegyzes newInstance ( String lang, String keystr, String valuestr )
  {
    return new SzotarBejegyzesImpl( lang, keystr, valuestr );
  }

}
