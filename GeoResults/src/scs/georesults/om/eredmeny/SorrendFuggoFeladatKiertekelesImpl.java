package scs.georesults.om.eredmeny;

public class SorrendFuggoFeladatKiertekelesImpl extends SorrendFuggoFeladatKiertekeles
{

  public SorrendFuggoFeladatKiertekelesImpl ()
  {
    super();
  }

  public SorrendFuggoFeladatKiertekelesImpl ( long eid, int rajtszam, long sfftid, int posindex )
  {
    super( eid, rajtszam, sfftid, posindex );
  }

  public SorrendFuggoFeladatKiertekelesImpl ( long eid, int rajtszam, long sfftid, int posindex, String menetlevelFelirat, String etalonFelirat, int tipus )
  {
    super( eid, rajtszam, sfftid, posindex, menetlevelFelirat, etalonFelirat, tipus );
  }

}
