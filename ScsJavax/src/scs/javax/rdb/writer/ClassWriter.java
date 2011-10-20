package scs.javax.rdb.writer;

import java.util.*;
import scs.javax.collections.ArrayUtils;
import scs.javax.dii.BeanProperty;
import scs.javax.dii.ClassUtils;
import scs.javax.dii.DIIException;
import scs.javax.io.*;
import scs.javax.io.writers.JavaWriter;
import scs.javax.rdb.mapping.DeclarativeClassMapping;
import scs.javax.rdb.mapping.fields.RdbField;

public class ClassWriter
{

  private static Map mapPool = new HashMap(); // Map ( className:String -> DeclarativeClassMapping )

  private DeclarativeClassMapping cm;

  private JavaWriter writer;

  private String packageName;

  private String classShortName;

  private String[][] allFields;

  private String[][] keyFields;

  private Map referencedClassNames;

  public static void registerClassMapping ( DeclarativeClassMapping cm )
  {
    mapPool.put( cm.getBaseClassName(), cm );
  }

  public ClassWriter ( DeclarativeClassMapping cm ) throws DIIException
  {
    this.cm = cm;
    packageName = ClassUtils.getPackageName( cm.getBaseClassName() );
    registerClassMapping( cm );
  }

  private DeclarativeClassMapping getFromPool ( String baseClassName )
  {
    DeclarativeClassMapping result = ( DeclarativeClassMapping ) mapPool.get( baseClassName );
    if ( result == null ) {
      throw new IllegalArgumentException( "mapping for class '" + baseClassName + "' not defined previously, " + "required by class '" + cm.getBaseClassName() + "'" );
    }
    return result;
  }

  public void write ( Path srcDir, boolean forceImplFiles ) throws IOException
  {
    String packagePathName = packageName.replaceAll( "\\.", "/" );
    classShortName = ClassUtils.getSimpleClassName( cm.getBaseClassName() );
    Path outFile = new Path( srcDir, packagePathName + "/" + classShortName + ".java" );
    outFile.getParent().mkdirs();
    writer = new JavaWriter( new OutputStreamWriter( new FileOutputStream( outFile ) ) );
    writeMainClass();
    writer.close();
    outFile = new Path( srcDir, packagePathName + "/" + classShortName + "Impl.java" );
    if ( forceImplFiles || !outFile.exists() ) {
      writer = new JavaWriter( new OutputStreamWriter( new FileOutputStream( outFile ) ) );
      writeImplClass();
      writer.close();
    }
  }

  private void writeMainClass () throws IOException
  {
    calculateRefenrencedClasses();
    writeHeader();
    writer.startMainClass( true, classShortName, "StorableEntityBase", null );
    allFields = writeFields();
    keyFields = new String[cm.getPrimaryKey().getFields().length][];
    ArrayUtils.arraycopy( allFields, 0, keyFields, 0, keyFields.length );
    writeConstructor( classShortName, null );
    writeConstructor( classShortName, keyFields );
    if ( allFields.length > keyFields.length ) writeConstructor( classShortName, allFields );
    writeCompositionGetters();
    writeSimpleGettersSetters();
    if ( cm.isListable() ) writeListClass();
    writeLoaders();
    writeNewInstance( classShortName, null );
    writeNewInstance( classShortName, keyFields );
    if ( allFields.length > keyFields.length ) writeNewInstance( classShortName, allFields );
    writer.endClass();
  }

  private void calculateRefenrencedClasses ()
  {
    referencedClassNames = new HashMap();
    for ( Iterator it = cm.getReferencedClassAttributeNames().iterator(); it.hasNext(); ) {
      String fieldName = ( String ) it.next();
      String referencedClassName = cm.getReferencedClassName( fieldName );
      boolean needLoader = !referencedClassNames.containsKey( referencedClassName );
      DeclarativeClassMapping refcm = ( DeclarativeClassMapping ) mapPool.get( referencedClassName );
      RdbField[] refKeys = refcm.getPrimaryKey().getFields();
      needLoader &= refKeys.length == 1 && refKeys[0].getPropertyName().equals( fieldName );
      referencedClassNames.put( referencedClassName, Boolean.valueOf( needLoader ) );
    }
  }

  private void writeImplClass () throws IOException
  {
    String implClassName = classShortName + "Impl";
    writer.definePackage( packageName );
    if ( cm.hasDateField() ) writer.addImport( "scs.javax.lang.Date" );
    if ( cm.hasTimeField() ) writer.addImport( "scs.javax.lang.Time" );
    writer.startMainClass( false, implClassName, classShortName, null );
    writeImplConstructor( implClassName, null );
    writeImplConstructor( implClassName, keyFields );
    if ( allFields.length > keyFields.length ) writeImplConstructor( implClassName, allFields );
    writer.endClass();
  }

