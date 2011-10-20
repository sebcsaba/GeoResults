package scs.georesults.common.iotools.exml;

public class PageFooter extends PageHeaderFooterBase
{

  public PageFooter ()
  {
    super();
  }

  public PageFooter ( String data )
  {
    super( data );
  }

  public PageFooter ( String data, double margin )
  {
    super( data, margin );
  }

  protected String getTagName ()
  {
    return "Footer";
  }

}
