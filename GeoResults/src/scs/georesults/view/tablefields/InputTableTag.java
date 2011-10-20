package scs.georesults.view.tablefields;

import javax.servlet.jsp.JspException;
import scs.javax.collections.List;
import scs.javax.io.IOException;
import scs.javax.web.taglibs.TagBase;

public class InputTableTag extends TagBase
{

  protected int count;

  protected List items;

  protected Object default0;

  protected boolean headerRow;

  protected Object currentEntity;

  protected int rowIndex;

  public int doStartTag () throws JspException
  {
    try {
      headerRow = true;
      printTableHeader();
      printBeginRow();
      return EVAL_BODY_INCLUDE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public int doAfterBody () throws JspException
  {
    try {
      printEndRow();
      if ( headerRow ) {
        headerRow = false;
        rowIndex = -1;
      }
      ++rowIndex;
      if ( rowIndex < count ) {
        if ( rowIndex < items.size() ) {
          currentEntity = items.get( rowIndex );
        } else {
          currentEntity = default0;
        }
        printBeginRow();
        return EVAL_BODY_AGAIN;
      } else {
        printTableFooter();
        return EVAL_PAGE;
      }
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  private void printTableHeader () throws IOException
  {
    out.startTagWithParam( "table", "class", "inputTable" );
    out.closeOpenTag();
  }

  private void printTableFooter () throws IOException
  {
    out.endTag( "table" );
  }

  private void printBeginRow () throws IOException
  {
    out.startTag( "tr" );
    out.closeOpenTag();
  }

  private void printEndRow () throws IOException
  {
    out.endTag( "tr" );
  }

  public void setItems ( List items )
  {
    this.items = items;
  }

  public void setCount ( int count )
  {
    this.count = count;
  }

  public void setDefault ( Object default0 )
  {
    this.default0 = default0;
  }

  public int getRowIndex ()
  {
    return rowIndex;
  }

  public boolean isHeaderRow ()
  {
    return headerRow;
  }

  public Object getCurrentEntity ()
  {
    return currentEntity;
  }

}
