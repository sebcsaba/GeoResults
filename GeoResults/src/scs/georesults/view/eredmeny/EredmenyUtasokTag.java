package scs.georesults.view.eredmeny;

import javax.servlet.jsp.JspException;

public class EredmenyUtasokTag extends EredmenySzemelyTagBase
{

  private int colIndex;

  protected void doPrint () throws JspException
  {
    if ( nevezes.getUtasCount() == 0 )return;
    int utasCount = nevezes.getUtasCount();
    int cols = 6 / utasCount;
    for ( int i = 1; i <= utasCount; ++i ) {
      StringBuffer borderStyle = new StringBuffer();
      if ( i == 1 ) borderStyle.append( "l" );
      if ( i == utasCount ) borderStyle.append( "r" );
      borderStyle.append( "b" );
      printCell( borderStyle.toString(), cols, 1, nevezes.getUtas( i ), new Integer( colIndex + ( i - 1 ) * cols ) );
    }
  }

  public void setColIndex ( int colIndex )
  {
    this.colIndex = colIndex;
  }

}
