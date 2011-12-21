/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import mariogenetic.main.Config;
import mariogenetic.main.Vector;

/**
 *
 * @author alice
 */
public abstract class WorldObject {
    public Vector position;
    public Point size;
    Color c;
    public void paint(Graphics2D g2)
    {
        if(Config.show_coords)
            g2.drawString(String.format("%.1f %.1f", position.x,position.y), (int)position.x, (int)position.y);
    }
    public void tick()
    {
        
    }
    public WorldObject(Vector position, Point size)
    {
        this.position = position;
        this.size = size;
    }
    public Rectangle getRectangle()
    {
        return new Rectangle((int)position.x,(int)position.y,size.x,size.y);
    }
    public void evalCollision()
    {
        
    }
//    public boolean intersects(WorldObject o)
//    {
//        double this_right = this.position.x+this.size.x;
//        double o_right = o.position.x+o.size.x;
//
//        if(this_right >= o.position.x && position.x <= o_right)
//        {
//            double this_down = this.position.y+this.size.y;
//            double o_down = o.position.y+o.size.y;
//            if(position.y<=o_down && this_down >= o.position.y)
//            {
//                return true;
//            }
//        }
//        return false;
//    }
}
