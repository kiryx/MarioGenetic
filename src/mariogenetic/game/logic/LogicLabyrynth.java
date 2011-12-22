/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game.logic;

import java.awt.Rectangle;
import java.util.Iterator;
import mariogenetic.game.Main;
import mariogenetic.gene.GeneticsConfig;
import mariogenetic.main.GlobalVariables;
import mariogenetic.main.Vector;
import mariogenetic.objects.Actor;
import mariogenetic.objects.Bonus;
import mariogenetic.objects.Terrain;

/**
 *
 * @author alice
 */
public class LogicLabyrynth extends Logic{

    
    double velocity_X = 1.5;
    double velocity_Y = 1.5;
    public LogicLabyrynth(){ }

    public void doLogic() {
        if(GlobalVariables.resources_mutex)
            return;
        Main m = GlobalVariables.main;
        
            for(Actor a : m.resources.actors )
            {
                //Sprawdzanie kolizji ze scianami
                Rectangle nowR = a.getRectangle();
                {
                    int collision = 0;
                    Rectangle nextX = a.previewX();
                    Rectangle nextY = a.previewY();
                    Vector difference = new Vector(10.0,10.0);

                    for(Terrain t : m.resources.terrain)
                    {
                        Rectangle tR = t.getRectangle();
                        if(nextX.intersects(tR))
                        {
                            collision|=COL_X;
                            if(nowR.x<tR.x)
                            {
                                collision|=COL_RIGHT;
                                difference.x=t.position.x -(a.position.x+a.size.x);
                            }
                            if(nowR.x>tR.x)
                            {
                                collision|=COL_LEFT;
                                difference.x = -(a.position.x - (t.position.x+t.size.x));
                            }
                        }
                        if(nextY.intersects(tR))
                        {
                            collision|=COL_Y;
        //                    System.out.println("Y");
        //                    if(a.position.y>=(t.position.y+t.size.y) && nextY.y<(tR.y+tR.height))
                            if(nowR.y>=tR.y)
                            {
                                collision|=COL_UP;
                                difference.y=a.position.y -(t.position.y+t.size.y);
        //                        System.out.println("UP");
                            }
        //                    if((a.position.y+a.size.y)<=(t.position.y) && (nextY.y+a.size.y)>=(tR.y))
                            else
                            {
                                collision|=COL_DOWN;
                                difference.y=t.position.y -(a.position.y+a.size.y);
        //                        System.out.println("DOWN");
                            }
                        }
                    }
                    

                    if((collision & COL_X)==0)
                    {
                        a.tickX();
                    }
                    else
                    {
                        a.position.x+=difference.x;
                    }

                    if((collision & COL_Y)==0)
                    {
                        a.tickY();                       
                    }
                    else
                    {                       
                        a.position.y+=difference.y;
                    }


                }
                
           
                    Iterator it = m.resources.bonus.iterator();
                    while(it.hasNext())
                    {
                        Bonus b = (Bonus)it.next();
                        if(nowR.intersects(b.getRectangle()))
                        {
                            b.evalCollision();
                            it.remove();
                        }
                    }
                

            }
        
    }

    public void executeMoveAction(GeneticsConfig.Keys key){

        Actor a = GlobalVariables.main.resources.getMainActor();
        if(a==null)
            return;
        if(key==GeneticsConfig.Keys.LEFT)
        {
            a.left(velocity_X);
        }
        else if(key==GeneticsConfig.Keys.RIGHT)
        {
            a.right(velocity_X);            
        }
        else if(key==GeneticsConfig.Keys.UP)
        {
            a.up(velocity_Y);
        }
        else if(key==GeneticsConfig.Keys.DOWN)
        {
            a.down(velocity_Y);
        }
        else if(key==GeneticsConfig.Keys.NONE)
        {
            a.stopX();
            a.stopY();
        }
    }
    public String getDebugString(){ return String.format("");}
    public void executeSpecialAction(GeneticsConfig.Keys key){
        Actor a = GlobalVariables.main.resources.getMainActor();
        if(a==null)return;
        if(key==GeneticsConfig.Keys.A)
        {            
            
        }
    }
}
