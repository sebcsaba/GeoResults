package scs.javax.web.taglibs.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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

  private WriteTagHelper ( ServletContext servletContext ) throws DIIException
  {
    ServicesHandler services = ServicesHandler.getCurrentInstance( servletContext );
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

  public static WriteTagHelper getCurrentInstance ( ServletContext servletContext )
  {
    try {
      if ( instance == null ) {
        instance = new WriteTagHelper( servletContext );
      }
      return instance;
    }
    catch ( Exception ex ) {
      throw new Error( ex );
    }
  }

  public static String getTitleOrLabelOrCaption ( PageContext pageContext, String key, String label, String caption ) throws JspException
  {
    return getTitleOrLabelOrCaption(pageContext.getServletContext(),(HttpServletRequest)pageContext.getRequest(), key, label, caption);
  }
  
  public static String getTitleOrLabelOrCaption ( ServletContext servletContext, HttpServletRequest request, String key, String label, String caption ) throws JspException
  {
    if ( key != null ) {
      return getCurrentInstance( servletContext ).getBundleResolver().resolve( servletContext, request, key );
    } else if ( label != null ) {
      return getCurrentInstance( servletContext ).getLabelResolver().resolve( servletContext, request, label );
    } else return caption;
  }

}
