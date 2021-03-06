package de.fhws.gos.ss17.players;


import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Move;
import de.fhws.gos.ss17.core.utils.PositionToken;
import de.fhws.gos.ss17.main.GameOfStonesMain;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Neuer on 22.04.2017.
 */
public class LogPlayer extends AdvancedRandomPlayer {

  public LogPlayer(PositionToken playerToken) {
    super(playerToken);
  }

  protected Move getMovingMove(Board board) throws GameException {
    Move enemymove = board.getLastMove();
    log("Enemy From: " + enemymove.getFromId() + " to "+ enemymove.getToId() + " (removed " + enemymove.getRemoveId() + ")\n");
    Move move = super.getMovingMove(board);
    log("Moving from: " + move.getFromId() + " to " + move.getToId() + " (removed " + move
        .getRemoveId() + ")\n");
    return super.getMovingMove(board);
  }

  protected Move getPlacingMove(Board board) throws GameException {
    try {
      Move enemymove = board.getLastMove();
      log("Enemy From: " + enemymove.getFromId() + " to " + enemymove.getToId() + " (removed "
          + enemymove.getRemoveId() + ")\n");
    }catch(Exception ex){
      log("We begin \n");
    }

    Move move = super.getPlacingMove(board);
    log("Placing to: " + move.getToId() + " (removed " + move.getRemoveId() + ")\n");
    return move;
  }

  protected Move getFlyingMove(Board board) throws GameException {
    Move enemymove = board.getLastMove();
    log("Enemy From: " + enemymove.getFromId() + " to "+ enemymove.getToId() + " (removed " + enemymove.getRemoveId() + ")\n");
    Move move = super.getFlyingMove(board);
    log("Flying from: " + move.getFromId() + " to " + move.getToId() + " (removed " + move
        .getRemoveId() + ")\n");
    return move;
  }

  private void log(String output) {
    File file = new File(GameOfStonesMain.logName);
    try {
      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file, true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(output);
      if (bw != null) {
        bw.close();
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    }

  }
}
