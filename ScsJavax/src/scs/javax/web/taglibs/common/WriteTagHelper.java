package scs.javax.web.taglibs.common;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import scs.javax.dii.ClassUtils;
import scs.javax.dii.DIIException;
import scs.javax.utils.ServicesHandler;

public class WriteTagHelper
{

  public static final String KEY_BUNDLE_RESOLVER = "scs/javax/web/WriteTagHelper/bundleRersolver";

  public static final String KEY_LABEL_RESOLVER = "scs/javax/web/WriteTagHelper/labelRersolver";

  private static WriteTagHelper instance = null;

  private TextResolver bundleResolver;

  private TextResolver labelResolver;

  private WriteTagHelper ( PageContext pageContext ) throws DIIException
  {
    ServicesHandler services = ServicesHandler.getCurrentInstance( pageContext );
    bundleResolver = ( TextResolver ) ClassUtils.newInstance( services.getServiceClass( KEY_BUNDLE_RESOLVER ) );
    labelResolver = ( TextResolver ) ClassUtils.newInstance( services.getServiceClass( KEY_LABEL_RESOLVER ) );
  }

  public TextResolver getLabelResolver ()
  {
    return labelResolver;
  }

  public TextResolver getBundleResolver ()
  {
    return bundleResolver;
  }

  public static WriteTagHelper getCurrentInstance ( PageContext pageContext )
  {
    try {
      if ( instance == null ) {
        instance = new WriteTagHelper( pageContext );
      }
      return instance;
    }
    catch ( Exception ex ) {
      throw new Error( ex );
    }
  }

  public static String getTitleOrLabelOrCaption ( PageContext pageContext, String key, String label, String caption ) throws JspException
  {
    if ( key != null ) {
      return getCurrentInstance( pageContext ).getBundleResolver().resolve( pageContext, key );
    } else if ( label != null ) {
      return getCurrentInstance( pageContext ).getLabelResolver().resolve( pageContext, label );
    } else return caption;
  }

}
