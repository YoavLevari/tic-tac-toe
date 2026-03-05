package model;

public class BasicModel implements TikTackToeModel{

  private final XO[][] board;
  private XO turn;
  private XO winner;
  private int turnCount;

  public BasicModel() {
    board = new XO[3][3];
    turn = XO.X;
    turnCount = 0;
  }

  @Override
  public void changeTurn() {
    if (turn == XO.X) {
      turn = XO.O;
    }
    else {
      turn = XO.X;
    }
  }

  @Override
  public void playTurn(int row, int column) throws IllegalArgumentException {
    //TODO this looks a bit stupid but this is a way that I can make it that when a player wins there can not be more turns no matter what
    getWinner();
    if (isGameOver()){
      throw new IllegalArgumentException();
    }
    if (board[row][column] != null) {
      throw new IllegalArgumentException();
    }
    board[row][column] = turn;
    changeTurn();
    turnCount++;
  }

  @Override
  public Boolean isGameOver() {
    if (winner != null || turnCount == 9) {
      return true;
    }
    return false;
  }

  @Override
  public XO[][] getBoard() {
    return board.clone();
  }

  @Override
  public XO getWinner() {
    for (int i = 0; i < 3; i++ ) {
      if (horizantalHelp(i)){
        winner = board[i][0];
        return board[i][0];
      }
      if (verticalHelp(i)){
        winner = board[0][i];
        return board[0][i];
      }
    }
    if (diagnalHelp()){
      winner = board[1][1];
      return board[1][1];
    }
    return null;
  }

  private Boolean horizantalHelp(int row){
    if (board[row][0] == board[row][1] && board[row][1] == board[row][2]
        && board[row][0] != null) {
      return true;
    }
    return false;
  }

  private Boolean verticalHelp(int column){
    if(board[0][column] == board[1][column] && board[0][column] == board[2][column]
        && board[0][column] != null) {
      return true;
    }
    return false;
  }

  private Boolean diagnalHelp(){
    if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
      return true;
    }
    if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
      return true;
    }
    return false;
  }

  @Override
  public XO getTurn() {
    return turn;
  }
}
