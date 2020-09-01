package gp.random;

public class BinaryMagic {

  public int solution(String S) {

    int count=0;
    while ( S.indexOf('1')!=-1 ) {
      if ( S.charAt(S.length()-1)=='0' ) {
        S=S.substring(0,S.length()-1);
        count++;
      }
      if ( S.charAt(S.length()-1)=='1' ) {
        S=S.substring(0,S.length()-1)+'0';
        count++;
      }
    }

    return count;
  }

  public static void main(String args[]) {

    BinaryMagic bm=new BinaryMagic();
    System.out.println(bm.solution("1111010101111"));
  }
}
