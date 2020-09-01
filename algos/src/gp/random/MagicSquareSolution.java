package gp.random;

// import org.junit.jupiter.api.Test;

class MagicSquareGenerator {
  
  public int[][] getSquareOfOrder(int n) {

    int[][] magicSquare = new int[n][n];
    
    // 1's position
    int i = n/2;
    int j = n-1;
    
    for (int num = 1; num <= n*n; ) {
      
      if (i == -1 && j == n) {
        
        j = n-2; i = 0;
      } else {
        
        if (j == n) j = 0;
        if (i < 0) i = n-1;
      }
      
      
      if (magicSquare[i][j] != 0) {
        
        j -= 2; i++;
        continue;
      } else {
        
        magicSquare[i][j] = num++;        
      }

      j++; i--;
    }
    
    
    return magicSquare;
  }
}

public class MagicSquareSolution {
  
  public static void main(String[] args) {
    
    int[][] magicSquare = new MagicSquareGenerator().getSquareOfOrder(3);
    
    System.out.println("The Magic Square for 3:"); 
    System.out.println("Sum of each row or column is "+3*(3*3+1)/2+"."); 
    for(int i=0; i<3; i++) { 
      
      for(int j=0; j<3; j++) 
        System.out.print(magicSquare[i][j]+" "); 
      System.out.println(); 
    }
  }
  
  // @Test
  public void test() {}
}