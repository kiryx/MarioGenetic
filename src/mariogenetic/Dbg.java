/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic;

import java.util.HashMap;

/**
 *
 * @author alice
 */
public class Dbg {
    public static boolean DEBUG = true;    

    public static void o(Object s)
    {
        if(DEBUG)
            System.out.println(s.toString());
    }

}
