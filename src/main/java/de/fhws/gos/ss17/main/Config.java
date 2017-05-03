package de.fhws.gos.ss17.main;

import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Player;
import de.fhws.gos.core.network.Connection;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.game.impl.BoardImpl;
import de.fhws.gos.network.impl.DefaultConnection;
//import de.fhws.gos.ss17.players.RandomPlayerWithRules;
import de.fhws.gos.remote.impl.RemoteBotPlayer;
import de.fhws.gos.ss17.players.*;

/**
 * (c) Tobias Fertig, FHWS 2017
 */
public class Config {

  public final static String HOST = "193.174.81.64";

  public final static int PORT = 3000;

  public final static String GROUP_ID = "-1";

  public final static String GAME_MODE = "remotebotgame";

  /**
   * This method is used to initialize a connection with the server. The DefaultConnection class can
   * be used.
   *
   * @return the initialized connection object.
   */
  public static Connection initConnection() {
    DefaultConnection connect = new DefaultConnection(HOST, PORT, GROUP_ID);
    return connect;
  }

  /**
   * This method is used to initialize a nine mens morris board. The BoardImpl class can be used.
   *
   * @return the initialized board object.
   */
  public static Board initBoard() {
    BoardImpl board = new BoardImpl();
    return board;
  }

  /**
   * This method is used to get a remote player that retrieves the moves from the server. In case of
   * bot games the RemoteBotPlayer class can be used. In case of versus games the RemoteVersusPlayer
   * class can be used.
   *
   * @param connection the connection that should be used by the remote player.
   * @return the initialized remote player.
   */
  public static Player getRemotePlayer(Connection connection) {
    RemoteBotPlayer remotePlayer = new RemoteBotPlayer(PositionToken.PLAYER_TWO, connection);
    return remotePlayer;
  }

  /**
   * This Method is used to play as a human.
   *
   * @return the initialized human player
   */
  public static Player getHumanPlayer() {
    HumanPlayer humanPlayer = new HumanPlayer(PositionToken.PLAYER_ONE);
    return humanPlayer;
  }

  public static Player getCyborgPlayer() {
    CyborgPlayer cyborgPlayer = new CyborgPlayer(PositionToken.PLAYER_ONE);
    return cyborgPlayer;
  }

  /**
   * This method is used to get a random player. The RandomPlayerWithRules class can be used.
   *
   * @return the initialized player.
   */

  public static Player getRandomPlayer() {
    RandomPlayerWithRules localPlayer = new RandomPlayerWithRules(PositionToken.PLAYER_ONE);
    return localPlayer;
  }

  public static Player getAdvancedRandomPlayer(){
    AdvancedRandomPlayer advancedRandomPlayer = new AdvancedRandomPlayer((PositionToken.PLAYER_ONE));
    return advancedRandomPlayer;
  }

  public static Player getLogPlayer(){
    LogPlayer logPlayer = new LogPlayer((PositionToken.PLAYER_ONE));
    return logPlayer;
  }
  public static Player getShortLogPlayer() {
    ShortLogPlayer localPlayer = new ShortLogPlayer(PositionToken.PLAYER_ONE);
    return localPlayer;
  }
}