  private void writeImplConstructor ( String className, String[][] initializers ) throws IOException
  {
    writer.printConstructorHeader( className, initializers, true );
    writer.startBlock();
    StringBuffer code = new StringBuffer( "super(" );
    if ( initializers != null )for ( int i = 0; i < initializers.length; ++i ) {
      String name = initializers[i][1];
      String type = initializers[i][0];
      if ( i > 0 ) {
        code.append( "," );
      }
      code.append( " " ).append( name );
      if ( i == initializers.length - 1 ) {
        code.append( " " );
      }
    }
    code.append( ");" );
    writer.printiln( code.toString() );
    writer.endBlock();
    writer.println();
  }

  private void writeHeader () throws IOException
  {
    writer.definePackage( packageName );
    if ( cm.hasDateField() ) writer.addImport( "scs.javax.lang.Date" );
    if ( cm.hasTimeField() ) writer.addImport( "scs.javax.lang.Time" );
    writer.addImport( "scs.javax.rdb.StorableEntityBase" );
    writer.addImport( "scs.javax.rdb.RdbException" );
    if ( cm.isListable() ) writer.addImport( "scs.javax.rdb.StorableObjectList" );
    for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
      addImportIfRequired( cm.getComposition( ( String ) it.next() ).getClassName() );
    }
    boolean hasForeignKey = false;
    for ( Iterator it = referencedClassNames.keySet().iterator(); it.hasNext(); ) {
      String referencedClassName = ( String ) it.next();
      if ( ( ( Boolean ) referencedClassNames.get( referencedClassName ) ).booleanValue() ) {
        addImportIfRequired( referencedClassName );
        hasForeignKey = true;
      }
    }
    if ( hasForeignKey || !cm.isListable() ) {
      writer.addImport( "scs.javax.collections.List" );
      writer.addImport( "scs.javax.rdb.RdbSession" );
    }
  }

  private void addImportIfRequired ( String className )
  {
    if ( !ClassUtils.getPackageName( className ).equals( packageName ) ) {
      writer.addImport( className );
    }
  }

  private String[][] writeFields () throws IOException
  {
    String[][] allTheFields = new String[cm.getFieldCount()][];
    for ( int i = 0; i < cm.getFieldCount(); ++i ) {
      RdbField field = cm.getField( i );
      allTheFields[i] = new String[2];
      allTheFields[i][0] = simplifyTypeName( field.getJavaTypeName() );
      allTheFields[i][1] = field.getPropertyName();
      writer.printField( allTheFields[i][0], allTheFields[i][1] );
    }
    for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
      String compName = ( String ) it.next();
      String className = cm.getComposition( compName ).getClassName();
      writer.printField( ClassUtils.getSimpleClassName( className ) + ".Lista", compName );
    }
    return allTheFields;
  }

  private void writeConstructor ( String className, String[][] initializers ) throws IOException
  {
    if ( cm.hasAnyComposition() ) {
      writer.printConstructorHeader( className, initializers, false );
      writer.startBlock();
      for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
        String compName = ( String ) it.next();
        String c = ClassUtils.getSimpleClassName( cm.getComposition( compName ).getClassName() );
        writer.printiln( compName + " = new " + c + ".Lista();" );
      }
      if ( initializers != null )for ( int i = 0; i < initializers.length; ++i ) {
        String name = initializers[i][1];
        writer.printiln( BeanProperty.prefixSetterName( name ) + "( " + name + " );" );
      }
      writer.endBlock();
      writer.println();
    } else {
      writer.printConstructor( className, initializers, false );
    }
  }

  private void writeNewInstance ( String className, String[][] initializers ) throws IOException
  {
    StringBuffer header = new StringBuffer( "public static " );
    StringBuffer code = new StringBuffer( "return new " );
    header.append( className ).append( " newInstance (" );
    code.append( className ).append( "Impl(" );
    if ( initializers != null )for ( int i = 0; i < initializers.length; ++i ) {
      String name = initializers[i][1];
      String type = initializers[i][0];
      if ( i > 0 ) {
        header.append( "," );
        code.append( "," );
      }
      header.append( " " ).append( type ).append( " " ).append( name );
      code.append( " " ).append( name );
      if ( i == initializers.length - 1 ) {
        header.append( " " );
        code.append( " " );
      }
    }
    header.append( ")" );
    code.append( ");" );
    writer.printSimpleMethod( header.toString(), code.toString() );
  }

  private void writeCompositionGetters () throws IOException
  {
    for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
      String compName = ( String ) it.next();
      String compClass = ClassUtils.getSimpleClassName( cm.getComposition( compName ).getClassName() );
      writer.printGetter( compClass + ".Lista", compName );
    }
  }

  private void writeSimpleGettersSetters () throws IOException
  {
    for ( int i = 0; i < cm.getFieldCount(); ++i ) {
      RdbField field = cm.getField( i );
      writer.printGetter( simplifyTypeName( field.getJavaTypeName() ), field.getPropertyName() );
      printSetter( field );
    }
  }

  private void printSetter ( RdbField field ) throws IOException
  {
    String s = BeanProperty.prefixSetterName( field.getPropertyName() );
    String arg = "( " + simplifyTypeName( field.getJavaTypeName() ) + " " + field.getPropertyName() + " )";
    writer.printiln( "public void " + s + " " + arg );
    writer.startBlock();
    writer.printiln( "this." + field.getPropertyName() + " = " + field.getPropertyName() + ";" );
    for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
      String compName = ( String ) it.next();
      String compClass = cm.getComposition( compName ).getClassName();
      DeclarativeClassMapping compStcm = getFromPool( compClass );
      if ( compStcm.hasListKey( field.getPropertyName() ) ) {
        writer.printiln( compName + "." + s + "( " + field.getPropertyName() + " );" );
      }
    }
    writer.endBlock();
    writer.println();
  }

  private static String simplifyTypeName ( String typename )
  {
    int dot = typename.lastIndexOf( '.' );
    return dot >= 0 ? typename.substring( dot + 1 ) : typename;
  }

  private void writeListClass () throws IOException
  {
    writer.startInnerClass( "Lista", "StorableObjectList", null );
    writer.printiln( "private " + classShortName + " keyEntity = new " + classShortName + "Impl();" );
    writer.println();
    writer.printSimpleMethod( "protected Object getKeyEntity ()", "return keyEntity;" );
    writeAddMethod();
    String[] listKeys = cm.getListKey();
    for ( int i = 0; i < listKeys.length; ++i ) {
      RdbField field = cm.getField( listKeys[i] );
      printListSetter( field );
      String simpleType = simplifyTypeName( field.getJavaTypeName() );
      String g = BeanProperty.prefixGetterNameForType( field.getPropertyName(), simpleType );
      writer.printGetter( simpleType, field.getPropertyName(), "return keyEntity." + g + "();" );
    }
    writer.endClass();
    writer.println();
  }

  private void writeAddMethod () throws IOException
  {
    writer.printiln( "public boolean add ( Object o )" );
    writer.startBlock();
    writer.printiln( classShortName + " entity = ( " + classShortName + " ) o;" );
    String[] listKeys = cm.getListKey();
    for ( int i = 0; i < listKeys.length; ++i ) {
      RdbField field = cm.getField( listKeys[i] );
      String g = BeanProperty.prefixGetterNameForType( field.getPropertyName(), simplifyTypeName( field.getJavaTypeName() ) );
      String s = BeanProperty.prefixSetterName( field.getPropertyName() );
      writer.printiln( "entity." + s + "( " + g + " () );" );
    }
    writer.printiln( "return super.add( entity );" );
    writer.endBlock();
    writer.println();
  }

  private void printListSetter ( RdbField field ) throws IOException
  {
    String methodName = BeanProperty.prefixSetterName( field.getPropertyName() );
    String arg = " ( " + simplifyTypeName( field.getJavaTypeName() ) + " " + field.getPropertyName() + " )";
    String set = methodName + "( " + field.getPropertyName() + " )";
    writer.printiln( "public void " + methodName + arg );
    writer.startBlock();
    writer.printiln( "keyEntity." + set + ";" );
    writer.printiln( "for ( int i = 0; i < size(); ++i ) {" );
    writer.incIndent();
    writer.printiln( classShortName + " entity = ( " + classShortName + " ) get( i );" );
    writer.printiln( "entity." + set + ";" );
    writer.endBlock();
    writer.endBlock();
    writer.println();
  }

  private void writeLoaders () throws IOException
  {
    for ( Iterator it = cm.getReferencedClassAttributeNames().iterator(); it.hasNext(); ) {
      String fieldName = ( String ) it.next();
      String referencedClassName = cm.getReferencedClassName( fieldName );
      if ( ( ( Boolean ) referencedClassNames.get( referencedClassName ) ).booleanValue() ) {
        String referencedClassSimplyName = simplifyTypeName( referencedClassName );
        String instanceName = referencedClassSimplyName.toLowerCase();
        String args = " ( RdbSession session, " + referencedClassSimplyName + " " + instanceName + " )";
        String header = "public static List loadAllFor" + referencedClassSimplyName + args + " throws RdbException";
        String code = "return loadAll( session, " + classShortName + ".class, \"" + fieldName + "\", " + instanceName + " );";
        writer.printSimpleMethod( header, code );
      }
    }
    if ( !cm.isListable() ) {
      String header = "public static List loadAll ( RdbSession session ) throws RdbException";
      String code = "return loadAll( session, " + classShortName + ".class );";
      writer.printSimpleMethod( header, code );
    }
  }

}
