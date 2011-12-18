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
            case KeyEvent.VK_LEFT: {Global.KEY_LEFT=true; break; }
            case KeyEvent.VK_RIGHT: {Global.KEY_RIGHT=true; break;}
            case KeyEvent.VK_UP: {Global.KEY_UP=true; break;}
            case KeyEvent.VK_R: { Global.main.gamestate.reset();break;}
            
//            case KeyEvent.VK_DOWN: {m.resources.actors.get(0).crouch(); break;}
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT: {Global.KEY_LEFT=false; break;}
            case KeyEvent.VK_RIGHT: {Global.KEY_RIGHT=false; break;}
            case KeyEvent.VK_UP: {Global.KEY_UP=false; break;}
//            case KeyEvent.VK_DOWN: {m.resources.actors.get(0).stopY(); break;}
        }
    }
    public void generateEvent()
    {
        if(Global.KEY_UP)
        {
            m.logic.executeSpecialAction(Global.Keys.A);
//            m.resources.actors.get(0).jump();
        }
//        else
//        {
//            m.resources.actors.get(0).allow_double();
//        }
        if(Global.KEY_LEFT)
        {
            if(Global.KEY_RIGHT)m.resources.actors.get(0).stopX();
            else
            m.resources.actors.get(0).left();
        }
        
        else if(Global.KEY_RIGHT)
        {
            m.resources.actors.get(0).right();
        }
        else
        {
            m.resources.actors.get(0).stopX();
        }
    }



    

}
