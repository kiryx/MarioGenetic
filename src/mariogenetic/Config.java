/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic;

import mariogenetic.gene.GeneticsConfig;
import mariogenetic.GUI.LabeledTextBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
public class Config extends JPanel{
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

            final Config conf_main = new Config();
            frame.getContentPane().add(conf_main);
            frame.setLayout(new BorderLayout());
            frame.pack();
            frame.setSize(Config.window_main_size);
            frame.setVisible(true);

            JToolBar toolbar = new JToolBar("Tools", JToolBar.HORIZONTAL);

            final JButton btn_coords = new JButton("Coords:ON");
            btn_coords.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                String txt = "Coords:OFF";
                Config.show_coords = !Config.show_coords;
                if(Config.show_coords==true)
                {
                    txt = "Coords:ON";
                }
                btn_coords.setText(txt);}
            });
            toolbar.setFloatable(false);
            toolbar.add(btn_coords);

            final LabeledTextBox lbltxt_crossing_param = new LabeledTextBox("Crossing constant",String.valueOf(GeneticsConfig.getInstance().crossing_parameter));
            
            lbltxt_crossing_param.getJLabel().setPreferredSize(new Dimension(150,20));
            
            lbltxt_crossing_param.getText().setPreferredSize(new Dimension(50,20));
//            toolbar.add(lbltxt_crossing_param);


            JPanel container = new JPanel();

//            final FloatSlider sli_jmp = new FloatSlider(1.0);
//            sli_jmp.setValue(GeneticsConf.jump_probability);
//            final JLabel txt_jmp = new JLabel(String.format("Jump probability:%.2f", sli_jmp.getFloatValue()));
//
//            final FloatSlider sli_move = new FloatSlider(1.0);
//            sli_move.setValue(GeneticsConf.move_probability);
//            final JLabel txt_move = new JLabel(String.format("Move probability:%.2f", sli_move.getFloatValue()));
//
//            final FloatSlider sli_lr = new FloatSlider(1.0);
//            sli_lr.setValue(GeneticsConf.right_probability);
//            final JLabel txt_lr = new JLabel(String.format("Right probability:%.2f (left:%.2f)", sli_lr.getFloatValue(),1-sli_lr.getFloatValue()));
//
//            sli_jmp.addChangeListener(new ChangeListener() {
//            public void stateChanged(ChangeEvent e) {
//                GeneticsConf.jump_probability = sli_jmp.getFloatValue();
//                txt_jmp.setText(String.format("Jump probability:%.2f", GeneticsConf.jump_probability));
//            }
//            });
//
//            sli_move.addChangeListener(new ChangeListener() {
//            public void stateChanged(ChangeEvent e) {
//                GeneticsConf.move_probability = sli_move.getFloatValue();
//                txt_move.setText(String.format("Move probability:%.2f", GeneticsConf.move_probability));
//            }
//            });
//
//            sli_lr.addChangeListener(new ChangeListener() {
//            public void stateChanged(ChangeEvent e) {
//                GeneticsConf.right_probability = sli_lr.getFloatValue();
////                GeneticsConf.left_probability = 1-sli_lr.getFloatValue();
//                txt_lr.setText(String.format("Right probability:%.2f (left:%.2f)", GeneticsConf.right_probability,1-GeneticsConf.right_probability));
//            }
//            });

            container.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
//            container.add(txt_jmp);
//            container.add(sli_jmp);
//
//            container.add(txt_move);
//            container.add(sli_move);
//
//            container.add(txt_lr);
//            container.add(sli_lr);

            GeneticsConfig gc = GeneticsConfig.getInstance();
            final LabeledTextBox[] lbtx = {
                new LabeledTextBox(Global.Keys.UP, String.valueOf(gc.getMoveKeyProbability(Global.Keys.UP))),
                new LabeledTextBox(Global.Keys.DOWN, String.valueOf(gc.getMoveKeyProbability(Global.Keys.DOWN))),
                new LabeledTextBox(Global.Keys.LEFT, String.valueOf(gc.getMoveKeyProbability(Global.Keys.LEFT))),
                new LabeledTextBox(Global.Keys.RIGHT, String.valueOf(gc.getMoveKeyProbability(Global.Keys.RIGHT))),
                new LabeledTextBox(Global.Keys.NONE, String.valueOf(gc.getMoveKeyProbability(Global.Keys.NONE))),
                new LabeledTextBox(Global.Keys.A, String.valueOf(gc.getSpecialKeyProbability(Global.Keys.A))),
                new LabeledTextBox(Global.Keys.B, String.valueOf(gc.getSpecialKeyProbability(Global.Keys.B))),
                new LabeledTextBox(Global.Keys.C, String.valueOf(gc.getSpecialKeyProbability(Global.Keys.C))),
                new LabeledTextBox(Global.Keys.D, String.valueOf(gc.getSpecialKeyProbability(Global.Keys.D))),
                new LabeledTextBox(Global.Keys.NONE, String.valueOf(gc.getSpecialKeyProbability(Global.Keys.NONE)))
            };

            JButton btn_ok = new JButton("Apply");
            JPanel move_group = new JPanel();
            move_group.setBorder(BorderFactory.createTitledBorder("Movement key probabilities"));
            container.add(move_group);
            move_group.setLayout(new BoxLayout(move_group,BoxLayout.Y_AXIS));

            JPanel special_group = new JPanel();
            special_group.setBorder(BorderFactory.createTitledBorder("Special key probabilities"));
            container.add(special_group);
            special_group.setLayout(new BoxLayout(special_group,BoxLayout.Y_AXIS));

            JPanel genetic_group = new JPanel();
            genetic_group.setBorder(BorderFactory.createTitledBorder("Genetic Settings"));
            container.add(genetic_group);
            genetic_group.setLayout(new BoxLayout(genetic_group,BoxLayout.Y_AXIS));

            genetic_group.add(lbltxt_crossing_param);


            for (int i = 0; i < 5; i++) {
                move_group.add(lbtx[i]);
            }
            for (int i = 5; i < lbtx.length; i++) {
                special_group.add(lbtx[i]);
            }
            container.add(btn_ok);

            btn_ok.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < 5; i++) {
                        GeneticsConfig.getInstance().setMoveKeyProbability(lbtx[i].getKey(), Double.valueOf(lbtx[i].getValue()));
                    }
                    for (int i = 5; i < lbtx.length; i++) {
                        GeneticsConfig.getInstance().setSpecialKeyProbability(lbtx[i].getKey(), Double.valueOf(lbtx[i].getValue()));
                    }
                    GeneticsConfig.getInstance().setCrossing_parameter(Double.valueOf(lbltxt_crossing_param.getValue()));
                    conf_main.repaint();
                }
            });

            frame.add(toolbar,BorderLayout.NORTH);
            frame.add(container,BorderLayout.CENTER);

        }

}
