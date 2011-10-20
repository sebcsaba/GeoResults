package scs.javax.xml.tags;

public interface XmlNamespace
{

  public Class forTagName ( String tagName );

  public String getRootElementName ();

  public String getPublicID ();

  public String getSystemID ();

}
