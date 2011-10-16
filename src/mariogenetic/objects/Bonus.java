/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import mariogenetic.Conf;
import mariogenetic.Global;
import mariogenetic.Vector;

/**
 *
 * @author alice
 */
public abstract class Bonus extends WorldObject{

    public static final int TYPE_VALUE=0;
    public static final int TYPE_WINS = 1;
    public static final int TYPE_KILLS = 2;
    public static final Color[] type_colors = {Conf.color_type_value, Conf.color_type_wins, Conf.color_type_kills};

    public int value;
    public int type;
    
    public Bonus(Vector position, Point size, int value) {
        super(position,size);
        this.value=value;
    }
    public void paint(Graphics2D g2)
    {
        super.paint(g2);
        g2.setColor(type_colors[type]);

        g2.fillRect((int)position.x, (int)position.y, size.x, size.y);
        g2.setColor(Color.black);
        g2.drawString(String.valueOf(value), (int)(position.x+size.x/2-4), (int)(position.y+size.y/2+3));
    }
    public void tick()
    {

    }
    public void evalCollision()
    {
        Global.frame_main.gamestate.score+=value;
    }

}
