package scs.georesults.logic.servlets;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import scs.javax.lang.Date;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.ServletBase;
import scs.javax.web.WebException;
import scs.georesults.BackupThread;
import scs.javax.web.taglibs.common.WriteTagHelper;
import javax.servlet.jsp.JspException;

public class BackupInfoServlet extends ServletBase
{

  public static String INFO_MODE = "info";

  public static String ACTION_MODE = "action";

  public BackupInfoServlet ()
  {}

  protected void doPostService ( DynamicForm form, HttpServletResponse response ) throws WebException, IOException, RdbException, JspException
  {
    if (INFO_MODE.equals(form.getString("mode"))) doInfo(response);
    else if (ACTION_MODE.equals(form.getString("mode"))) doAction(response);
  }

  private void doInfo( HttpServletResponse response ) throws IOException, JspException
  {
    Date date = BackupThread.getCurrentInstance().getLastSaveTime();
    boolean progress = BackupThread.getCurrentInstance().isInProgress();
    if (progress) {
      String str = WriteTagHelper.getTitleOrLabelOrCaption( getServletContext(), getRequest(), "FT_BACKUP_IN_PROGRESS", null, null );
      response.getWriter().println( str );
    } else if (date==null) {
      String str = WriteTagHelper.getTitleOrLabelOrCaption( getServletContext(), getRequest(), "FT_NO_BACKUP", null, null );
      response.getWriter().println( str );
    } else {
      String str = WriteTagHelper.getTitleOrLabelOrCaption( getServletContext(), getRequest(), "FT_BACKUP_TIME", null, null );
      response.getWriter().println(str + date.getDetailedFace() );
    }
  }

  private void doAction( HttpServletResponse response ) throws JspException, IOException
  {
    BackupThread.getCurrentInstance().userBackup();
    String str = WriteTagHelper.getTitleOrLabelOrCaption( getServletContext(), getRequest(), "FT_BACKUP_IN_PROGRESS", null, null );
    response.getWriter().println( str );
  }

}
