package scs.javax.io.writers;

import scs.javax.collections.ArrayUtils;
import scs.javax.collections.List;
import scs.javax.dii.BeanProperty;
import scs.javax.io.IOException;
import scs.javax.io.Writer;

public class JavaWriter extends ExtensibleWriter
{

  private int indent;

  private List importList;

  public JavaWriter ( Writer out )
  {
    super( out );
    indent = 0;
    importList = new List();
  }

  public void printIndent () throws IOException
  {
    for ( int i = 0; i < indent; ++i ) print( "  " );
  }

  public void printiln ( String str ) throws IOException
  {
    printIndent();
    println( str );
  }

  public void incIndent ()
  {
    ++indent;
  }

  public void startBlock () throws IOException
  {
    printiln( "{" );
    ++indent;
  }

  public void endBlock () throws IOException
  {
    --indent;
    printiln( "}" );
  }

  public void definePackage ( String packageName ) throws IOException
  {
    printiln( "package " + packageName + ";" );
    println();
  }

  public void addImport ( String className )
  {
    importList.add( className );
  }

  private void flushImports () throws IOException
  {
    Object[] im = importList.toArray();
    importList.clear();
    ArrayUtils.sort( im );
    for ( int i = 0; i < im.length; ++i ) printiln( "import " + im[i] + ";" );
    if ( im.length > 0 ) println();
  }

  public void startMainClass ( boolean isAbstract, String className, String parentClass, String[] interfaces ) throws
          IOException
  {
    startClass( true, false, isAbstract, className, parentClass, interfaces );
  }

  public void startInnerClass ( String className, String parentClass, String[] interfaces ) throws
          IOException
  {
    startClass( true, true, false, className, parentClass, interfaces );
  }

  public void startClass ( boolean isPublic, boolean isStatic, boolean isAbstract, String className, String parentClass,
                           String[] interfaces ) throws IOException
  {
    flushImports();
    printIndent();
    if ( isPublic ) print( "public " );
    else if ( isStatic ) print( "private " );
    if ( isStatic ) print( "static " );
    if ( isAbstract ) print( "abstract " );
    print( "class " + className );
    if ( parentClass != null ) print( " extends " + parentClass );
    if ( interfaces != null ) {
      print( " implements " );
      for ( int i = 0; i < interfaces.length; ++i ) {
        if ( i > 0 ) print( ", " );
        print( interfaces[i] );
      }
    }
    println();
    startBlock();
    println();
  }

  public void endClass () throws IOException
  {
    endBlock();
  }

  public void printField ( String type, String name ) throws IOException
  {
    printiln( "private " + type + " " + name + ";" );
    println();
  }

  public void printGetter ( String type, String name ) throws IOException
  {
    printGetter( type, name, "return " + name + ";" );
  }

  public void printGetter ( String type, String name, String code ) throws IOException
  {
    String g = BeanProperty.prefixGetterNameForType( name, type );
    printSimpleMethod( "public " + type + " " + g + " ()", code );
  }

  public void printSetter ( String type, String name ) throws IOException
  {
    printSetter( type, name, "this." + name + " = " + name + ";" );
  }

  public void printSetter ( String type, String name, String code ) throws IOException
  {
    String s = BeanProperty.prefixSetterName( name );
    printSimpleMethod( "public void " + s + " ( " + type + " " + name + " )", code );
  }

  public void printConstructorHeader ( String className, String[][] initializers, boolean isPublic ) throws
          IOException
  {
    printIndent();
    print( ( isPublic ? "public " : "protected " ) + className + " (" );
    if ( initializers != null ) {
      for ( int i = 0; i < initializers.length; ++i ) {
        print( " " + initializers[i][0] + " " + initializers[i][1] );
        if ( i < initializers.length - 1 ) print( "," );
      }
      print( " " );
    }
    println( ")" );
  }

  public void printConstructor ( String className, String[][] initializers, boolean isPublic ) throws IOException
  {
    printConstructorHeader( className, initializers, isPublic );
    startBlock();
    if ( initializers != null )for ( int i = 0; i < initializers.length; ++i ) {
      printiln( "this." + initializers[i][1] + " = " + initializers[i][1] + ";" );
    }
    endBlock();
    println();
  }

  public void printSimpleMethod ( String header, String code ) throws IOException
  {
    printiln( header );
    startBlock();
    printiln( code );
    endBlock();
    println();
  }

}
