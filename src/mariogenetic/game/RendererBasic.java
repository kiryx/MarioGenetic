/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.Color;
import mariogenetic.objects.Actor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Iterator;
import javax.swing.JPanel;
import mariogenetic.*;
import mariogenetic.mapeditor.MapMain;
import mariogenetic.objects.Bonus;
import mariogenetic.objects.Terrain;

/**
 *
 * @author alice
 */
public class RendererBasic extends Renderer{


    
    private RendererBasic()
    {       
        camera = new Camera();
        camera.setTarget(Global.main.resources.actors.get(Global.camera_actor));
    }
    
    public static Renderer getInstance()
    {
        if(singleton == null)
            singleton = new RendererBasic();
        return singleton;
    }


    public static void drawGrid(Graphics2D g2,JPanel panel)
    {
//            int W = panel.getWidth();
            int W = 3000;
            int H = 3000;
//            int H = panel.getHeight();
//            double interX = W/100.0;
//            double interY = H/100.0;

            int gap = 5;

//            Point scr_drag = ((MapMain)panel).getScreenDragTotal();

            int cnt=0;
            Color gridA = Config.color_grid_light;
            Color gridB = Config.color_grid_dark;
            for (double i = -W; i <= W; i+=gap) {

                if(cnt%10==0)
                        g2.setColor(gridB);
                else
                        g2.setColor(gridA);

                g2.drawLine((int)i, -H, (int)i, H);

                cnt++;
            }
            g2.setColor(gridB);
            g2.drawLine(W-1, 0, W-1, H-1);
            cnt=0;
            for (double i = -H; i <= H; i+=gap) {

                    if(cnt%10==0)
                            g2.setColor(gridB);
                    else
                            g2.setColor(gridA);

                    g2.drawLine(-W, (int)i, W, (int)i);

                    cnt++;
            }
            g2.setColor(gridB);
            g2.drawLine(0, H-1, W-1, H-1);
    }
    public void render(Graphics2D g2)
    {
        
        //g2.setRenderingHint(Graphics2D.ANTIALIASING, Graphics2D.ANTIALIAS_ON);
        if(Global.main.resources.getMainActor()==null)
            return;
        Actor a = Global.main.resources.getMainActor();
        if(Config.show_debug)
        {


            g2.drawString(String.format("sleep: %d",Global.SLEEP_TIME), 30, 30);
            g2.drawString(Global.main.logic.getDebugString(), 30, 40);
            g2.drawString(String.format("x:%.2f y:%.2f",a.position.x,a.position.y ),30,50);
            g2.drawString(String.format("vx:%.2f vy:%.2f",a.velocity.x,a.velocity.y),30,60);
        
            if(Global.main.controller instanceof ControllerTime)
            {
                ControllerTime cont = (ControllerTime) Global.main.controller;
                g2.drawString(String.format("Chromosome: %d",cont.current_chromosome),30,80);
            }
            g2.drawString(Global.main.gamestate.toString(), 30, 70);
        }

        camera.update();
        Camera.translateDiffNeg(g2, camera.getPosition());
        //g2.translate(-camera.getPosition().x, -camera.getPosition().y);
        
        Main m = Global.main;
        g2.translate(m.getWidth()/2, m.getHeight()/2);
        if(!camera.follow && camera.tmp_drag!=null)
        {
            Camera.translateDiff(g2, camera.tmp_drag);
        }
//        drawGrid(g2, m);

        Iterator it;
        if(!Global.shuffling_resources)
        {
            Global.shuffling_resources=true;

                it = m.resources.actors.iterator();
                while(it.hasNext())
                {
                    Actor ac = (Actor)it.next();
                    ac.paint(g2);
                }

                it = m.resources.terrain.iterator();
                while(it.hasNext())
                {
                    Terrain t = (Terrain)it.next();
                    t.paint(g2);
                }

                it = m.resources.bonus.iterator();
                while(it.hasNext())
                {
                    Bonus b = (Bonus)it.next();
                    b.paint(g2);
                }
            Global.shuffling_resources=false;
        }

        if(!camera.follow && camera.tmp_drag!=null)
        {
            Camera.translateDiffNeg(g2, camera.tmp_drag);
        }

//        for(Actor ac : m.resources.actors)
//        {
//            ac.paint(g2);
//        }
//        for(Terrain t : m.resources.terrain)
//        {
//            t.paint(g2);
//        }
//        for(Bonus b : m.resources.bonus)
//        {
//            b.paint(g2);
//        }

    }
    public void reset()
    {
        camera.setTarget(Global.main.resources.getMainActor());
    }

}
