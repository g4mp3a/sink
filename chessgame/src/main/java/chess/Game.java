package chess;

import java.util.List;

public class Game {

  private Player white, black;
  private Board board;
  private Player currentTurn;
  private GameStatus status;
  private List<Move> moves;

  public void initialize(Player white, Player black) {

    this.white=white;
    this.black=black;
    board=new Board();
    currentTurn=white;
    moves.clear();
  }

  public boolean hasEnded() {

    return !GameStatus.ACTIVE.equals(this.status);
  }

  public void setStatus(GameStatus status) {

    this.status=status;
  }

  public boolean move(Player p, int sX, int sY, int eX, int eY) {

    Square s=board.getSquare(sX,sY);
    Square e=board.getSquare(eX,eY);
    Move m=new Move(p,s,e);
    return doMove(p,m);
  }

  private boolean doMove(Player p, Move m) {

    Piece pieceToMove=m.getStart().getPiece();
    if ( pieceToMove==null ) return false;
    if ( p!=currentTurn ) return false;
    if ( pieceToMove.isWhite()!=p.isWhite() ) return false;
    if ( !pieceToMove.isMoveAllowed(board,m.getStart(),m.getEnd()) );

    Piece pieceKilled=m.getEnd().getPiece();
    if ( pieceKilled!=null ) {
      pieceKilled.setKilled(true);
      m.setPieceKilled(pieceKilled);
    }

    if ( pieceToMove!=null&&pieceToMove instanceof King
        &&((King) pieceToMove).isValidCastlingMove(board,m.getStart(),m.getEnd()) ) {
      m.setCastling(true);
    }

    moves.add(m);
    m.getEnd().setPiece(pieceToMove);
    m.getStart().setPiece(null);

    if ( pieceKilled!=null&&pieceKilled instanceof King ) {
      if ( currentTurn.isWhite() ) this.setStatus(GameStatus.WHITE_WIN);
      else this.setStatus(GameStatus.BLACK_WIN);
    }

    if ( currentTurn.isWhite() ) currentTurn=black;
    else currentTurn=white;

    return true;
  }
}
