package gp.random;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class BitStringsBitwiseOR {

  // Complete the acmTeam function below.
  static int[] acmTeam(final String[] topic, final int m) {

    int maxTopics=0;
    int numTeams=0;
    for (int i=0; i<topic.length; ++i) {
      for (int j=i+1; j<topic.length; ++j) {
        String currPairTopics="";
        final int curr_bit=0;
        /*
         * for (int k=0; k<m; ++k)
         * { curr_bit = (topic[i].charAt(k)-'0') | (topic[j].charAt(k)-'0');
         *  currPairTopics += (char)(curr_bit + '0'); }
         */
        final BigInteger b1=new BigInteger(topic[i],2);
        final BigInteger b2=new BigInteger(topic[j],2);
        currPairTopics=b1.or(b2).toString(2);
        final int numTopics=new Long(currPairTopics.chars().filter(ch -> ch=='1').count())
            .intValue();
        if ( numTopics>maxTopics ) {
          maxTopics=numTopics;
          numTeams=1;
        } else if ( numTopics==maxTopics ) {
          numTeams++;
        }
      }
    }
    return new int[] { maxTopics, numTeams };
  }

  private static final Scanner scanner=new Scanner(System.in);

  public static void main(final String[] args) throws IOException {

    final BufferedWriter bufferedWriter=new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    final String[] nm=scanner.nextLine().split(" ");

    final int n=Integer.parseInt(nm[0]);

    final int m=Integer.parseInt(nm[1]);

    final String[] topic=new String[n];

    for (int i=0; i<n; i++) {
      final String topicItem=scanner.nextLine();
      topic[i]=topicItem;
    }

    final int[] result=acmTeam(topic,m);

    for (int i=0; i<result.length; i++) {
      bufferedWriter.write(String.valueOf(result[i]));

      if ( i!=(result.length-1) ) {
        bufferedWriter.write("\n");
      }
    }

    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
