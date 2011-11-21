/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.objects;

import java.awt.Graphics2D;
import java.awt.Point;
import mariogenetic.Config;
import mariogenetic.Vector;

/**
 *
 * @author alice
 */
public class Terrain extends WorldObject{
    

    public Terrain(Vector position, Point size) {
        super(position,size);
    }
    public void paint(Graphics2D g2)
    {
        super.paint(g2);
        g2.setColor(Config.color_terrain);
        g2.fillRect((int)position.x, (int)position.y, size.x, size.y);
    }
    public void tick()
    {

    }

}
