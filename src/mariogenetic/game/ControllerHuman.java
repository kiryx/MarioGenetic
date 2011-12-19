/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import mariogenetic.Global;

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
            case KeyEvent.VK_LEFT: {Global.KEYS_MASK|=Global.KEY_LEFT; break; }
            case KeyEvent.VK_RIGHT: {Global.KEYS_MASK|=Global.KEY_RIGHT; break;}
            case KeyEvent.VK_UP: {Global.KEYS_MASK|=Global.KEY_UP; break;}
            case KeyEvent.VK_DOWN: {Global.KEYS_MASK|=Global.KEY_DOWN; break;}
            case KeyEvent.VK_F: { Global.KEYS_MASK|=Global.KEY_A;break;}
            case KeyEvent.VK_D: { Global.KEYS_MASK|=Global.KEY_B;break;}
            case KeyEvent.VK_S: { Global.KEYS_MASK|=Global.KEY_C;break;}
            case KeyEvent.VK_A: { Global.KEYS_MASK|=Global.KEY_D;break;}
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT: {Global.KEYS_MASK&=~Global.KEY_LEFT; break; }
            case KeyEvent.VK_RIGHT: {Global.KEYS_MASK&=~Global.KEY_RIGHT; break;}
            case KeyEvent.VK_UP: {Global.KEYS_MASK&=~Global.KEY_UP; break;}
            case KeyEvent.VK_DOWN: {Global.KEYS_MASK&=~Global.KEY_DOWN; break;}
            case KeyEvent.VK_F: { Global.KEYS_MASK&=~Global.KEY_A;break;}
            case KeyEvent.VK_D: { Global.KEYS_MASK&=~Global.KEY_B;break;}
            case KeyEvent.VK_S: { Global.KEYS_MASK&=~Global.KEY_C;break;}
            case KeyEvent.VK_A: { Global.KEYS_MASK&=~Global.KEY_D;break;}
//            case KeyEvent.VK_DOWN: {m.resources.actors.get(0).stopY(); break;}
        }
    }
    public boolean down(int key)
    {
        return (Global.KEYS_MASK&key)>0;
    }
    public void generateEvent()
    {
        Global.Keys X = Global.Keys.NONE;
        Global.Keys Y = Global.Keys.NONE;
        boolean UP = down(Global.KEY_UP);
        boolean DOWN = down(Global.KEY_DOWN);
        boolean LEFT = down(Global.KEY_LEFT);
        boolean RIGHT = down(Global.KEY_RIGHT);
        if(UP && !DOWN)
        {
            Y = Global.Keys.UP;
        }
        else if(DOWN && !UP)
        {
            Y = Global.Keys.DOWN;
        }
        else
        {
            Y = Global.Keys.NONE;
        }

        if(LEFT && !RIGHT)
        {
            X = Global.Keys.LEFT;
        }
        else if(!LEFT && RIGHT)
        {
            X = Global.Keys.RIGHT;
        }
        else{
            X = Global.Keys.NONE;
        }
        m.logic.executeMoveAction(Global.Keys.NONE);
        if(X!=Global.Keys.NONE)
        {
            m.logic.executeMoveAction(X);
        }
        if(Y!=Global.Keys.NONE)
        {
            m.logic.executeMoveAction(Y);
        }

        if(down(Global.KEY_A))
        {
            m.logic.executeSpecialAction(Global.Keys.A);
            
        }
        if(down(Global.KEY_B))
        {
            m.logic.executeSpecialAction(Global.Keys.B);
        }
        if(down(Global.KEY_C))
        {
            m.logic.executeSpecialAction(Global.Keys.C);
        }
        if(down(Global.KEY_D))
        {
            m.logic.executeSpecialAction(Global.Keys.D);
        }
        
    }
}
