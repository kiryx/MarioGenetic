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
    private Vector position;
//    public Vector followBox;
    WorldObject following;
    public Camera(Vector initial_pos)
    {
        position = initial_pos;
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
    public void setPosition(Vector pos)
    {
        this.position = pos;
    }
    public Vector getPosition()
    {
        return position;
    }

    public void update()
    {
        
        if(following!=null)
        {
            position = following.position;
            
        }
       
    }
}
