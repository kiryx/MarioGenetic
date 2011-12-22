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

    public static long SLEEP_TIME = 10;

    public static final int MODE_USER = 0;
    public static final int MODE_TIME = 1;    
    public static int MODE_CURRENT = 1;
    public static int MODE_NEXT = 1;

    public static int camera_actor = 0;

    public static boolean resources_mutex = false;
    public static int global_result_counter = 0;

    
}
