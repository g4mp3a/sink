package chess;

public abstract class Player {

  protected boolean white;
  protected boolean human;

  public boolean isWhite() {

    return white;
  }

  public boolean isHuman() {

    return human;
  }
}

class HumanPlayer extends Player {

  public HumanPlayer(boolean white) {

    this.white=white;
    this.human=true;
  }
}

class ComputerPlayer extends Player {

  public ComputerPlayer(boolean white) {

    this.white=white;
    this.human=false;
  }
}
