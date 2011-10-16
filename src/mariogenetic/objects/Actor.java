/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import mariogenetic.Conf;
import mariogenetic.Global;
import mariogenetic.Vector;

/**
 *
 * @author alice
 */
public class Actor extends WorldObject{
	
    public boolean falling;
//    public boolean jumping;

    public Vector velocity;
    public Vector previous_pos;
    private boolean can_double_jump;
    
    public Actor(Vector position, Point size)
    {
        super(position,size);
        falling = true;
//        jumping = false;        
        previous_pos = position;
        velocity = new Vector(0.0,0.0);       
    }
    public void paint(Graphics2D g2)
    {
        super.paint(g2);
        Color c = g2.getColor();
        g2.setColor(Conf.color_actor);
        g2.fillRect((int)position.x, (int)position.y, size.x, size.y);
        g2.setColor(c);
    }
    public Rectangle previewX()
    {
        return new Rectangle((int)(position.x+velocity.x),(int)position.y,size.x,size.y);
    }
    public Rectangle previewY()
    {
        return new Rectangle((int)(position.x),(int)(position.y+velocity.y),size.x,size.y);
    }
    public void tickX()
    {
        previous_pos.x=position.x;
        position.x+=velocity.x;
    }
    public void tickY()
    {
        previous_pos.y = position.y;
        
        if(falling)
        {
            position.y+=velocity.y;
            velocity.y+=0.05;
        }
    }
    public void left()
    {
        velocity.x = -1.5;
    }
    public void right()
    {
        velocity.x = 1.5;
    }
    public void jump()
    {
        if(falling)
        {
//            if(!jumping && can_double_jump)
//            {
//                velocity.y = -3.0;
//                jumping=true;
//                can_double_jump=false;
//            }
        }
        else
        {
            velocity.y = -3.0;
            falling=true;
        }
        
    }
    public void setFreefall()
    {
        falling = true;
        velocity.y = 0.0;
    }

    public void crouch()
    {
      return;
        //velocity.y = 0.5;
        
    }
    public void stopX()
    {
        velocity.x=0;
    }
    public void stopY()
    {
        velocity.y=0;
    }

}
