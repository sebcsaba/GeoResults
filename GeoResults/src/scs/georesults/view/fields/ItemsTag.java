package scs.georesults.view.fields;

import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;

public class ItemsTag extends TagBase
{

  private Collection items;

  private String valuePropertyName;

  private String labelPropertyName;

  public int doEndTag () throws JspException
  {
    try {
      if ( items != null ) printItems();
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  private void printItems () throws DIIException, JspException
  {
    DynamicTag dt = new DynamicTag( pageContext, new TagBase() );
    for ( Iterator it = items.iterator(); it.hasNext(); ) {
      Object item = it.next();
      String value = PropertyUtils.getProperty( item, valuePropertyName ).toString();
      String label = PropertyUtils.getProperty( item, labelPropertyName ).toString();
      ItemTag itemTag = new ItemTag( value, label );
      itemTag.init( ( TagBase ) getParent() );
      dt.addChild( itemTag );
    }
    dt.eval();
  }

  public void setItems ( Collection items )
  {
    this.items = items;
  }

  public void setValuePropertyName ( String valuePropertyName )
  {
    this.valuePropertyName = valuePropertyName;
  }

  public void setLabelPropertyName ( String labelPropertyName )
  {
    this.labelPropertyName = labelPropertyName;
  }

}
