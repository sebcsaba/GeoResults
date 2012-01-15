package scs.georesults.om.alap;

public class SorrendFuggoFeladatTipusImpl extends SorrendFuggoFeladatTipus
{

  public SorrendFuggoFeladatTipusImpl ()
  {
    super();
  }

  public SorrendFuggoFeladatTipusImpl ( long sfftid )
  {
    super( sfftid );
  }

  public SorrendFuggoFeladatTipusImpl ( long sfftid, long vid, String nev, int hianyHibapont, int tobbletHibapont, boolean reszletesBevitel, int ellenorzesTipus )
  {
    super( sfftid, vid, nev, hianyHibapont, tobbletHibapont, reszletesBevitel, ellenorzesTipus );
  }

}
