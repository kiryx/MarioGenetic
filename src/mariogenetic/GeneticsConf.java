/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic;

/**
 *
 * @author alice
 */
public class GeneticsConf {

    public static double right_probability = 0.85;
    public static double left_probability = 0.1;
    public static int getMove(Double d)
    {
        if(d>=right_probability+left_probability)
            return Global.MOVE_NONE;
        else if(d<right_probability+left_probability && d>=right_probability)
            return Global.MOVE_LEFT;
        else
            return Global.MOVE_RIGHT;
    }

}
