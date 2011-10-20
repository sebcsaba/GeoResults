package scs.georesults;

import scs.javax.lang.Date;
import scs.georesults.common.iotools.DatabaseVersenyExportIterator;
import scs.georesults.common.iotools.DatabaseWriterBase;
import scs.georesults.common.iotools.DatabaseSqlWriter;
import scs.javax.io.writers.XmlWriter;
import scs.javax.io.OutputStreamWriter;
import scs.javax.rdb.RdbException;
import scs.javax.io.IOException;
import scs.javax.io.FileOutputStream;
import scs.javax.io.Path;
import scs.georesults.common.iotools.DatabaseGeoxmlWriter;
import java.util.Set;
import java.util.HashSet;
import scs.georesults.om.verseny.Verseny;
import java.util.Iterator;

public class BackupThread extends Thread
{

  private static BackupThread instance = null;

  public static synchronized BackupThread getCurrentInstance()
  {
    if ( instance == null ) {
      instance = new BackupThread();
      instance.start();
    }
    return instance;
  }

  private Date lastSaveTime = null;
  private boolean progress = false;
  private final Set updatedVids;

  private BackupThread ()
  {
    super("Backup");
    updatedVids = new HashSet();
  }

  public Date getLastSaveTime()
  {
    return lastSaveTime;
  }

  public boolean isInProgress() {
    return progress;
  }

  public void run()
  {
    while (true)
    {
      try {
        synchronized ( this ) {
          wait( Config.BACKUP_TIME );
        }
        lastSaveTime = new Date();
        progress = true;
        backup();
      }
      catch ( Throwable ex ) {
        ex.printStackTrace();
      }
      finally {
        progress = false;
      }
    }
  }

  public void notifyVersenyUpdated(Verseny v) {
    if ( v == null ) return;
    synchronized ( updatedVids ) {
      updatedVids.add( new Long( v.getVid() ) );
    }
  }

  public synchronized void userBackup()
  {
    notify();
  }

  private long[] getUpdatedVidsAndClear() {
    synchronized ( updatedVids ) {
      long[] result = new long[updatedVids.size()];
      int i = 0;
      for ( Iterator it = updatedVids.iterator(); it.hasNext(); ++i ) {
        result[i] = ( ( Long ) it.next() ).longValue();
      }
      updatedVids.clear();
      return result;
    }
  }

  private void backup() throws GeoException, RdbException, IOException
  {
    long[] vids = getUpdatedVidsAndClear();
    for ( int i=0; i<vids.length; ++i ) {
      long vid = vids[i];
      GeoDbSession db = GeoDbSession.getCurrentInstance();
      DatabaseVersenyExportIterator dbitSql = new DatabaseVersenyExportIterator( db, vid );
      backup( new DatabaseSqlWriter( dbitSql ), vid );
      DatabaseVersenyExportIterator dbitXml = new DatabaseVersenyExportIterator( db, vid );
      backup( new DatabaseGeoxmlWriter( dbitXml ), vid );
    }
  }

  private void backup(DatabaseWriterBase writer, long vid) throws IOException
  {
    String filename = "backup_vid" + vid + "_" + lastSaveTime.getCompactFace() + "." + writer.getExtension();
    Path dir = new Path ( Config.getBackuppath(), "vid"+vid );
    Path file = new Path( dir, filename );
    dir.mkdirs();
    OutputStreamWriter oswriter = new OutputStreamWriter( new FileOutputStream( file ) ) ;
    writer.doExport( new XmlWriter( oswriter) );
  }

}
