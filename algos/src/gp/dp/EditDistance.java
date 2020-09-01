package gp.dp;

/**
 * Edit distances between 2 Strings - src String A and the dest String B
 * 
 * 1) Levenshtein distance - calculates the cost of converting A0...m-1 to
 * B0...n-1 Allowed operations are insertion, deletion and substitution
 * 
 * Assume Dm+1,n+1 such that Di,j is the cost of converting A0...i-1 to B0...j-1
 * Then, D0,j = j for j in {0, n} insert Bj Di,0 = i for i in {0, m} delete Ai
 * 
 * If Ai == Bj, then no operation is needed Di,j = Di-1,j-1
 * 
 * If Ai != Bj, then either a deletion, or an insertion or a substitution is
 * needed Di,j = 1 + min(Di,j-1 , Di-1,j , Di-1,j-1)
 * 
 * Dm,n holds the answer
 * 
 * 2) LCS distance is similar to above except that allowable operations are
 * insertion and deletion
 * 
 * 3) Hamming Distance between 2 Strings (of equal length) is the count of the
 * number positions at which their corresponding symbols are different
 * 
 * 4) Hamming Weight of a string is the count of the number of non zero symbols
 * == Hamming Distance between the String and the String consisting of all zero
 * symbols For binary strings, it is the popcount
 * 
 * 
 * @author gautampriya
 * 
 */
public class EditDistance {
  private String src;
  private String dest;

  public EditDistance setSrc(String src) {
    this.src=src;
    return this;
  }

  public EditDistance setDest(String dest) {
    this.dest=dest;
    return this;
  }

  public EditDistance() {
  }

  public EditDistance(String src, String dest) {
    this.src=src;
    this.dest=dest;
  }

  public int getHammingDistance(String s, String d) {
    setSrc(s);
    setDest(d);
    return getHammingDistance();
  }

  public int getHammingDistance() {
    if (src==null||dest==null)
      return -2;
    if (src.length()!=dest.length())
      return -1;

    int result=0;
    for (int i=0; i<src.length(); i++) {
      if (src.charAt(i)!=dest.charAt(i))
        result++;
    }
    return result;
  }

  public int getHammingDistance(int x, int y) {
    return getHammingWeight(x^y);
  }

  public int getHammingWeight(int i) {
    int result;
    for (result=0; i>0; result++)
      i&=i-1;
    return result;
  }

  public int getLevenshteinDistance() {
    if ((src==null||src.trim().length()==0)&&(dest==null||dest.trim().length()==0))
      return 0;

    int m=src.length(), n=dest.length();
    int[][] d=new int[m+1][n+1];

    // Base cases
    for (int i=0; i<=m; i++) {
      d[i][0]=i;
    }
    for (int j=0; j<=n; j++) {
      d[0][j]=j;
    }

    // Calculate other values using DP
    for (int i=1; i<=m; i++) {
      for (int j=1; j<=n; j++) {

        if (src.charAt(i-1)==dest.charAt(j-1)) {
          d[i][j]=d[i-1][j-1];
        } else {
          d[i][j]=1+Math.min(Math.min(d[i-1][j],d[i][j-1]),d[i-1][j-1]);
        }
      }
    }
    return d[m][n];
  }

  public int getLCSDistance() {
    if ((src==null||src.trim().length()==0)&&(dest==null||dest.trim().length()==0))
      return 0;

    int m=src.length(), n=dest.length();
    int[][] d=new int[m+1][n+1];

    // Base Cases
    for (int i=0; i<=m; i++) {
      d[i][0]=i;
    }
    for (int j=0; j<=n; j++) {
      d[0][j]=j;
    }

    // Solve the other cases using DP
    for (int i=1; i<=m; i++) {
      for (int j=1; j<=n; j++) {

        if (src.charAt(i-1)==dest.charAt(j-1))
          d[i][j]=d[i-1][j-1];
        else
          d[i][j]=1+Math.min(d[i-1][j],d[i][j-1]);
      }
    }
    return d[m][n];
  }
}
