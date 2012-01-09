/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.event.MouseEvent;
import mariogenetic.GUI.PopulationWindow;
import mariogenetic.game.logic.Logic;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
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
import mariogenetic.game.logic.LogicLabyrynth;
import mariogenetic.game.logic.LogicMario;
import mariogenetic.game.logic.LogicSuperMeatBoy;
import mariogenetic.main.Config;
import mariogenetic.main.GlobalVariables;
import mariogenetic.mapeditor.MapEditor;

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
        public ArrayList<Logic> logic_list= new ArrayList<Logic>();
        public int selected_logic;

        private void registerLogic(Logic l)
        {
            logic_list.add(l);
        }
        public void setLogic(int i)
        {
            logic = logic_list.get(i);
        }
	public Main()
	{
            this.setSize(Config.window_main_size);
            this.setDoubleBuffered(true);
            this.setPreferredSize(Config.window_main_size);
            this.setIgnoreRepaint(true);
            
            Graphics2D g2 =(Graphics2D) this.getGraphics();
            GlobalVariables.main = this;
            

            //logic = new LogicLabyrynth();
            //logic = new LogicMario();
            
            registerLogic(new LogicMario());
            registerLogic(new LogicLabyrynth());
            selected_logic=0;
            setLogic(selected_logic);
            //logic = new LogicSuperMeatBoy();
            resources = new Resources("maps/map1");
            controller = new ControllerHuman();
            //controller = new ControllerGenetic();
            renderer = RendererSimple.getInstance();
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
            this.addMouseMotionListener(new MouseMotionListener() {
                public void mouseDragged(MouseEvent e) {
                    controller.mouseDragged(e);
                }

                public void mouseMoved(MouseEvent e) {
                    controller.mouseMoved(e);
                }
            });
            this.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                controller.mouseClicked(e);
            }

            public void mousePressed(MouseEvent e) {
                controller.mousePressed(e);
            }

            public void mouseReleased(MouseEvent e) {
                controller.mouseReleased(e);
            }

            public void mouseEntered(MouseEvent e) {
                controller.mouseEntered(e);
            }

            public void mouseExited(MouseEvent e) {
                controller.mouseExited(e);
            }
        });

            new Thread(this).start();
	}

    /**
     * @param args the command line arguments
     */
        public void resetAll()
        {
            this.gamestate.reset();
            this.controller.resetPopulation();
            this.requestFocus();
            GlobalVariables.global_result_counter = 0;
        }
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                renderer.render(g2);
	}
        
   public static void main(String[] args) {
                
        JFrame frame = new JFrame("AI Platformer");
        JMenuBar jmb = new JMenuBar();
        JMenu menu = new JMenu("System");
        JMenu menu_help = new JMenu("Help");
        GlobalVariables.pop_frame = PopulationWindow.getInstance();
        final Main main = new Main();

        

        JMenuItem mi_mapedit = new JMenuItem("Map Editor",new ImageIcon("img/globe.png"));
        mi_mapedit.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                MapEditor.main(null);
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
                main.resetAll();
            }
        });
        toolbar.add(btn_restart);



        final JButton btn_speed = new JButton("SpeedUp",new ImageIcon("img/plane.png"));
        btn_speed.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                //Jesli ma przyspieszyc
                if(GlobalVariables.SLEEP_TIME>0)
                {
                    btn_speed.setBackground(Config.color_map_selected);
                    GlobalVariables.SLEEP_TIME=0;
                }
                else
                {
                    btn_speed.setBackground(Config.color_map_not_selected);
                    GlobalVariables.SLEEP_TIME=10;
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


        final JButton btn_mode = new JButton("Mode: User",new ImageIcon("img/connections.png"));
        btn_mode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                GlobalVariables.MODE_NEXT = (GlobalVariables.MODE_CURRENT+1)%2;
                if(GlobalVariables.MODE_NEXT==GlobalVariables.MODE_USER) {
                    btn_mode.setText("Mode: User");
                }
                else {
                    btn_mode.setText("Mode: Genetic");
                }
                main.requestFocus();
            }
        });
        toolbar.add(btn_mode);

        frame.setLayout(new BorderLayout());
        frame.add(toolbar,BorderLayout.NORTH);
        
        jmb.add(menu);
        jmb.add(menu_help);
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
            if(GlobalVariables.SLEEP_TIME>0)
                this.repaint();
            controller.generateEvent();
            gamestate.check();
            gamestate.updateTime(10);
            Thread.currentThread().yield();
            try
            {
                if(GlobalVariables.SLEEP_TIME>0)
                    Thread.currentThread().sleep(GlobalVariables.SLEEP_TIME);
            }catch (InterruptedException e) {e.printStackTrace();}
        }
    }
}
