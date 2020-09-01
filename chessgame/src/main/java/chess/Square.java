package chess;

public class Square {

  private Piece piece;
  private int x, y;

  public Square(int x, int y, Piece p) {

    this.x=x;
    this.y=y;
    this.piece=p;
  }

  public Piece getPiece() {

    return piece;
  }

  public void setPiece(Piece piece) {

    this.piece=piece;
  }

  public int getX() {

    return x;
  }

  public void setX(int x) {

    this.x=x;
  }

  public int getY() {

    return y;
  }

  public void setY(int y) {

    this.y=y;
  }
}
