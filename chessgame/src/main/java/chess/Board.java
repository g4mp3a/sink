package chess;

public class Board {

  Square[][] squares;

  public Board() {

    resetBoard();
  }

  public Square getSquare(int x, int y) {

    if ( x<0||x>7||y<0||y>7 )
      throw new IndexOutOfBoundsException("Both x and y should be between 0 and 7.");

    return squares[x][y];
  }

  private void resetBoard() {

    squares[0][0]=new Square(0,0,new Rook(true));
    squares[0][1]=new Square(0,1,new Knight(true));
    squares[0][2]=new Square(0,2,new Bishop(true));
    squares[0][3]=new Square(0,3,new Queen(true));
    squares[0][4]=new Square(0,4,new King(true));
    squares[0][5]=new Square(0,5,new Bishop(true));
    squares[0][6]=new Square(0,6,new Knight(true));
    squares[0][7]=new Square(0,7,new Rook(true));
    for (int j=0; j<8; j++) squares[1][j]=new Square(1,j,new Pawn(true));

    squares[7][0]=new Square(7,0,new Rook(false));
    squares[7][1]=new Square(7,1,new Knight(false));
    squares[7][2]=new Square(0,2,new Bishop(false));
    squares[7][3]=new Square(0,3,new Queen(false));
    squares[7][4]=new Square(0,4,new King(false));
    squares[7][5]=new Square(7,5,new Bishop(false));
    squares[7][6]=new Square(0,6,new Knight(false));
    squares[7][7]=new Square(0,7,new Rook(false));
    for (int j=0; j<8; j++) squares[6][j]=new Square(6,j,new Pawn(false));

    for (int i=2; i<6; i++) for (int j=0; j<8; j++) squares[i][j]=new Square(i,j,null);
  }

}
