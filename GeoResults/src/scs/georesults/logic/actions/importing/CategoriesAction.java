package scs.georesults.logic.actions.importing;

import java.util.HashMap;
import java.util.Map;
import scs.javax.dii.ClassUtils;
import scs.javax.dii.DIIException;
import scs.javax.web.DynamicForm;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.beans.importing.*;

public class CategoriesAction extends ImportActionBase
{

  private static Map categories;

  static
  {
    categories = new HashMap();
    categories.put( "verseny", VersenySubbean.class );
    categories.put( "alapok", AlapokSubbean.class );
    categories.put( "nevezesek", NevezesekSubbean.class );
    categories.put( "etapok", EtapokSubbean.class );
    categories.put( "szlalomFutamok", SzlalomFutamokSubbean.class );
    categories.put( "menetlevelek", MenetlevelekSubbean.class );
    categories.put( "forditas", ForditasSubbean.class );
  }

  public String importService ( DynamicForm form ) throws WebException, DIIException
  {
    if ( !form.has( "category" ) )throw new GeoMessageException( "IF_NINCS_KATEGORIA_KIJELOLVE" );
    String category = form.getString( "category" );
    if ( category == null ) {
      throw new GeoMessageException( "IF_NINCS_KATEGORIA_KIJELOLVE" );
    } else if ( categories.keySet().contains( category ) ) {
      checkLezaras( category );
      Class subbeanClass = ( Class ) categories.get( category );
      ImportCategorySubbean subbean = ( ImportCategorySubbean ) ClassUtils.newInstance( subbeanClass );
      subbean.init( getBean() );
      getBean().setCategorySubbean( subbean );
      return category;
    } else throw new ImportException( "ER_IMPORTALASI_HIBA" );
  }

  private void checkLezaras ( String category ) throws SessionTimeoutException, ImportException
  {
    if ( category.equals( "verseny" ) )return;
    if ( category.equals( "forditas" ) )return;
    if ( !getVerseny().isLeVanZarva() )return;
    throw new ImportException( "IF_LEZART_VERSENYHEZ_NEM_LEHET_IMPORTALNI" );
  }

}
