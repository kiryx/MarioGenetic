/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import mariogenetic.Global;
import mariogenetic.Main;
import mariogenetic.Vector;

/**
 *
 * @author alice
 */
public abstract class Controller implements KeyListener,MouseMotionListener,MouseListener{

    Main m;
    public Controller()
    {
        m = Global.main;
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

    public void mouseDragged(MouseEvent e) {
        if(e.getButton()==e.BUTTON3)
        {
            if(!m.renderer.camera.follow && m.renderer.camera.tmp_drag!=null)
            {
                m.renderer.camera.tmp_drag.width=e.getX();
                m.renderer.camera.tmp_drag.height=e.getY();
                System.out.println("hi");
//                if(screenDrag_tmp!=null)
//                {
//                    screenDrag_tmp.width=e.getX();
//                    screenDrag_tmp.height=e.getY();
//                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {

        //TODO sie nie wypisuje to nizej
        System.out.println("hi");
        if(e.getButton() == e.BUTTON3)
        {
            if(!m.renderer.camera.follow)
            {
                m.renderer.camera.tmp_drag = new Rectangle(e.getX(),e.getY(),e.getX(),e.getY());
//            screenDrag_tmp =
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == e.BUTTON3)
        {
            if(!m.renderer.camera.follow)
            {
                m.renderer.camera.setPosition(new Vector(
                        m.renderer.camera.getPosition().x+(m.renderer.camera.tmp_drag.width-m.renderer.camera.tmp_drag.x),
                        m.renderer.camera.getPosition().y+(m.renderer.camera.tmp_drag.height-m.renderer.camera.tmp_drag.y)
                        ));
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
