/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import mariogenetic.main.Config;
import mariogenetic.main.GlobalVariables;
import mariogenetic.gene.ChromosomeTime;

/**
 *
 * @author alice
 */
public class GeneDetailsWindow extends JFrame{
    
    JTextArea details = new JTextArea();
    JPanel container = new JPanel();
    
    
    public static String[] strings = null;
    
    public String keyToString(GlobalVariables.Keys k)
    {
        return GeneDetailsWindow.getArray()[k.ordinal()];
    }
    public static String[] initArray()
    {
          String[] strings = new String[9];
        strings[GlobalVariables.Keys.A.ordinal()] = "A";
        strings[GlobalVariables.Keys.B.ordinal()] = "B";
        strings[GlobalVariables.Keys.C.ordinal()] = "C";
        strings[GlobalVariables.Keys.D.ordinal()] = "D";
        strings[GlobalVariables.Keys.NONE.ordinal()] = "N";
        strings[GlobalVariables.Keys.UP.ordinal()] = "U";
        strings[GlobalVariables.Keys.DOWN.ordinal()] = "D";
        strings[GlobalVariables.Keys.LEFT.ordinal()] = "L";
        strings[GlobalVariables.Keys.RIGHT.ordinal()] = "R";
        return strings;
    }
    public static String[] getArray()
    {
        if(strings==null)
        {
            strings = initArray();
        }
        return strings;
    }
    public GeneDetailsWindow(ChromosomeTime c)
    {
        this.setPreferredSize(Config.window_main_size);
        this.setSize(Config.window_main_size);
        this.setContentPane(container);

        String s = "";
        s+=c.toString()+"\n";


        String k = String.format("Chromosome array length: %d\nMoves genotype:\n",c.arr_length);
        for(GlobalVariables.Keys key : c.moves)
            k+=keyToString(key)+" ";
        
        s+=k+"\n";
        k = "Special genotype:\n";
        for(GlobalVariables.Keys key : c.special)
            k+=keyToString(key)+" ";
        s+=k+"\n";

        details.setText(s);
        

        container.add(new JScrollPane(details));
        
        details.setWrapStyleWord(true);
        details.setLineWrap(true);
        details.setEditable(false);
        Dimension d = this.getSize();
        details.setSize(new Dimension(d.width-15, d.height-50));
        details.setPreferredSize(new Dimension(d.width-15, d.height-50));
        
        repaint();
        this.setVisible(true);
    }
    public void paintComponents(Graphics g)
    {
        super.paintComponents(g);
        Dimension d = this.getSize();
        details.setSize(new Dimension(d.width-15, d.height-50));
        details.setPreferredSize(new Dimension(d.width-15, d.height-50));


    }

}
