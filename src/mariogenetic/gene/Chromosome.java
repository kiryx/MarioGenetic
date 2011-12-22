/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.gene;

import java.util.Random;
import mariogenetic.gene.GeneticsConfig.Keys;


/**
 *
 * @author alice
 */
public class Chromosome implements Comparable{

    public GeneticsConfig.Keys[] moves; //30 sekund
    public GeneticsConfig.Keys[] special;
    public int arr_length = 100; // 100 == 10 sekund
    public ResultData resultData;
    public Chromosome()
    {
        
        GeneticsConfig gc = GeneticsConfig.getInstance();
        Integer moves_per_sec = (Integer) GeneticsConfig.getInstance().getParameter(GeneticsConfig.Parameter.MOVES_PER_SECOND);
        Integer max_time = (Integer) GeneticsConfig.getInstance().getParameter(GeneticsConfig.Parameter.MAXIMUM_TIME);
        Integer size = (max_time*moves_per_sec)/1000;        
        
        this.arr_length = size;
        //arr_length = 100;
        moves = new GeneticsConfig.Keys[arr_length];
        special = new GeneticsConfig.Keys[arr_length];

        for (int i = 0; i < moves.length; i++) {            
            moves[i] = gc.getRandomMove();
        }

        for (int i = 0; i < special.length; i++) {
            special[i] = gc.getRandomSpecial();
        }
    }
    public void setResultData(ResultData rd)
    {
        resultData=rd;
    }

    public static void main(String[] args)
    {
        Chromosome ct = new Chromosome();
        for(Keys k: ct.special)
            System.out.print(k+" ");

        Chromosome[] par = {ct};
        Chromosome c2 = new Chromosome(par);
        System.out.println("");
        for(Keys k: c2.special)
            System.out.print(k+" ");
    }

    public void mutateMoves(Double breadth)
    {
        Random rand = new Random();        
        for (int i = 0; i < moves.length; i++) {
            Double d = rand.nextDouble();
            if(d<breadth)
            {
                moves[i]=GeneticsConfig.getInstance().getRandomMove();
            }            
        }
        
    }
    public void mutateSpecial(Double breadth)
    {
        Random rand = new Random();  
        for (int i = 0; i < special.length; i++) {
            Double d = rand.nextDouble();
            if(d<breadth)
            {
                special[i]=GeneticsConfig.getInstance().getRandomSpecial();
            }            
        }
    }    

    public Chromosome(Chromosome[] parents)
    {
        this();

        GeneticsConfig gc = GeneticsConfig.getInstance();        
        for (int i = 0; i < moves.length; i++) {
            GeneticsConfig.Keys[] modifiers = new GeneticsConfig.Keys[parents.length];
            for (int j = 0; j < modifiers.length; j++) {
//                System.out.println("list:"+parents);
//                System.out.println("par [j]"+parents[j]+" j="+j+" par.size="+parents.length);
//                System.out.println(parents[j].getMovesArray());
//                System.out.println(parents[j].getMovesArray()[i]);
                modifiers[j]=parents[j].getMovesArray()[i];
            }
            moves[i] = gc.getRandomMove(modifiers);
        }

        for (int i = 0; i < special.length; i++) {
            GeneticsConfig.Keys[] modifiers = new GeneticsConfig.Keys[parents.length];
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
        int index = (int) (time * moves.length / (Integer) GeneticsConfig.getInstance().getParameter(GeneticsConfig.Parameter.MAXIMUM_TIME));
        return index>=moves.length;
    }
    public GeneticsConfig.Keys getCurrentMove(long time)
    {
        int index = (int) (time * moves.length / (Integer) GeneticsConfig.getInstance().getParameter(GeneticsConfig.Parameter.MAXIMUM_TIME));
        return moves[index];
    }
    public GeneticsConfig.Keys getSpecial(long time)
    {
        int index = (int) (time * special.length / (Integer) GeneticsConfig.getInstance().getParameter(GeneticsConfig.Parameter.MAXIMUM_TIME));
        return special[index];
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
        Chromosome ct = (Chromosome)o;
       return (resultData.getFinalScore()<ct.resultData.getFinalScore()?-1:(resultData.getFinalScore()>ct.resultData.getFinalScore()?1:0));
    }
}
