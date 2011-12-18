/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game.logic;

import java.awt.Rectangle;
import java.util.Iterator;
import mariogenetic.Global;
import mariogenetic.Main;
import mariogenetic.Vector;
import mariogenetic.objects.Actor;
import mariogenetic.objects.Bonus;
import mariogenetic.objects.Terrain;

/**
 *
 * @author alice
 */
public class LogicMario extends Logic{

    private boolean actor_falling;
    public LogicMario(){actor_falling=true; }

    public void doLogic() {
        if(Global.shuffling_resources)
            return;
        Main m = Global.main;
        
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
                        if(actor_falling)
                        {
                            a.position.y+=a.velocity.y;
                            a.velocity.y+=0.05;
                        }
                        else
                        {
                            actor_falling = true;
                            a.velocity.y = 0.0;
                        }
                    }
                    else
                    {
                        if((collision & COL_UP) != 0)
                        {
        //                    a.jumping=false;
                            a.velocity.y=0;
                            actor_falling=true;

                        }
                        if((collision & COL_DOWN) != 0)
                        {
        //                    a.jumping=false;
                            actor_falling=false;
                        }
                        else
                        {
                            actor_falling=true;
                        }
                        a.position.y+=difference.y;
                    }


                }
                //kolizja z bonusami

               
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

    public void executeMoveAction(Global.Keys key){
    }
    public void executeSpecialAction(Global.Keys key){
        if(key==Global.Keys.A)
        {
            Actor a = Global.main.resources.actors.get(0);
            if(!actor_falling)
            {
                a.velocity.y = -3.0;
                actor_falling=true;
            }
        }
    }
}
