/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.Rectangle;
import java.util.Iterator;
import mariogenetic.objects.Actor;
import mariogenetic.*;
import mariogenetic.objects.Bonus;
import mariogenetic.objects.Terrain;
import mariogenetic.objects.WorldObject;

/**
 *
 * @author alice
 */
public class LogicHuman_Temporary extends Logic{

    
    public LogicHuman_Temporary(){ }


    public void doLogic() {
        Main m = Global.frame_main;
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
                    if(!a.falling)
                    {
                        a.setFreefall();
                    }
                }
                else
                {
                    if((collision & COL_UP) != 0)
                    {
    //                    a.jumping=false;
                        a.velocity.y=0;
                        a.falling=true;

                    }
                    if((collision & COL_DOWN) != 0)
                    {
    //                    a.jumping=false;
                        a.falling=false;
                    }
                    else
                    {
                        a.falling=true;
                    }
                    a.position.y+=difference.y;
                }


            }
            //kolizja z bonusami
            {
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
    }
}
