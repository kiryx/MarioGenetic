/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.gene;

import mariogenetic.game.GameState;

/**
 *
 * @author alice
 */
public class ResultData {

    private int final_score=-1;
    public int final_state;
    public int score;
    public long time_elapsed;
    public ResultData(GameState gs)
    {
        getDataFromGameState(gs);
    }
    public int getFinalScore()
    {
        if(final_score<0)
            this.calcFunc();
        return final_score;
    }
    public void calcFunc()
    {
        final_score=0;
        if(final_state==GameState.RESULT_DEAD)
        {
            final_score -= 40;
        }
        else if(final_state==GameState.RESULT_WON)
        {
            final_score += 30;
        }
        final_score += score*2;
        //final_score += 15*((Chromosome.arr_length*100)/time_elapsed);
    }
    public void getDataFromGameState(GameState gs)
    {
        score = gs.score;
        time_elapsed = gs.timeElapsed();
        final_state = gs.result;
    }
    public String toString()
    {
//        long now = new Date().getTime();
        return String.format("Result: %s Score: %d Time: %d Final Score %d",GameState.result_strings[final_state] ,score,time_elapsed,this.getFinalScore());
    }
}
