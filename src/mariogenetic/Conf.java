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
        public static boolean mouse_cam = false;


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

            final FloatSlider sli_jmp = new FloatSlider(1.0); 
            sli_jmp.setValue(GeneticsConf.jump_probability);
            final JLabel txt_jmp = new JLabel(String.format("Jump probability:%.2f", sli_jmp.getFloatValue()));
            
            final FloatSlider sli_move = new FloatSlider(1.0); 
            sli_move.setValue(GeneticsConf.move_probability);
            final JLabel txt_move = new JLabel(String.format("Move probability:%.2f", sli_move.getFloatValue()));
            
            final FloatSlider sli_lr = new FloatSlider(1.0); 
            sli_lr.setValue(GeneticsConf.right_probability);
            final JLabel txt_lr = new JLabel(String.format("Right probability:%.2f (left:%.2f)", sli_lr.getFloatValue(),1-sli_lr.getFloatValue()));

            sli_jmp.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                GeneticsConf.jump_probability = sli_jmp.getFloatValue();
                txt_jmp.setText(String.format("Jump probability:%.2f", GeneticsConf.jump_probability));
            }
            });

            sli_move.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                GeneticsConf.move_probability = sli_move.getFloatValue();
                txt_move.setText(String.format("Move probability:%.2f", GeneticsConf.move_probability));
            }
            });

            sli_lr.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                GeneticsConf.right_probability = sli_lr.getFloatValue();
//                GeneticsConf.left_probability = 1-sli_lr.getFloatValue();
                txt_lr.setText(String.format("Right probability:%.2f (left:%.2f)", GeneticsConf.right_probability,1-GeneticsConf.right_probability));
            }
            });
                        
            container.add(txt_jmp);
            container.add(sli_jmp);

            container.add(txt_move);
            container.add(sli_move);

            container.add(txt_lr);
            container.add(sli_lr);

            frame.add(toolbar,BorderLayout.NORTH);
            frame.add(container,BorderLayout.CENTER);

        }

}
