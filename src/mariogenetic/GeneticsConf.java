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

    public static double move_probability = 0.9;
    public static double right_probability = 0.9;
//    public static double left_probability = 0.1;

    public static int population_size = 10;

    public static double jump_probability = 0.45;

    public static int getMove(Double d)
    {
        double real_right = right_probability*move_probability;
        double real_left = (1.0-right_probability)*move_probability;

        if(d>=real_right+real_left)
            return Global.MOVE_NONE;
        else if(d<real_right+real_left && d>=real_right)
            return Global.MOVE_LEFT;
        else
            return Global.MOVE_RIGHT;
    }
    public static int getJump(Double d)
    {
        if(d<=jump_probability)
            return Global.MOVE_JUMP;
        return Global.MOVE_NONE;
    }

}
