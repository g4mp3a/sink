package gp.dp;

/**
 * 1) Binary addition
 * 
 * @author gautampriya
 * 
 */
public class BinaryNumbers {
  private String b1, b2;

  public BinaryNumbers setB1(String b1) {
    this.b1=b1;
    return this;
  }

  public BinaryNumbers setB2(String b2) {
    this.b2=b2;
    return this;
  }

  public BinaryNumbers() {
  }

  public BinaryNumbers(String b1, String b2) {
    this.b1=b1;
    this.b2=b2;
  }

  public String getSum() {
    String carry="0";
    StringBuilder result=new StringBuilder();
    for (int i=b1.length()-1, j=b2.length()-1; i>=0||j>=0||carry.equals("1");) {

      String b1Bit="0";
      if (i>=0) {
        b1Bit=String.valueOf(b1.charAt(i--));
      }
      String b2Bit="0";
      if (j>=0) {
        b2Bit=String.valueOf(b2.charAt(j--));
      }
      String operands=carry.concat(b1Bit).concat(b2Bit);
      int countOfOnes=operands.length()-operands.replace("1","").length();

      if (countOfOnes==1) {
        result.append("1");
        carry="0";
      } else if (countOfOnes==2) {
        result.append("0");
        carry="1";
      } else if (countOfOnes==3) {
        result.append("1");
        carry="1";
      }
    }
    return result.reverse().toString();
  }

}
