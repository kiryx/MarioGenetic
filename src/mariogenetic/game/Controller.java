/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import mariogenetic.main.GlobalVariables;
import mariogenetic.main.Vector;

/**
 *
 * @author alice
 */
public abstract class Controller implements KeyListener,MouseMotionListener,MouseListener{

    Main m;
    public static int KEYS_MASK;
    public static int KEY_UP=1;
    public static int KEY_DOWN=2;
    public static int KEY_LEFT=4;
    public static int KEY_RIGHT=8;
    public static int KEY_A=16;
    public static int KEY_B=32;
    public static int KEY_C=64;
    public static int KEY_D=128;
    public Controller()
    {
        m = GlobalVariables.main;
    }
    
    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) {
          switch(e.getKeyCode())
        {
            case KeyEvent.VK_I: {GlobalVariables.SLEEP_TIME++; break; }
            case KeyEvent.VK_K: {
                if (GlobalVariables.SLEEP_TIME > 0){
                    --GlobalVariables.SLEEP_TIME;
                }
                break;
            }
            //TODO Wyłączyć tryb speed jeśli prezłączamy sie na USERA
            case KeyEvent.VK_H: {GlobalVariables.MODE_NEXT=GlobalVariables.MODE_USER; break;}
            case KeyEvent.VK_T: {GlobalVariables.MODE_NEXT=GlobalVariables.MODE_TIME; break;}


//            case KeyEvent.VK_DOWN: {m.resources.actors.get(0).crouch(); break;}
        }}

    public void keyReleased(KeyEvent e) { }

    public void mouseDragged(MouseEvent e) {
        
        if((e.getModifiers() | e.BUTTON3_DOWN_MASK) > 0)
        {            
            if(!m.renderer.camera.follow && m.renderer.camera.tmp_drag!=null)
            {
                m.renderer.camera.tmp_drag.width=e.getX();
                m.renderer.camera.tmp_drag.height=e.getY();
              
//                if(screenDrag_tmp!=null)
//                {
//                    screenDrag_tmp.width=e.getX();
//                    screenDrag_tmp.height=e.getY();
//                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {


        if((e.getModifiers() | e.BUTTON3_DOWN_MASK) > 0)
        {
            if(!m.renderer.camera.follow)
            {
                m.renderer.camera.tmp_drag = new Rectangle(e.getX(),e.getY(),e.getX(),e.getY());
//            screenDrag_tmp =
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        
        if((e.getModifiers() | e.BUTTON3_DOWN_MASK) > 0)
        {

            if(!m.renderer.camera.follow)
            {
                
                m.renderer.camera.setPosition(new Vector(
                        m.renderer.camera.getPosition().x-(m.renderer.camera.tmp_drag.width-m.renderer.camera.tmp_drag.x),
                        m.renderer.camera.getPosition().y-(m.renderer.camera.tmp_drag.height-m.renderer.camera.tmp_drag.y)
                        ));
                m.renderer.camera.tmp_drag=null;
            }
//            screenDrag=getScreenDragTotal();
//            screenDrag_tmp=null;

        }
    }


    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }


    public void generateEvent() {
        
    }
    public void resetPopulation()
    {
        
    }

}
