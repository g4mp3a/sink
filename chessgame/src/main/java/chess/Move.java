package chess;

public class Move {

  private Player player;
  private Square start;
  private Square end;
  private Piece pieceMoved;
  private Piece pieceKilled;
  private boolean castling;

  public Move(Player player, Square s, Square e) {

    this.setPlayer(player);
    this.setStart(s);
    this.setEnd(e);
    this.setPieceMoved(s.getPiece());
    this.setPieceKilled(e.getPiece());
  }

  public boolean isCastling() {

    return castling;
  }

  public void setCastling(boolean castling) {

    this.castling=castling;
  }

  public Square getStart() {

    return start;
  }

  public void setStart(Square start) {

    this.start = start;
  }

  public Square getEnd() {

    return end;
  }

  public void setEnd(Square end) {

    this.end = end;
  }

  public Player getPlayer() {

    return player;
  }

  public void setPlayer(Player player) {

    this.player = player;
  }

  public Piece getPieceMoved() {

    return pieceMoved;
  }

  public void setPieceMoved(Piece pieceMoved) {

    this.pieceMoved = pieceMoved;
  }

  public Piece getPieceKilled() {

    return pieceKilled;
  }

  public void setPieceKilled(Piece pieceKilled) {

    this.pieceKilled = pieceKilled;
  }
}
