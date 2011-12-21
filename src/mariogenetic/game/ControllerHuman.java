/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import mariogenetic.main.GlobalVariables;

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
            case KeyEvent.VK_LEFT: {GlobalVariables.KEYS_MASK|=GlobalVariables.KEY_LEFT; break; }
            case KeyEvent.VK_RIGHT: {GlobalVariables.KEYS_MASK|=GlobalVariables.KEY_RIGHT; break;}
            case KeyEvent.VK_UP: {GlobalVariables.KEYS_MASK|=GlobalVariables.KEY_UP; break;}
            case KeyEvent.VK_DOWN: {GlobalVariables.KEYS_MASK|=GlobalVariables.KEY_DOWN; break;}
            case KeyEvent.VK_F: { GlobalVariables.KEYS_MASK|=GlobalVariables.KEY_A;break;}
            case KeyEvent.VK_D: { GlobalVariables.KEYS_MASK|=GlobalVariables.KEY_B;break;}
            case KeyEvent.VK_S: { GlobalVariables.KEYS_MASK|=GlobalVariables.KEY_C;break;}
            case KeyEvent.VK_A: { GlobalVariables.KEYS_MASK|=GlobalVariables.KEY_D;break;}
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT: {GlobalVariables.KEYS_MASK&=~GlobalVariables.KEY_LEFT; break; }
            case KeyEvent.VK_RIGHT: {GlobalVariables.KEYS_MASK&=~GlobalVariables.KEY_RIGHT; break;}
            case KeyEvent.VK_UP: {GlobalVariables.KEYS_MASK&=~GlobalVariables.KEY_UP; break;}
            case KeyEvent.VK_DOWN: {GlobalVariables.KEYS_MASK&=~GlobalVariables.KEY_DOWN; break;}
            case KeyEvent.VK_F: { GlobalVariables.KEYS_MASK&=~GlobalVariables.KEY_A;break;}
            case KeyEvent.VK_D: { GlobalVariables.KEYS_MASK&=~GlobalVariables.KEY_B;break;}
            case KeyEvent.VK_S: { GlobalVariables.KEYS_MASK&=~GlobalVariables.KEY_C;break;}
            case KeyEvent.VK_A: { GlobalVariables.KEYS_MASK&=~GlobalVariables.KEY_D;break;}
//            case KeyEvent.VK_DOWN: {m.resources.actors.get(0).stopY(); break;}
        }
    }
    public boolean down(int key)
    {
        return (GlobalVariables.KEYS_MASK&key)>0;
    }
    public void generateEvent()
    {
        GlobalVariables.Keys X = GlobalVariables.Keys.NONE;
        GlobalVariables.Keys Y = GlobalVariables.Keys.NONE;
        boolean UP = down(GlobalVariables.KEY_UP);
        boolean DOWN = down(GlobalVariables.KEY_DOWN);
        boolean LEFT = down(GlobalVariables.KEY_LEFT);
        boolean RIGHT = down(GlobalVariables.KEY_RIGHT);
        if(UP && !DOWN)
        {
            Y = GlobalVariables.Keys.UP;
        }
        else if(DOWN && !UP)
        {
            Y = GlobalVariables.Keys.DOWN;
        }
        else
        {
            Y = GlobalVariables.Keys.NONE;
        }

        if(LEFT && !RIGHT)
        {
            X = GlobalVariables.Keys.LEFT;
        }
        else if(!LEFT && RIGHT)
        {
            X = GlobalVariables.Keys.RIGHT;
        }
        else{
            X = GlobalVariables.Keys.NONE;
        }
        m.logic.executeMoveAction(GlobalVariables.Keys.NONE);
        if(X!=GlobalVariables.Keys.NONE)
        {
            m.logic.executeMoveAction(X);
        }
        if(Y!=GlobalVariables.Keys.NONE)
        {
            m.logic.executeMoveAction(Y);
        }

        if(down(GlobalVariables.KEY_A))
        {
            m.logic.executeSpecialAction(GlobalVariables.Keys.A);
            
        }
        if(down(GlobalVariables.KEY_B))
        {
            m.logic.executeSpecialAction(GlobalVariables.Keys.B);
        }
        if(down(GlobalVariables.KEY_C))
        {
            m.logic.executeSpecialAction(GlobalVariables.Keys.C);
        }
        if(down(GlobalVariables.KEY_D))
        {
            m.logic.executeSpecialAction(GlobalVariables.Keys.D);
        }
        
    }
}
