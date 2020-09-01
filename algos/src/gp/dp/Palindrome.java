package gp.dp;

/**
 * 1) Check whether a given string is a plaindrome
 * 
 * 2) Minimum number of characters that need to be inserted to make Xm...n into
 * a palindrome Another 2D DP problem
 * 
 * Let D1,n be the minimum number of insertions needed for X1...n
 * 
 * Assume Y1...k is an optimal solution for Xi...j ie. Y1...k is the minimal
 * palindrome of Xi...j => Either Y1 == Xi or Yk == Xj => Y2...k-1 is an optimal
 * solution for either Xi+1...j when Y1 == Xi or Xi...j-1 when Yk == Xj or
 * Xi+1...j-1 when Y1 == Yk == Xi == Xj
 * 
 * => Di,j = 1 + min(Di+1,j , Di,j-1) when Xi != Xj => Di,j = Di+1,j-1 when Xi
 * == Xj
 * 
 * Also, Di,i = Di,i-1 = 0
 * 
 * We need to populate D in increasing order of j-i
 * 
 * @author gautampriya
 * 
 */
public class Palindrome {
  private String s;

  public Palindrome setS(String s) {
    this.s=s;
    return this;
  }

  public Palindrome() {
  }

  public Palindrome(String s) {
    setS(s);
  }

  public boolean isPalindromeUsingStringBuilder() {
    return s==null||s.trim().length()==0||s.equals(new StringBuilder(s).reverse().toString());
  }

  public boolean isPalindrome() {
    if (s==null||s.trim().length()==0)
      return true;

    int left=0, right=s.length()-1;
    while (left<right) {
      if (s.charAt(left)!=s.charAt(right))
        return false;
      left++;
      right--;
    }
    return true;
  }

  public int minNumberOfInsertionsNeededToMakeAPalindrome() {
    if (s==null||s.trim().length()==0)
      return 0;

    int n=s.length();
    int[][] d=new int[n][n];

    // Base Cases
    for (int i=0; i<n; i++) {
      for (int j=0; j<n; j++) {
        if (j<=i)
          d[i][j]=0;
      }
    }

    // Apply DP
    for (int t=1; t<n; t++) {
      for (int i=0, j=t; j<n; i++, j++) {
        if (s.charAt(i)==s.charAt(j))
          d[i][j]=d[i+1][j-1];
        else
          d[i][j]=1+Math.min(d[i][j-1],d[i+1][j]);
      }
    }

    return d[0][n-1];
  }
}
