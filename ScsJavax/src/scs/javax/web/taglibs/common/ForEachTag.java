package scs.javax.web.taglibs.common;

import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import scs.javax.web.taglibs.TagBase;

public class ForEachTag extends TagBase
{

  private Collection items;

  private int limit;

  private String var;

  private String indexVar;

  private LimitedIterator it;

  public int doStartTag () throws JspException
  {
    try {
      it = new LimitedIterator( items, limit );
      if ( startNextIteration() ) {
        return EVAL_BODY_INCLUDE;
      } else {
        return SKIP_BODY;
      }
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public int doAfterBody () throws JspException
  {
    try {
      if ( startNextIteration() ) {
        return EVAL_BODY_AGAIN;
      } else {
        return EVAL_PAGE;
      }
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public int doEndTag () throws JspException
  {
    try {
      if ( var != null ) pageContext.removeAttribute( var, PageContext.REQUEST_SCOPE );
      if ( indexVar != null ) pageContext.removeAttribute( indexVar, PageContext.REQUEST_SCOPE );
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  private boolean startNextIteration ()
  {
    if ( it.hasNext() ) {
      Object value = it.next();
      if ( var != null ) pageContext.setAttribute( var, value, PageContext.REQUEST_SCOPE );
      if ( indexVar != null ) pageContext.setAttribute( indexVar, new Integer( it.getIndex() ), PageContext.REQUEST_SCOPE );
      return true;
    } else return false;
  }

  public void setIndexVar ( String indexVar )
  {
    this.indexVar = indexVar;
  }

  public void setVar ( String var )
  {
    this.var = var;
  }

  public void setLimit ( int limit )
  {
    this.limit = limit;
  }

  public void setItems ( Collection items )
  {
    this.items = items;
  }

  private static class LimitedIterator implements Iterator
  {

    private int index;

    private int limit;

    private Iterator it;

    public LimitedIterator ( Collection items, int limit )
    {
      if ( items != null )this.it = items.iterator();
      this.limit = limit;
      this.index = -1;
    }

    public void remove ()
    {
      throw new UnsupportedOperationException( "remove" );
    }

    public boolean hasNext ()
    {
      if ( limit == 0 ) {
        if ( it != null ) {
          return it.hasNext();
        } else {
          return false;
        }
      } else {
        if ( index < limit - 1 ) {
          if ( it != null ) {
            return it.hasNext();
          } else return true;
        } else return false;
      }
    }

    public Object next ()
    {
      ++index;
      if ( it != null && it.hasNext() ) {
        return it.next();
      }
      return null;
    }

    public int getIndex ()
    {
      return index;
    }

  }

}
