package scs.javax.web.taglibs.formelements;

public class SubmitTag extends InputTag
{

  protected void initTypeClearUnexpected ()
  {
    type = "submit";
    checked = false;
    readonly = false;
    maxlength = null;
    onselect = null;
    onchange = null;
    src = null;
    alt = null;
  }

}
