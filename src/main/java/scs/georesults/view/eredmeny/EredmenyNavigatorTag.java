package scs.georesults.view.eredmeny;

import javax.servlet.jsp.JspException;

public class EredmenyNavigatorTag extends EredmenySzemelyTagBase
{

  protected void doPrint () throws JspException
  {
    int rows = ( nevezes.getUtasCount() > 0 ? 1 : 2 );
    String borderStyle = "rt" + ( rows == 2 ? "b" : "" );
    printCell( borderStyle, 3, rows, nevezes.getNavigator(), null );
  }

}
