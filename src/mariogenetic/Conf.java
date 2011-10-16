/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author alice
 */
public class Conf extends JPanel{
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
        public static boolean show_coords = true;


        public static void main(String[] args) {
            JFrame frame = new JFrame("Settings");

            final Conf main = new Conf();
            frame.getContentPane().add(main);
            frame.setLayout(new BorderLayout());
            frame.pack();
            frame.setSize(Conf.window_main_size);
            frame.setVisible(true);

            JToolBar toolbar = new JToolBar("Tools", JToolBar.HORIZONTAL);

            final JButton btn_coords = new JButton("Coords:ON");
            btn_coords.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                String txt = "Coords:OFF";
                Conf.show_coords = !Conf.show_coords;
                if(Conf.show_coords==true)
                {
                    txt = "Coords:ON";
                }
                btn_coords.setText(txt);}
            });
            toolbar.setFloatable(false);
            toolbar.add(btn_coords);

            JPanel container = new JPanel();

            final JSlider sli_jmp = new JSlider(0, 1000);

            double sli_init = 200;
            final JLabel txt_jmp = new JLabel(String.format("Jump probability:%.2f", sli_init/sli_jmp.getMaximum()));
            sli_jmp.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                GeneticsConf.jump_probability = (double)sli_jmp.getValue();
                txt_jmp.setText(String.format("Jump probability:%.2f", GeneticsConf.jump_probability/sli_jmp.getMaximum()));
            }
            });
            
            sli_jmp.setValue((int)sli_init);
            container.add(txt_jmp);
            container.add(sli_jmp);

            frame.add(toolbar,BorderLayout.NORTH);
            frame.add(container,BorderLayout.CENTER);

        }

}
