/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.Color;
import java.awt.Graphics2D;
import mariogenetic.*;


/**
 *
 * @author alice
 */
public abstract class Renderer {

    protected Camera camera;
    protected static Renderer singleton;
    
    protected Renderer(){ }

    public void render(Graphics2D g2) { }

    public static Renderer getInstance()
    {
        return null;
    }
    public Camera getCamera()
    {
        return camera;
    }

    void reset() {

    }

}
