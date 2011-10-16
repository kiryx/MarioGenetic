/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import mariogenetic.Global;
import mariogenetic.Vector;

/**
 *
 * @author alice
 */
public class BonusCoin extends Bonus {

    public BonusCoin(Vector position, Point size, int value) {
        super(position,size,value);
        this.type = Bonus.TYPE_VALUE;
    }

    @Override
    public void tick()
    {

    }
    public void evalCollision()
    {
        super.evalCollision();
    }


}
