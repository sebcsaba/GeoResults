package scs.georesults.logic.beans.importing;

import scs.georesults.om.verseny.VersenyImpl;

public class ImportBean
{

  private int stepsDone;

  private int stepsAll;


  private String lang;

  private VersenyImpl currentVerseny;


  private ImportSource sourceSubbean;

  private ImportCategorySubbean categorySubbean;

  private boolean recognise;

  private Recogniser recogniser;


  public ImportBean ( String lang, VersenyImpl currentVerseny )
  {
    this.stepsDone = 1;
    this.stepsAll = 10;
    this.lang = lang;
    this.currentVerseny = currentVerseny;
    this.sourceSubbean = null;
    this.categorySubbean = null;
    this.recognise = false;
    this.recogniser = null;
  }

  public void setStepsAll ( int stepsAll )
  {
    this.stepsAll = stepsAll;
  }

  public void setCategorySubbean ( ImportCategorySubbean categorySubbean )
  {
    this.categorySubbean = categorySubbean;
    this.stepsAll = this.stepsDone + 1 + categorySubbean.getMaximumBranchSteps();
  }

  public void setRecognise ( boolean recognise )
  {
    this.recognise = recognise;
  }

  public void setRecogniser ( Recogniser recogniser )
  {
    this.recogniser = recogniser;
  }

  public void setSourceSubbean ( ImportSource sourceSubbean )
  {
    this.sourceSubbean = sourceSubbean;
  }

  public int getStepsAll ()
  {
    return stepsAll;
  }

  public ImportCategorySubbean getCategorySubbean ()
  {
    return categorySubbean;
  }

  public boolean isRecognise ()
  {
    return recognise;
  }

  public String getLang ()
  {
    return lang;
  }

  public VersenyImpl getCurrentVerseny ()
  {
    return currentVerseny;
  }

  public Recogniser getRecogniser ()
  {
    return recogniser;
  }

  public int getStepsDone ()
  {
    return stepsDone;
  }

  public ImportSource getSourceSubbean ()
  {
    return sourceSubbean;
  }

  public void incStepsDone ()
  {
    ++stepsDone;
  }

  public int getStepsPercent ()
  {
    return 100 * stepsDone / stepsAll;
  }

}
