package gp.random;

class SumOfDivisors {

  public int divisorsSum(int n) {

    if ( n==0||n==1 ) return n;
    if ( n<0 ) n*=-1;

    int sqrt=(int) Math.ceil(Math.sqrt(n));

    int start=2;
    int step=1;
    if ( n%2!=0 ) { start=3; step=2; }
    int result=1+n;

    for (; start<=sqrt; start+=step) {
      if ( n%start==0 ) {
        result+=start;
        if ( n/start>sqrt ) result+=n/start;
      }
    }

    return result;
  }
}
