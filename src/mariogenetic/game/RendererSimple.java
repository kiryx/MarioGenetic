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
import mariogenetic.main.*;
import mariogenetic.mapeditor.MapEditor;
import mariogenetic.objects.Bonus;
import mariogenetic.objects.Terrain;

/**
 *
 * @author alice
 */
public class RendererSimple extends Renderer{


    
    private RendererSimple()
    {       
        camera = new Camera();
        camera.setTarget(GlobalVariables.main.resources.actors.get(GlobalVariables.camera_actor));
    }
    
    public static Renderer getInstance()
    {
        if(singleton == null)
            singleton = new RendererSimple();
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
        if(GlobalVariables.main.resources.getMainActor()==null)
            return;
        Actor a = GlobalVariables.main.resources.getMainActor();
        if(Config.show_debug)
        {


            g2.drawString(String.format("sleep: %d",GlobalVariables.SLEEP_TIME), 30, 30);
            g2.drawString(GlobalVariables.main.logic.getDebugString(), 30, 40);
            g2.drawString(String.format("x:%.2f y:%.2f",a.position.x,a.position.y ),30,50);
            g2.drawString(String.format("vx:%.2f vy:%.2f",a.velocity.x,a.velocity.y),30,60);
        
            if(GlobalVariables.main.controller instanceof ControllerGenetic)
            {
                ControllerGenetic cont = (ControllerGenetic) GlobalVariables.main.controller;
                g2.drawString(String.format("Chromosome: %d",cont.current_chromosome),30,80);
            }
            g2.drawString(GlobalVariables.main.gamestate.toString(), 30, 70);
        }

        camera.update();
        Camera.translateScreenNeg(g2, camera.getPosition());
        //g2.translate(-camera.getPosition().x, -camera.getPosition().y);
        
        Main m = GlobalVariables.main;
        g2.translate(m.getWidth()/2, m.getHeight()/2);
        if(!camera.follow && camera.tmp_drag!=null)
        {
            Camera.translateScreen(g2, camera.tmp_drag);
        }
//        drawGrid(g2, m);

        Iterator it;
        if(!GlobalVariables.resources_mutex)
        {
            GlobalVariables.resources_mutex=true;

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
            GlobalVariables.resources_mutex=false;
        }

        if(!camera.follow && camera.tmp_drag!=null)
        {
            Camera.translateScreenNeg(g2, camera.tmp_drag);
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
        camera.setTarget(GlobalVariables.main.resources.getMainActor());
    }

}
