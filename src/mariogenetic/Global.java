/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic;

import mariogenetic.GUI.PopulationWindow;
import javax.swing.JFrame;
import mariogenetic.game.Controller;

/**
 *
 * @author alice
 */
public class Global {
    
    public static Main main;
    public static PopulationWindow pop_frame;
    public static boolean KEY_UP=false;
    public static boolean KEY_DOWN=false;
    public static boolean KEY_LEFT=false;
    public static boolean KEY_RIGHT=false;
    public static boolean KEY_DOUBLE_JUMP=false;


    public enum Keys
    {
        NONE,UP,DOWN,LEFT,RIGHT,A,B,C,D
    }
    public static Keys moveKeys[] = {Keys.UP, Keys.DOWN, Keys.LEFT, Keys.RIGHT, Keys.NONE};
    public static Keys specialKeys[] = {Keys.A, Keys.B, Keys.C, Keys.D, Keys.NONE};

//    public static int MOVE_NONE = 0;
//    public static int MOVE_JUMP = 1;
//    public static int MOVE_LEFT = 2;
//    public static int MOVE_RIGHT = 3;

    public static long SLEEP_TIME = 10;

    public static final int MODE_USER = 0;
    public static final int MODE_TIME = 1;
    public static final int MODE_POS = 2;
    public static int MODE_CURRENT = 1;
    public static int MODE_NEXT = 1;

    public static int camera_actor = 0;

    public static boolean shuffling_resources = false;
    public static int global_result_counter = 0;

    
}
