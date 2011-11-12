/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.objects;
import java.awt.Point;
import mariogenetic.Global;
import mariogenetic.Vector;
import mariogenetic.game.GameState;
/**
 *
 * @author alice
 */
public class BonusKills extends Bonus {

    public BonusKills(Vector position, Point size,int value) {
        super(position,size,value);
     
        this.type = Bonus.TYPE_KILLS;

    }

    @Override
    public void tick()
    {

    }
    public void evalCollision()
    {
        super.evalCollision();
        Global.main.gamestate.result=GameState.RESULT_DEAD;
    }

}
