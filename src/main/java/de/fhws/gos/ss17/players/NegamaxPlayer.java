package de.fhws.gos.ss17.players;

import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.utils.GameStatus;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.main.Config;
import de.fhws.gos.ss17.players.utils.BoardState;
import de.fhws.gos.ss17.players.utils.Node;
import de.fhws.gos.ss17.players.utils.PossibleMoves;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achim on 08.06.2017.
 */
public class NegamaxPlayer extends AbstractPlayer{

  //generate possible Moves
  //getBoardScore
  //Copy Board
  private final static int MAX_VALUE = 9999;
  private final static int MIN_VALUE = -9999;
  private static final long timePerRound = 28 * 1000;
  private static final int depth = 3;

  public NegamaxPlayer(PositionToken playerToken){
    super(playerToken);
  }

  @Override
  protected Move getPlacingMove(Board board) throws GameException {
    int test = negamax(new Node(board,null), depth, 1, MAX_VALUE, MIN_VALUE, System.currentTimeMillis());
    return null;
  }

  @Override
  protected Move getMovingMove(Board board) throws GameException {
    return null;
  }

  @Override
  protected Move getFlyingMove(Board board) throws GameException {
    return null;
  }


  public int negamax(Node node, int depth, int playerValue, int alpha, int beta, long startTime) {
    PositionToken playerToken =
        (playerValue == 1) ? PositionToken.PLAYER_ONE : PositionToken.PLAYER_TWO;
    if (depth == 0 || ((timePerRound + startTime) > System.currentTimeMillis()) || (
        node.board.getCurrentGameStatus() != GameStatus.RUNNING)) {
      return playerValue * BoardState.getScore(node.board, playerValue);
    }
    List<Move> possibleMoves = PossibleMoves.getPossibleMoves(node.board, super.phase, playerToken);
    int bestValue = MIN_VALUE;
    List<Node> childNodes = new ArrayList<>();
    for (Move move : possibleMoves) {
      Board childBoard = Config.copyBoard((de.fhws.gos.ss17.game.Board) node.board);
      childBoard.executeMove(move, playerToken);
      childNodes.add(new Node(childBoard, move));
    }
    for (Node child : childNodes) {
      int v = -negamax(child, depth - 1, -playerValue, -alpha, -beta, startTime);
      if (bestValue < v)
        bestValue = v;
      if (alpha < v)
        alpha = v;
      if (alpha > beta)
        break;
    }
    return bestValue;
  }
}