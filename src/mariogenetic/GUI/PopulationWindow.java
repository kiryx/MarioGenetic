/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import mariogenetic.main.Config;
import mariogenetic.main.GlobalVariables;
import mariogenetic.game.ControllerTime;
import mariogenetic.gene.Chromosome;
import mariogenetic.gene.ChromosomeTime;

/**
 *
 * @author alice
 */
public class PopulationWindow extends JFrame{

    JList list = new JList();
    JButton run_gene = new JButton("Run selected gene");
    JButton details_gene = new JButton("Show details");
    JPanel container = new JPanel();
    private static PopulationWindow singleton;
    private PopulationWindow()
    {
        super("Population");
        this.setSize(new Dimension(Config.window_main_size));

        this.getContentPane().add(container);

        container.setLayout(new BorderLayout());
        container.add(list,BorderLayout.CENTER);
        
        JToolBar toolbar = new JToolBar("Tools", JToolBar.HORIZONTAL);
        toolbar.setFocusable(false);
        toolbar.add(run_gene);
        toolbar.add(details_gene);
        this.add(toolbar,BorderLayout.SOUTH);

        run_gene.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                
                if(list.getSelectedIndex()!=-1)
                {
                    if(GlobalVariables.main.controller instanceof ControllerTime)
                    {
                        ControllerTime ct = (ControllerTime)GlobalVariables.main.controller;
                        if(ct.getPopulation().chromosomes.get(list.getSelectedIndex()).resultData!=null)
                        {
                            ct.current_chromosome = list.getSelectedIndex();
                            GlobalVariables.main.gamestate.reset();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(container, "The chromosome must complete it's run first.");
                        }
//                        ct.getPopulation().chromosomes.get(list.getSelectedIndex());
                    }
                }
            }
        });

        details_gene.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                if(list.getSelectedIndex()!=-1)
                {
                    if(GlobalVariables.main.controller instanceof ControllerTime)
                    {
                        ControllerTime ct = (ControllerTime)GlobalVariables.main.controller;
                        Chromosome c = ct.getPopulation().chromosomes.get(list.getSelectedIndex());
                        new GeneDetailsWindow((ChromosomeTime)c);
                    }
                }
            }
        });

        
    }

    public static PopulationWindow getInstance()
    {
        if(singleton==null)
            singleton=new PopulationWindow();
        return singleton;
    }
    
    public void fillList(Object[] objects)
    {        
        list.setListData(objects);
        this.repaint();
    }

    public void fillList(ArrayList<Chromosome> objects)
    {
        fillList(objects.toArray(new Chromosome[objects.size()]));
    }

}
