package gp.dp;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Given 2 strings X1...m and Y1...n,
 *
 * 1) The length of their longest common subsequence is deduced by the following
 * method
 *
 * Assume Di,j is the length of the LCS of X1...i and Y1...j
 *
 * If Xi == Yj, they both contribute to the LCS Hence, Di,j = Di-1,j-1 + 1
 *
 * If Xi != Yj, then one of them does not contribute to the LCS and can be
 * dropped Hence, Di,j = max(Di-1,j , Di,j-1)
 *
 * If i == 0 || j == 0 then Di,j = 0
 *
 * Finally the length is given by D[m][n]. D is the LCS matrix
 *
 * 2) Retrieving one or all of the LCSs Backtracking the LCS matrix, gives any
 * one or all of the LCSs
 *
 * 3) Number of all common subsequences ACS The number of All Common
 * SubSequences of two Strings is an indication of their similarity It is
 * deduced by the following method
 *
 * Assume Ni-1,j-1 is the number of ACSs of two strings X1...i-1 and Y1...j-1.
 * Then
 *
 * Ni,j = 2 * Ni-1,j-1 if Xi == Yj Ni,j = Ni,j-1 + Ni-1,j - Ni-1,j-1 if Xi != Yj
 *
 * Also, Ni,0 = 1 = N0,j
 *
 * Nm,n then gives the number of ACSs of two strings X1...m and Y1...n
 *
 * @author gautampriya
 *
 */
public class LCS {
  private String s1, s2;

  public LCS() {
  }

  public LCS(final String s1, final String s2) {
    this.s1=s1;
    this.s2=s2;
  }

  public Set<String> getAllLongestCommonSequences() {
    if ((this.s1==null)||(this.s2==null)||(this.s1.trim().length()==0)||(this.s2.trim().length()==0))
      return new HashSet<>();

    return this.backtrack(this.generateLCSMatrix(),this.s1,this.s2,this.s1.length(),this.s2.length());
  }

  public int getLength() {
    if ((this.s1==null)||(this.s2==null)||(this.s1.trim().length()==0)||(this.s2.trim().length()==0))
      return 0;

    final int[][] d=this.generateLCSMatrix();
    return d[this.s1.length()][this.s2.length()];
  }

  public String getLongestCommonSubSequence() {
    if ((this.s1==null)||(this.s2==null)||(this.s1.trim().length()==0)||(this.s2.trim().length()==0))
      return new String();

    final int[][] d=this.generateLCSMatrix();
    final StringBuilder result=new StringBuilder();
    int i=this.s1.length(), j=this.s2.length();
    while ((i>0)&&(j>0)) {
      if (this.s1.charAt(i-1)==this.s2.charAt(j-1)) {
        result.append(this.s1.charAt(i-1));
        i--;
        j--;
      } else if (d[i-1][j]>d[i][j-1]) {
        i--;
      } else {
        j--;
      }
    }
    return result.reverse().toString();
  }

  public int getNumberOfACSs() {
    if ((this.s1==null)||(this.s2==null)||(this.s1.trim().length()==0)||(this.s2.trim().length()==0))
      return 0;

    final int m=this.s1.length(), n=this.s2.length();
    final int[][] acs=new int[m+1][n+1];

    for (int i=0; i<=n; i++) {
      acs[0][i]=1;
    }
    for (int i=0; i<=m; i++) {
      acs[m][0]=1;
    }

    for (int i=1; i<=m; i++) {
      for (int j=1; j<=n; j++) {

        if (this.s1.charAt(i-1)==this.s2.charAt(j-1)) {
          acs[i][j]=2*acs[i-1][j-1];
        } else {
          acs[i][j]=(acs[i-1][j]+acs[i][j-1])-acs[i-1][j-1];
        }
      }
    }
    return acs[m][n];
  }

  private Set<String> backtrack(final int[][] d, final String s1, final String s2, final int i, final int j) {
    if ((i==0)||(j==0))
      return new HashSet<>();

    if (s1.charAt(i-1)==s2.charAt(j-1)) {
      final Set<String> result=this.backtrack(d,s1,s2,i-1,j-1);
      for (final Iterator<String> iterator=result.iterator(); iterator.hasNext();) {
        final String entry=iterator.next();

        iterator.remove();
        result.add(entry+s1.charAt(i-1));
      }
      return result;
    }
    final Set<String> result=new HashSet<>();
    if (d[i][j-1]>=d[i-1][j]) {
      result.addAll(this.backtrack(d,s1,s2,i,j-1));
    }
    if (d[i-1][j]>=d[i][j-1]) {
      result.addAll(this.backtrack(d,s1,s2,i-1,j));
    }
    return result;
  }

  private int[][] generateLCSMatrix() {

    final int m=this.s1.length(), n=this.s2.length();
    final int[][] d=new int[m+1][n+1];
    for (int i=0; i<=n; i++) {
      d[0][i]=0;
    }
    for (int i=0; i<=m; i++) {
      d[i][0]=0;
    }
    for (int i=1; i<=m; i++) {
      for (int j=1; j<=n; j++) {
        if (this.s1.charAt(i-1)==this.s2.charAt(j-1)) {
          d[i][j]=d[i-1][j-1]+1;
        } else {
          d[i][j]=Math.max(d[i-1][j],d[i][j-1]);
        }
      }
    }
    return d;
  }
}
