package de.fhws.gos.ss17.game;


import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.logic.Player;
import de.fhws.gos.core.utils.GameStatus;
import de.fhws.gos.remote.exceptions.GameLostException;
import de.fhws.gos.remote.exceptions.GameTieException;
import de.fhws.gos.remote.exceptions.GameWonException;
import de.fhws.gos.ss17.exceptions.CheckedExceptions;

/**
 * Created by awinter on 05.05.17.
 */
public abstract class AbstractGame implements de.fhws.gos.core.logic.Game {

  protected Board board;
  protected Player playerOne;
  protected Player playerTwo;
  protected Player currentPlayer;

  public AbstractGame(Board board) {
    this.board = board;
  }

  public AbstractGame(Board board, Player playerOne, Player playerTwo) {
    this.board = board;
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
  }

  public void initGame() {

  }

  public void startGame() {
    for (; this.board.getCurrentGameStatus().equals(GameStatus.RUNNING); this.currentPlayer =
        this.currentPlayer.equals(this.playerOne) ? (this.currentPlayer = this.playerTwo)
            : (this.currentPlayer = this.playerOne)) {
      try {
        Move nextMove = currentPlayer.getNextMove(board);
        board.executeMove(nextMove, currentPlayer.getPlayerToken());
        board.printBoard();
      } catch (GameLostException ex) {
        this.board.setCurrentGameStatus(GameStatus.PLAYER_TWO_WON);
        printGameResult();
      } catch (GameWonException ex) {
        this.board.setCurrentGameStatus(GameStatus.PLAYER_ONE_WON);
        printGameResult();
      } catch (GameTieException ex) {
        this.board.setCurrentGameStatus(GameStatus.TIE);
        printGameResult();
      } catch (GameException ex) {
        CheckedExceptions.catchGameException(ex);
      }
    }

  }


  public void printGameResult() {
    System.out.println(
        "Moves for Player One: " + board.getNumberOfMovesForPlayer(playerOne.getPlayerToken()));
    System.out.println(
        "Moves for Player Two: " + board.getNumberOfMovesForPlayer(playerTwo.getPlayerToken()));
    GameStatus status = board.getCurrentGameStatus();
    if (status.equals(GameStatus.ERROR)) {
      System.out.println("Error while Game");
    } else if (status.equals(GameStatus.PLAYER_ONE_WON)) {
      System.out.println("Congrats Player One");
    } else if (status.equals(GameStatus.PLAYER_TWO_WON)) {
      System.out.println("Congrats Player Two");
    } else if (status.equals(GameStatus.TIE)) {
      System.out.println("The Game was a tie");
    }
  }


  public void setPlayers(Player playerOne, Player playerTwo) {
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
  }
}
