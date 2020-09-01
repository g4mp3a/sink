package gp.random;

/**
 * Things to remember about HCF or GCD
 * – If H is the HCF of two numbers A and B, then H is also a
 * factor of AX and BY
 * – If HCF (A,B) is H, then H is also the HCF of (A, A+B) – If HCF (A,B) is H,
 * then H is also the HCF of (A, A-B)
 * – If HCF (A,B) is H, then H is also the HCF of (A+B, A-B)
 * – If HCF=LCM for two numbers, then the numbers must be equal to each other.
 * – HCF of two or more
 * fractions is given by: (HCF of numerators of all the fractions) / (LCM of denominator of all the
 * fractions)
 */

/**
 * Is it possible to move from point (a,b) to (c,d) using only the followin
 * steps: (a, b) -> (a, a+b) or (a+b, b) or (a, a-b) or (a-b, b)
 */

class GCD {

  static int gcd1(final int i, final int j) {

    if ( i==j ) return i;
    if ( i>j ) return gcd1(i-j,j);
    return gcd1(i,j-i);
  }

  static int gcd2(int i, int j) {

    while ( i!=j ) {
      if ( i>j ) {
        i=i-j;
      } else {
        j=j-i;
      }
    }
    return i;
  }

  // Assumes inputs are such that i > j
  static int gcd3(final int i, final int j) {

    if ( j==0 ) return i;
    return gcd3(j,i%j);
  }

  static int gcd4(int i, int j) {

    if ( i<j ) {
      int t=i;
      i=j;
      j=t;
    }

    while ( j!=0 ) {
      final int t=j;
      j=i%j;
      i=t;
      /*
       * remainder = i % j; i = j; j = remainder;
       */
    }
    return i;
  }

  static boolean isPossible(int a, int b, int c, int d) {

    a=Math.abs(a);
    b=Math.abs(b);
    c=Math.abs(c);
    d=Math.abs(d);
    return gcd1(a,b)==gcd1(c,d);
  }

  public static void main(final String[] args) {

    final int x=35, y=15;
    final int a=20, b=25;
    if ( isPossible(x,y,a,b) ) {
      System.out.print("Yes");
    } else {
      System.out.print("No");
    }
  }

}
