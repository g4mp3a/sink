package chess;

public class Knight extends Piece {

  public Knight(boolean white) {

    super(white);
  }

  @Override
  public boolean isMoveAllowed(Board b, Square s, Square e) {

    if ( e.getPiece().isWhite()==isWhite() ) return false;

    int x=Math.abs(s.getX()-e.getX());
    int y=Math.abs(s.getY()-e.getY());

    return x*y==2;
  }

}
