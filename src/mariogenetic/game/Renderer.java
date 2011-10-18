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

    public Camera camera;
    
    public Renderer(){camera = new Camera(new Vector(0.0,0.0));}
    
    void render() {}

    public void render(Graphics2D g2) { }

    void reset() {

    }

}
