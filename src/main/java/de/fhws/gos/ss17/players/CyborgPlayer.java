package de.fhws.gos.ss17.players;

import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Move;
import de.fhws.gos.ss17.core.utils.PositionToken;
import java.util.Scanner;

/**
 * Created by awinter on 04.04.17.
 */

public class CyborgPlayer extends AbstractPlayer {

  HumanPlayer human = new HumanPlayer(playerToken);
  RandomPlayerWithRules RandomPlayer = new RandomPlayerWithRules(playerToken);

  private Scanner sc = new Scanner(System.in);
  char change = 'n';

  public CyborgPlayer(PositionToken playerToken) {
    super(playerToken);
  }


  protected Move getPlacingMove(Board board) throws GameException {
    Move placement = RandomPlayer.getNextMove(board);
    System.out.println("Stein wird gesetzt auf: " + placement.getToId());
    System.out.print("Möchten Sie den Zug verändern?(y/n)");
    change = sc.next(".").charAt(0);

    if (change == 'y') {
      placement = human.getPlacingMove(board);
    }
    return placement;
  }

  protected Move getMovingMove(Board board) throws GameException {
    Move move = RandomPlayer.getNextMove(board);
    System.out
        .println("Stein wird verschoben von: " + move.getFromId() + " nach: " + move.getToId());
    System.out.print("Möchten Sie den Zug ändern? (y/n)");
    change = sc.next(".").charAt(0);
    if (change == 'y') {
      move = human.getMovingMove(board);
    }
    return move;
  }


  protected Move getFlyingMove(Board board) throws GameException {
    Move move = RandomPlayer.getNextMove(board);
    System.out
        .println("Stein wird verschoben von: " + move.getFromId() + " nach: " + move.getToId());
    change = sc.next(".").charAt(0);

    if (change == 'y') {
      move = human.getFlyingMove(board);
    }
    return move;
  }


}
