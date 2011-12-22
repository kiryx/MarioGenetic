/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.event.KeyEvent;
import mariogenetic.gene.GeneticsConfig;
import mariogenetic.gene.GeneticsConfig.Keys;


/**
 *
 * @author alice
 */
public class ControllerHuman extends Controller{

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT: {Controller.KEYS_MASK|=Controller.KEY_LEFT; break; }
            case KeyEvent.VK_RIGHT: {Controller.KEYS_MASK|=Controller.KEY_RIGHT; break;}
            case KeyEvent.VK_UP: {Controller.KEYS_MASK|=Controller.KEY_UP; break;}
            case KeyEvent.VK_DOWN: {Controller.KEYS_MASK|=Controller.KEY_DOWN; break;}
            case KeyEvent.VK_F: { Controller.KEYS_MASK|=Controller.KEY_A;break;}
            case KeyEvent.VK_D: { Controller.KEYS_MASK|=Controller.KEY_B;break;}
            case KeyEvent.VK_S: { Controller.KEYS_MASK|=Controller.KEY_C;break;}
            case KeyEvent.VK_A: { Controller.KEYS_MASK|=Controller.KEY_D;break;}
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT: {Controller.KEYS_MASK&=~Controller.KEY_LEFT; break; }
            case KeyEvent.VK_RIGHT: {Controller.KEYS_MASK&=~Controller.KEY_RIGHT; break;}
            case KeyEvent.VK_UP: {Controller.KEYS_MASK&=~Controller.KEY_UP; break;}
            case KeyEvent.VK_DOWN: {Controller.KEYS_MASK&=~Controller.KEY_DOWN; break;}
            case KeyEvent.VK_F: { Controller.KEYS_MASK&=~Controller.KEY_A;break;}
            case KeyEvent.VK_D: { Controller.KEYS_MASK&=~Controller.KEY_B;break;}
            case KeyEvent.VK_S: { Controller.KEYS_MASK&=~Controller.KEY_C;break;}
            case KeyEvent.VK_A: { Controller.KEYS_MASK&=~Controller.KEY_D;break;}
//            case KeyEvent.VK_DOWN: {m.resources.actors.get(0).stopY(); break;}
        }
    }
    public boolean down(int key)
    {
        return (Controller.KEYS_MASK&key)>0;
    }
    public void generateEvent()
    {
        GeneticsConfig.Keys X = GeneticsConfig.Keys.NONE;
        GeneticsConfig.Keys Y = GeneticsConfig.Keys.NONE;
        boolean UP = down(Controller.KEY_UP);
        boolean DOWN = down(Controller.KEY_DOWN);
        boolean LEFT = down(Controller.KEY_LEFT);
        boolean RIGHT = down(Controller.KEY_RIGHT);
        if(UP && !DOWN)
        {
            Y = GeneticsConfig.Keys.UP;
        }
        else if(DOWN && !UP)
        {
            Y = GeneticsConfig.Keys.DOWN;
        }
        else
        {
            Y = GeneticsConfig.Keys.NONE;
        }

        if(LEFT && !RIGHT)
        {
            X = GeneticsConfig.Keys.LEFT;
        }
        else if(!LEFT && RIGHT)
        {
            X = GeneticsConfig.Keys.RIGHT;
        }
        else{
            X = GeneticsConfig.Keys.NONE;
        }
        m.logic.executeMoveAction(GeneticsConfig.Keys.NONE);
        if(X!=GeneticsConfig.Keys.NONE)
        {
            m.logic.executeMoveAction(X);
        }
        if(Y!=GeneticsConfig.Keys.NONE)
        {
            m.logic.executeMoveAction(Y);
        }

        if(down(Controller.KEY_A))
        {
            m.logic.executeSpecialAction(GeneticsConfig.Keys.A);
            
        }
        if(down(Controller.KEY_B))
        {
            m.logic.executeSpecialAction(GeneticsConfig.Keys.B);
        }
        if(down(Controller.KEY_C))
        {
            m.logic.executeSpecialAction(GeneticsConfig.Keys.C);
        }
        if(down(Controller.KEY_D))
        {
            m.logic.executeSpecialAction(GeneticsConfig.Keys.D);
        }
        
    }
}
