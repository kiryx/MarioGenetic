/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.gene;

import java.util.Random;
import mariogenetic.Global;
import mariogenetic.game.GameState;

/**
 *
 * @author alice
 */
public abstract class Chromosome implements Comparable{

    public static int[] possibleMoves = {Global.MOVE_NONE, Global.MOVE_LEFT, Global.MOVE_RIGHT};
    public static int[] possibleSpecial = {Global.MOVE_NONE, Global.MOVE_JUMP };
    public static GameState final_state = null;
    public static int arr_length = 100; // 100 == 10 sekund
    public int final_score;
    
    public Chromosome()
    {

    }
    public void getCurrentMove()
    {

    }
    public void setGameState(GameState gs)
    {
        final_state = new GameState(gs);
    }
}
