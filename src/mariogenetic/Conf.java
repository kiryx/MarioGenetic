/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JButton;

/**
 *
 * @author alice
 */
public class Conf {
        public static final Dimension window_main_size = new Dimension(500,500);
        public static final Dimension window_editor_size = new Dimension(700,500);
	public static final int TILE_SIZE = 30;
        public static final Color color_grid_light = new Color(210,210,210);
        public static final Color color_grid_dark = new Color(170,170,170);

        public static final Color color_actor = Color.blue;
        public static final Color color_terrain = Color.darkGray;
        public static final Color color_type_value = Color.orange;
        public static final Color color_type_wins = Color.green;
        public static final Color color_type_kills = Color.red;

        public static final Color color_map_selected = Color.GREEN;
        public static final Color color_map_not_selected = (new JButton()).getBackground();
        public static final Color color_map_object_selected = Color.MAGENTA;



}
