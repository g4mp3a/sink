package gp.random;

import java.util.Random;

public class Riddle {

  public String solution(String riddle) {

    if ( riddle.indexOf('?')==-1 ) return riddle;

    int len=riddle.length();
    int i=-1;
    Random r=new Random();
    while ( i!=len-1 ) {
      i=riddle.indexOf('?',i+1);
      if ( i==-1 ) break;
      char cAfter='\0', cBefore='\0';
      if ( i>0 ) cBefore=riddle.charAt(i-1);
      if ( i<len-1 ) cAfter=riddle.charAt(i+1);
      char p=(char) ('a'+r.nextInt(26));
      while ( p==cBefore||p==cAfter ) p=(char) ('a'+r.nextInt(26));
      riddle=riddle.replaceFirst("\\?",Character.toString(p));
    }
    return riddle;
  }

  public static void main(String[] args) {

    Riddle r=new Riddle();
    System.out.println(r.solution("ab"));
  }
}
