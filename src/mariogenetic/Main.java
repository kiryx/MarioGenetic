/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic;

import mariogenetic.GUI.PopulationWindow;
import mariogenetic.game.logic.Logic;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import mariogenetic.game.*;
import mariogenetic.game.logic.LogicMario;
import mariogenetic.mapeditor.MapMain;

/**
 *
 * @author alice
 */
public class Main extends JPanel implements Runnable{

        public Renderer renderer;
        public Logic logic;
        public Resources resources;
        public Controller controller;
        public GameState gamestate;
        
	public Main()
	{
            this.setSize(Config.window_main_size);
            this.setDoubleBuffered(true);
            this.setPreferredSize(Config.window_main_size);
            this.setIgnoreRepaint(true);
            
            Graphics2D g2 =(Graphics2D) this.getGraphics();
            Global.main = this;
            

            logic = new LogicMario();
            resources = new Resources("maps/map1");
//            controller = new ControllerHuman();
            controller = new ControllerTime();
            renderer = RendererBasic.getInstance();
            gamestate = GameState.getInstance();

            this.setFocusable(true);
            this.addKeyListener(new KeyListener()
            {
                    public void keyTyped(KeyEvent e) {
                        controller.keyTyped(e);
                    }

                    public void keyPressed(KeyEvent e) {

                        controller.keyPressed(e);

                    }

                    public void keyReleased(KeyEvent e) {
                        controller.keyReleased(e);
                    }
            });

            new Thread(this).start();
	}

    /**
     * @param args the command line arguments
     */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                renderer.render(g2);
	}
   public static void main(String[] args) {
                
        JFrame frame = new JFrame("Platformer Genetic");
        JMenuBar jmb = new JMenuBar();
        JMenu menu = new JMenu("Game");
        Global.pop_frame = PopulationWindow.getInstance();
        final Main main = new Main();

        

        JMenuItem mi_mapedit = new JMenuItem("Open map editor",new ImageIcon("img/globe.png"));
        mi_mapedit.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                MapMain.main(null);
            }
            
        });
        menu.add(mi_mapedit);

        JMenuItem mi_loadmap = new JMenuItem("Load map",new ImageIcon("img/download.png"));
        mi_loadmap.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                JFileChooser fc = new JFileChooser();

                fc.setCurrentDirectory(new File("maps"));
                fc.setDialogTitle("Load map");
                int returnVal = fc.showOpenDialog(main);
                if(returnVal==JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    main.resources = new Resources(file.getPath());
                    main.gamestate.reset();
                }
            }
            
        });
        menu.add(mi_loadmap);

        JMenuItem mi_settings = new JMenuItem("Settings",new ImageIcon("img/configuration02.png"));
        mi_settings.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                Config.main(null);
            }

        });
        menu.add(mi_settings);


        JMenuItem mi_populationFrame = new JMenuItem("Population",new ImageIcon("img/lab.png"));
        mi_populationFrame.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e)
            {
                PopulationWindow pw = PopulationWindow.getInstance();
                pw.setVisible(!pw.isVisible());                
            }

        });
        menu.add(mi_populationFrame);

        JToolBar toolbar = new JToolBar("Tools", JToolBar.HORIZONTAL);
        toolbar.setFocusable(false);
        
        main.setFocusable(true);
        

        toolbar.setFloatable(false);
        JButton btn_restart = new JButton("Restart",new ImageIcon("img/reload.png"));
        btn_restart.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                main.gamestate.reset();
//                main.resources.reset();
                main.controller.resetPopulation();
                main.requestFocus();
            }
        });
        toolbar.add(btn_restart);


        final JButton btn_speed = new JButton("SpeedUp",new ImageIcon("img/plane.png"));
        btn_speed.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                //Jesli ma przyspieszyc
                if(Global.SLEEP_TIME>0)
                {
                    btn_speed.setBackground(Config.color_map_selected);
                    Global.SLEEP_TIME=0;
                }
                else
                {
                    btn_speed.setBackground(Config.color_map_not_selected);
                    Global.SLEEP_TIME=10;
                }
                main.requestFocus();
            }
        });
        toolbar.add(btn_speed);

        final JButton btn_cam = new JButton("Mouse Cam",new ImageIcon("img/mouse.png"));
        btn_cam.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Config.mouse_cam = !Config.mouse_cam;

                if(Config.mouse_cam)
                {
                    btn_cam.setBackground(Config.color_map_selected);
                    main.renderer.getCamera().setFollow(false);
//                    main.renderer.render();
                    
                }
                //follow player
                else
                {
                    btn_cam.setBackground(Config.color_map_not_selected);
                    main.renderer.getCamera().setFollow(true);
//                    main.renderer.render();
                    
                }
                main.requestFocus();

            }
        });
        toolbar.add(btn_cam);

        frame.setLayout(new BorderLayout());
        frame.add(toolbar,BorderLayout.NORTH);
        
        jmb.add(menu);
        frame.setJMenuBar(jmb);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(main);
        frame.pack();
        frame.setVisible(true);
        main.requestFocus();
   }

   public void run() {
        
        while(true)
        {
            logic.doLogic();
            if(Global.SLEEP_TIME>0)
                this.repaint();
            controller.generateEvent();
            gamestate.check();
            gamestate.updateTime(10);
            Thread.currentThread().yield();
            try
            {
                    Thread.currentThread().sleep(Global.SLEEP_TIME);
            }catch (InterruptedException e) {e.printStackTrace();}
        }
    }
}
