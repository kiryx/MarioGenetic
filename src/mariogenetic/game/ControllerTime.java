/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import mariogenetic.GeneticsConf;
import mariogenetic.Global;
import mariogenetic.gene.Chromosome;
import mariogenetic.gene.ChromosomeTime;
import mariogenetic.gene.Population;
import mariogenetic.gene.ResultData;
import mariogenetic.objects.Actor;

/**
 *
 * @author alice
 */
public class ControllerTime extends Controller{


    public Population p;
    public int current_chromosome=0;
    //ChromosomeTime c1;
    public ControllerTime()
    {
        //c1 = new ChromosomeTime();
        p = new Population();
        p.generateNew(GeneticsConf.population_size);
    }

    public void resetPopulation()
    {
        p = new Population();
        p.generateNew(GeneticsConf.population_size);
        current_chromosome=0;
    }
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
    }

    public void generateEvent()
    {
        long time = Global.main.gamestate.timeElapsed();

        long time10 = time/100; //10 razy na sek

        Actor a = m.resources.actors.get(0);
//        System.out.println(p.chromosomes.size());
        ChromosomeTime c1 = (ChromosomeTime) p.chromosomes.get(current_chromosome);

        long move = c1.getCurrentMove(time10);
        long special = c1.getSpecial(time10);


        //jesli koniec czasu lub wczesniejszy koniec
        if(c1.isEnd(time10) || m.gamestate.result!=GameState.RESULT_NONE)
        {
//            System.out.println("XX "+current_chromosome);
//            for (int i = 0; i < p.chromosomes.size(); i++) {
//
//                System.out.println(p.chromosomes.get(i));
//
//            }
//            System.out.println("XX");
            if(c1.isEnd(time10))
            {
                m.gamestate.result = GameState.RESULT_TIMEOUT;
            }
            c1.setResultData(new ResultData(m.gamestate));
            c1.resultData.calcFunc();
            System.out.print(Global.global_result_counter+++" ");
            System.out.println(c1);
            current_chromosome++;
            if(current_chromosome==p.chromosomes.size())
            {
                Global.shuffling_resources=true;
                p.nextPopulation();
                Global.shuffling_resources=false;
                current_chromosome=0;
            }
            //current_chromosome%=p.chromosomes.size();
            m.gamestate.reset();
            return;
        }
        if(special==Global.MOVE_JUMP)
        {
            a.jump();
        }
        //poruszanie sie
        if(move==Global.MOVE_LEFT)
        {
            a.left();
        }
        else if(move==Global.MOVE_RIGHT)
        {
            a.right();
        }
        else if(move==Global.MOVE_NONE)
        {
            a.stopX();
        }

    }

   

}
