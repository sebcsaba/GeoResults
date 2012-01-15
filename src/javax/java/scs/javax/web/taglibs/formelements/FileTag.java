package scs.javax.web.taglibs.formelements;

public class FileTag extends InputTag
{

  protected void initTypeClearUnexpected ()
  {
    type = "file";
    value = null;
    checked = false;
    readonly = false;
    maxlength = null;
    src = null;
    alt = null;
  }

}
