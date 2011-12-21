/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;


import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import mariogenetic.main.Vector;
import mariogenetic.objects.WorldObject;

/**
 *
 * @author alice
 */
public class Camera {

    private Vector position;
    public Rectangle tmp_drag;
//    public Vector followBox;
    private WorldObject target;
    boolean follow = true;
//    private static Camera singleton;
    public Camera()
    {
        position = new Vector(0,0);
//        followBox = new Vector(100,100);
    }

//    public static Camera getInstance()
//    {
//        if(singleton==null)
//            singleton = new Camera();
//        return singleton;
//    }

     public static void translateDiff(Graphics2D g2, Rectangle box)
    {
        if(box!=null)
            g2.translate(box.width-box.x,box.height-box.y);
    }
    public static void translateDiff(Graphics2D g2, Point box)
    {
        if(box!=null)
            g2.translate(box.x,box.y);
    }
    public static void translateDiff(Graphics2D g2, Vector box)
    {
        if(box!=null)
            g2.translate(box.x,box.y);
    }
    public static void translateDiffNeg(Graphics2D g2, Point box)
    {
        if(box!=null)
            g2.translate(-box.x,-box.y);
    }
    public static void translateDiffNeg(Graphics2D g2, Rectangle box)
    {
        if(box!=null)
            g2.translate(box.x-box.width,box.y-box.height);

    }
    public static void translateDiffNeg(Graphics2D g2, Vector box)
    {
        if(box!=null)
            g2.translate(-box.x,-box.y);
    }
    public void setTarget(WorldObject o)
    {
        target = o;
    }
    public void setFollow(boolean f)
    {
        follow = f;
    }
    public void setPosition(Vector pos)
    {
        position = pos;
    }
    public Vector getPosition()
    {
        return position;
    }

    public void update()
    {        
        if(target!=null && follow)
        {            
            setPosition(new Vector(target.position));
        }        
       
    }
    public void reset()
    {
//        position = new Vector(0.0,0.0);
    }
    public String toString()
    {
        return String.format("X:%.2f Y:%.2f\n", position.x,position.y);


    }
}
