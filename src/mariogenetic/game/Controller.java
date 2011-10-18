/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import mariogenetic.Global;
import mariogenetic.Main;

/**
 *
 * @author alice
 */
public abstract class Controller implements KeyListener{

    Main m;
    public Controller()
    {
        m = Global.frame_main;
    }    

    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) {
          switch(e.getKeyCode())
        {
            case KeyEvent.VK_I: {Global.SLEEP_TIME++; break; }
            case KeyEvent.VK_K: {
                if (Global.SLEEP_TIME > 0){
                    --Global.SLEEP_TIME;
                }
                break;
            }
            case KeyEvent.VK_H: {Global.MODE_NEXT=Global.MODE_USER; break;}
            case KeyEvent.VK_T: {Global.MODE_NEXT=Global.MODE_TIME; break;}


//            case KeyEvent.VK_DOWN: {m.resources.actors.get(0).crouch(); break;}
        }}

    public void keyReleased(KeyEvent e) { }

    public void generateEvent() {
        
    }
    public void resetPopulation()
    {
        
    }

}
