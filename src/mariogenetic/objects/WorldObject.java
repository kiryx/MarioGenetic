/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import mariogenetic.Vector;

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
}
