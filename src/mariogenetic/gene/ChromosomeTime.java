/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.gene;

import java.util.Random;
import mariogenetic.Global;
import mariogenetic.Global.Keys;

/**
 *
 * @author alice
 */
public class ChromosomeTime extends Chromosome{

    public Global.Keys[] moves = new Global.Keys[Chromosome.arr_length]; //30 sekund
    public Global.Keys[] special = new Global.Keys[Chromosome.arr_length];
    
    public ChromosomeTime()
    {     
        GeneticsConfig gc = GeneticsConfig.getInstance();

        for (int i = 0; i < moves.length; i++) {            
            moves[i] = gc.getRandomMove();
        }

        for (int i = 0; i < special.length; i++) {
            special[i] = gc.getRandomSpecial();
        }
    }

    public static void main(String[] args)
    {
        ChromosomeTime ct = new ChromosomeTime();
        for(Keys k: ct.special)
            System.out.print(k+" ");

        Chromosome[] par = {ct};
        ChromosomeTime c2 = new ChromosomeTime(par);
        System.out.println("");
        for(Keys k: c2.special)
            System.out.print(k+" ");
    }


    public ChromosomeTime(Chromosome[] parents)
    {
        GeneticsConfig gc = GeneticsConfig.getInstance();

        for (int i = 0; i < moves.length; i++) {
            Global.Keys[] modifiers = new Global.Keys[parents.length];
            for (int j = 0; j < modifiers.length; j++) {
                modifiers[j]=parents[j].getMovesArray()[i];
            }
            moves[i] = gc.getRandomMove(modifiers);
        }

        for (int i = 0; i < special.length; i++) {
            Global.Keys[] modifiers = new Global.Keys[parents.length];
            for (int j = 0; j < modifiers.length; j++) {
                modifiers[j]=parents[j].getSpecialArray()[i];
            }
            special[i] = gc.getRandomSpecial(modifiers);
        }
    }

    Keys[] getMovesArray() {
        return moves;
    }
    Keys[] getSpecialArray() {
        return special;
    }

    public boolean isEnd(long time)
    {
        return time>=moves.length;
    }
    public Global.Keys getCurrentMove(long time)
    {
        return moves[(int)time%moves.length];
    }
    public Global.Keys getSpecial(long time)
    {
        return special[(int)time%special.length];
    }
//    public void calcFunc()
//    {
//        final_score=0;
//        if(final_state.result==GameState.RESULT_DEAD)
//        {
//            final_score-=40;
//        }
//        else if(final_state.result==GameState.RESULT_WON)
//        {
//            final_score+=30;
//        }
//        final_score += final_state.score*2;
//        final_score += 15*((Chromosome.arr_length*100)/final_state.timeElapsed());
//    }

    @Override
    public String toString()
    {
        String s = "State not Set";
        if(resultData!=null)
            s=resultData.toString();
        return s;
    }

    public int compareTo(Object o) {
        ChromosomeTime ct = (ChromosomeTime)o;
       return (resultData.getFinalScore()<ct.resultData.getFinalScore()?-1:(resultData.getFinalScore()>ct.resultData.getFinalScore()?1:0));
    }
}
