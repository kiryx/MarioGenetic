/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;


import java.awt.Rectangle;
import mariogenetic.Vector;
import mariogenetic.objects.WorldObject;

/**
 *
 * @author alice
 */
public class Camera {
    public Vector pos;
//    public Vector followBox;
    WorldObject following;
    public Camera(Vector initial_pos)
    {
        pos = initial_pos;
//        followBox = new Vector(100,100);
    }
    public Camera(Vector initial_pos, WorldObject o)
    {
        this(initial_pos);
        following = o;
    }
    public void setFollow(WorldObject o)
    {
        following = o;
    }
    public void update()
    {
        pos = following.position;
       
    }
}
