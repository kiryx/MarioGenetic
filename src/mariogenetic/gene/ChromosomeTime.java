/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.gene;

import java.util.Random;
import mariogenetic.GeneticsConf;
import mariogenetic.game.GameState;

/**
 *
 * @author alice
 */
public class ChromosomeTime extends Chromosome{

    public int[] moves = new int[Chromosome.arr_length]; //30 sekund
    public int[] special = new int[Chromosome.arr_length];
    
    public ChromosomeTime()
    {
        final_score=0;
        Random generator = new Random();

        for (int i = 0; i < moves.length; i++) {
            double d = generator.nextDouble();
            moves[i] = GeneticsConf.getMove(d);
//            moves[i] = Chromosome.possibleMoves[generator.nextInt(Chromosome.possibleMoves.length)]; // zwraca 1,2,3
        }

        for (int i = 0; i < special.length; i++) {
            special[i] = Chromosome.possibleSpecial[generator.nextInt(Chromosome.possibleSpecial.length)];
        }
    }
    public boolean isEnd(long time)
    {
        return time>=moves.length;
    }
    public int getCurrentMove(long time)
    {
        return moves[(int)time%moves.length];
    }
    public int getSpecial(long time)
    {
        return special[(int)time%special.length];
    }
    public void calcFunc()
    {
        final_score=0;
        if(final_state.result==GameState.RESULT_DEAD)
        {
            final_score-=40;
        }
        else if(final_state.result==GameState.RESULT_WON)
        {
            final_score+=30;
        }
        final_score += final_state.score*2;
        final_score += 15*((Chromosome.arr_length*100)/final_state.timeElapsed());
    }
    @Override
    public String toString()
    {
        String s = "";
        if(final_state!=null)
            s+=final_state.toString();
        else
            s+="State not set";
        s+=" Final Score:"+final_score;
        return s;
    }

    public int compareTo(Object o) {
        ChromosomeTime ct = (ChromosomeTime)o;
       return (this.final_score<ct.final_score?-1:(this.final_score>ct.final_score?1:0));
    }
}
