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
        GeneticsConfig gc = GeneticsConfig.getInstance();
        final_score=0;
        double multiplier = 1.0;
        if(final_state==GameState.RESULT_TIMEOUT)
        {
            multiplier = (Double)gc.get_parameter(GeneticsConfig.Param.FUNCTION_TIMEOUT_MULTIPLIER);
        }
        else if(final_state == GameState.RESULT_WON)
        {
            multiplier = (Double)gc.get_parameter(GeneticsConfig.Param.FUNCTION_WON_MULTIPLIER);
        }
        else if(final_state == GameState.RESULT_LOST)
        {
            multiplier = (Double)gc.get_parameter(GeneticsConfig.Param.FUNCTION_DEAD_MULTIPLIER);
        }
        final_score += score;
        Integer max_time =(Integer)gc.get_parameter(GeneticsConfig.Param.MAXIMUM_TIME);
        Double time_mult = (Double) gc.get_parameter(GeneticsConfig.Param.FUNCTION_TIME_MULTIPLIER);
        final_score += ((double)max_time/((double)time_elapsed)*time_mult);
        
        final_score = (int)(final_score*multiplier);
        
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
