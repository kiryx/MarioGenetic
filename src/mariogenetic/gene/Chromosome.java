/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.gene;

import java.util.Random;
import mariogenetic.main.GlobalVariables;

import mariogenetic.game.GameState;

/**
 *
 * @author alice
 */
public abstract class Chromosome implements Comparable{

//    public static int[] possibleMoves = {Global.MOVE_NONE, Global.MOVE_LEFT, Global.MOVE_RIGHT};
//    public static int[] possibleSpecial = {Global.MOVE_NONE, Global.MOVE_JUMP };
//    public static GameState final_state = null;
    public int arr_length = 100; // 100 == 10 sekund    
    public ResultData resultData;
    
    public Chromosome()
    {
        arr_length = 100;
    }
    public GeneticsConfig.Keys getCurrentMove()
    {
        return null;
    }
//    public void setGameState(GameState gs)
//    {
//        final_state = new GameState(gs);
//    }
    public void setResultData(ResultData rd)
    {
        resultData = rd;
    }

    GeneticsConfig.Keys[] getMovesArray() {
        return null;
    }
    GeneticsConfig.Keys[] getSpecialArray() {
        return null;
    }
}
