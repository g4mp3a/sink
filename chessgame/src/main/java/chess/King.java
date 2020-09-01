package chess;

public class King extends Piece {

  private boolean castlingDone=false;

  public King(boolean white) {

    super(white);
  }

  @Override
  public boolean isMoveAllowed(Board b, Square s, Square e) {

    if ( e.getPiece().isWhite()==isWhite() ) return false;

    int x=Math.abs(s.getX()-e.getX());
    int y=Math.abs(s.getY()-e.getY());
    if ( x+y==1||x+y==2 ) {
      // TODO: check if king wont be endangered
      return true;
    }
    return isValidCastlingMove(b,s,e);
  }

  public boolean isCastlingDone() {

    return castlingDone;
  }

  public void setCastlingDone(boolean castlingDone) {

    this.castlingDone=castlingDone;
  }

  boolean isValidCastlingMove(Board b, Square s, Square e) {

    if ( isCastlingDone() ) return false;

    // TODO: Implement check castling logic
    return true;
  }

}
