package com.pargroup.event;

import com.pargroup.model.Player;

/**
 * @author Rawad Aboudlal
 *
 */
public class PlayerWinEvent extends GameOverEvent {

  private final Player winner;

  /**
   * @param winner
   */
  public PlayerWinEvent(Player winner) {
    super();

    this.winner = winner;

  }

  /**
   * @return the winner
   */
  public Player getWinner() {
    return winner;
  }

}
