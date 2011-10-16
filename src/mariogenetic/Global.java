/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic;

import javax.swing.JFrame;
import mariogenetic.game.Controller;

/**
 *
 * @author alice
 */
public class Global {
    
    public static Main frame_main;
    public static boolean KEY_UP=false;
    public static boolean KEY_DOWN=false;
    public static boolean KEY_LEFT=false;
    public static boolean KEY_RIGHT=false;
    public static boolean KEY_DOUBLE_JUMP=false;

    public static int MOVE_NONE = 0;
    public static int MOVE_JUMP = 1;
    public static int MOVE_LEFT = 2;
    public static int MOVE_RIGHT = 3;

    public static long SLEEP_TIME = 10;

    public static final int MODE_USER = 0;
    public static final int MODE_TIME = 1;
    public static final int MODE_POS = 2;
    public static int MODE_CURRENT = 1;
    public static int MODE_NEXT = 1;

    public static int camera_actor = 0;

    public static int population_size = 10;
}
