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
public class BonusWin extends Bonus{

    public BonusWin(Vector position, Point size,int value) {
        super(position,size,value);
       
        this.type = Bonus.TYPE_WINS;
        
    }

    @Override
    public void tick()
    {

    }
    public void evalCollision()
    {
        super.evalCollision();
        Global.main.gamestate.result = GameState.RESULT_WON;
    }

}
