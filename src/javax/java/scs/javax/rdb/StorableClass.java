package scs.javax.rdb;

import scs.javax.dii.DIIException;
import scs.javax.xml.tags.XmlTag;

public interface StorableClass
{

  public void create ( RdbSession session ) throws RdbException;

  public void read ( RdbSession session ) throws RdbException;

  public void update ( RdbSession session ) throws RdbException;

  public void delete ( RdbSession session ) throws RdbException;

  public XmlTag createRdbXmlEntityTag () throws DIIException, RdbException;

}
