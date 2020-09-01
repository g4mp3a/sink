package gp.dp;

/**
 * Calculates the number of ways of writing a number n as the sum of 1,3 and 4
 * DP is the technique used - this is a single dimensional DP problem
 * 
 * Assume Dn is the number of ways Consider one possible way - x1 + x2 + x3 +
 * ... + xM = n
 * 
 * If xM == 1, then sum(x1...M-1) = n-1 => number of sums that end with 1 = Dn-1
 * If xM == 3, then sum(x1...M-1) = n-3 => number of sums that end with 3 = Dn-3
 * If xM == 4, then sum(x1...M-1) = n-4 => number of sums that end with 4 = Dn-4
 * 
 * Hence, Dn = Dn-1 + Dn-3 + Dn-4 <-- the recurrence
 * 
 * Base cases: D0 = 1 D1 = 1 D2 = 1 D3 = 2 D4 = D3 + D1 + D0 = 2 + 1 + 1 = 4
 * 
 * Dn = 0 for all n < 0
 * 
 * @author gautampriya
 * 
 */
public class NumberOfWaysToWriteN {
  private int n;
  private long result;

  public NumberOfWaysToWriteN setN(int n) {
    this.n=n;
    return this;
  }

  /**
   * Default constructor
   */
  public NumberOfWaysToWriteN() {
  }

  /**
   * @param n The number that we wish to write as the sum of 1, 3, and 4
   */
  public NumberOfWaysToWriteN(int n) {
    setN(n);
  }

  /**
   * Uses <code>calculate()</code> to calculate the number of ways. d[n] will
   * always contain the result.
   * 
   * @return Number of ways to write n as the sum of 1, 3, and 4
   */
  public long get() {
    calculate();
    return result;
  }

  private void calculate() {
    long[] d=new long[n+1];
    if (n<4)
      d=new long[4];
    d[0]=d[1]=d[2]=1;
    d[3]=2;
    for (int i=4; i<d.length; i++) {
      d[i]=d[i-1]+d[i-3]+d[i-4];
    }
    result=d[n];
  }
}
