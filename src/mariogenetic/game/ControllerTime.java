/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.event.KeyEvent;
import mariogenetic.Global;
import mariogenetic.gene.Chromosome;
import mariogenetic.gene.ChromosomeTime;
import mariogenetic.gene.Population;
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
        p.generateNew(Global.population_size);
    }

    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
    }

    public void generateEvent()
    {
        long time = Global.frame_main.gamestate.timeElapsed();

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
            c1.setGameState(m.gamestate);
            c1.calcFunc();
            System.out.println(c1);
            current_chromosome++;
            if(current_chromosome==p.chromosomes.size())
            {
                p.nextPopulation();
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

    }

}
