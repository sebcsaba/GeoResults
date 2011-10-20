package scs.javax.lang;

public class DiscreteMath
{

  private DiscreteMath (){}

  public static boolean isPowerOfTwo(long x) {
    if (x<=0) return false;
    while ((x&1)==0) x>>=1;
    return x==1;
  }

  public static double log2(long x) {
    return Math.log(x)/Math.log(2);
  }

  public static long log2disc(long x) {
    if (x<=0) throw new IllegalArgumentException("not power of 2");
    int a=0;
    while ((x&1)==0) { x>>=1; ++a; }
    if (x!=1) throw new IllegalArgumentException("not power of 2");
    return a;
  }

}
