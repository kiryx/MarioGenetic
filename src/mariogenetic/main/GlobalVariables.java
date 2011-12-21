/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.main;

import mariogenetic.game.Main;
import mariogenetic.GUI.PopulationWindow;
import javax.swing.JFrame;
import mariogenetic.game.Controller;

/**
 *
 * @author alice
 */
public class GlobalVariables {
    
    public static Main main;
    public static PopulationWindow pop_frame;
    public static int KEYS_MASK;
    public static int KEY_UP=1;
    public static int KEY_DOWN=2;
    public static int KEY_LEFT=4;
    public static int KEY_RIGHT=8;
    public static int KEY_A=16;
    public static int KEY_B=32;
    public static int KEY_C=64;
    public static int KEY_D=128;
    


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
    public static int MODE_CURRENT = 1;
    public static int MODE_NEXT = 1;

    public static int camera_actor = 0;

    public static boolean shuffling_resources = false;
    public static int global_result_counter = 0;

    
}
