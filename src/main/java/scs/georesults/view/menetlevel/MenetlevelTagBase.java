package scs.georesults.view.menetlevel;

import javax.servlet.jsp.JspException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.om.etap.DarabFuggoEtapFeladatImpl;
import scs.georesults.om.etap.SorrendFuggoEtapFeladatImpl;
import scs.georesults.om.menetlevel.Menetlevel;
import scs.georesults.view.geo.InitSetFocusTag;

public abstract class MenetlevelTagBase extends TagBase
{

  protected Menetlevel value;

  protected StorableEntityBase ef;

  protected int count;

  public int doEndTag () throws JspException
  {
    try {
      DynamicTag dt = createFormTag( ( TagBase ) getParent() );
      dt.eval();

      if ( ! ( parent instanceof MenetlevelTag ) ) {
        InitSetFocusTag isf = new InitSetFocusTag();
        isf.init( ( TagBase ) getParent() );
        isf.setField( getFirstInputId() );
        isf.eval();
      }

      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  protected abstract DynamicTag createFormTag ( TagBase parent ) throws Exception;

  public abstract String getFirstInputId () throws Exception;

  public void setCount ( int count )
  {
    this.count = count;
  }

  public void setValue ( Menetlevel value )
  {
    this.value = value;
  }

  public void setEf ( StorableEntityBase ef )
  {
    this.ef = ef;
  }

  protected DarabFuggoEtapFeladatImpl getDarabFuggoEtapFeladat ()
  {
    return ( DarabFuggoEtapFeladatImpl ) ef;
  }

  protected SorrendFuggoEtapFeladatImpl getSorrendFuggoEtapFeladat ()
  {
    return ( SorrendFuggoEtapFeladatImpl ) ef;
  }

}
