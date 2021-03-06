package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.core.logic.Move;
import de.fhws.gos.ss17.core.logic.Position;
import de.fhws.gos.ss17.core.utils.PositionToken;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by awinter on 10.05.17.
 */
public class EvaluateMoves {

  private static final PositionToken friendly = PositionToken.PLAYER_ONE;

  private static List<Pair> fillMove(Board board, Phase phase)throws GameException{
    List<Pair> moves = new ArrayList<>();
    for(Move move : PossibleMoves.getPossibleMoves(board, phase, friendly)){
      Pair pair = new Pair(move, 0);
      moves.add(pair);
    }

    return moves;
  }

  //Evaluate if enemy has 2 stones somewhere and get in there to block them
  //Block enemy stones from moving
  //"Zwickmuehle"
  private static List<Pair> evaluateMoves(List<Pair> moves, Board board) throws GameException{
    for(Pair pair : moves){
      if(pair.getMove().getRemoveId() > -1)
        pair.setValue(pair.getValue() +10);
      if(allyAround(board, pair.getMove().getToId()) > 0){
        pair.setValue(pair.getValue() + allyAround(board, pair.getMove().getToId()) * 2);
      }
    }
    return moves;
  }

  public static Move getBestMove(Board board, Phase phase) throws GameException{
    Pair bestMove = new Pair(null, -50);
    List<Pair> moves = fillMove(board, phase);
    moves = evaluateMoves(moves, board);
    for(Pair pair : moves){
      if(pair.getValue() > bestMove.getValue())
        bestMove = pair;
    }
    return bestMove.getMove();
  }


  private static int allyAround(Board board, int toId) throws GameException{
    int counter = 0;
    for(Position position : board.getPosition(toId).getNeighbors()){
      if(position.getPositionToken().equals(friendly)) counter++;
    }
    return counter;
  }

}
