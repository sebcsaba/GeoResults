package scs.georesults.common.iotools.exml;

public class PageHeader extends PageHeaderFooterBase
{

  public PageHeader ()
  {
    super();
  }

  public PageHeader ( String data )
  {
    super( data );
  }

  public PageHeader ( String data, double margin )
  {
    super( data, margin );
  }

  protected String getTagName ()
  {
    return "Header";
  }

}
