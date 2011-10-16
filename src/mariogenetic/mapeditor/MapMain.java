/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.mapeditor;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import mariogenetic.Conf;
import mariogenetic.Global;
import mariogenetic.Vector;
import mariogenetic.game.RendererBasic;
import mariogenetic.game.Resources;
import mariogenetic.objects.Bonus;
import mariogenetic.objects.BonusCoin;
import mariogenetic.objects.BonusKills;
import mariogenetic.objects.BonusWin;
import mariogenetic.objects.Player;
import mariogenetic.objects.Terrain;
import mariogenetic.objects.WorldObject;

/**
 *
 * @author alice
 */
public class MapMain extends JPanel implements KeyListener, MouseListener, MouseMotionListener{

    Rectangle selection;
    JButton btn_terrain,btn_kills,btn_coin,btn_win,btn_player;
    JButton btn_undo, btn_delete,btn_clear,btn_testrun;
    JTextField txt_value_field = new JTextField("5");
    JButton[] buttons;

    WorldObject selected_object = null;

    Point screenDrag=new Point(0,0);
    Rectangle screenDrag_tmp=null;
    BlockType selected_type;
    ArrayList<WorldObject> world_objects = new ArrayList<WorldObject>();


    public enum BlockType
    {
        Terrain, BonusKills, BonusCoin, BonusWin, Player
    }
    public MapMain()
    {

        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setFocusable(true);
        final MapMain this_map = this;


        btn_testrun = new JButton("Test Run",new ImageIcon("img/cassette.png"));
        btn_testrun.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                String test_path = "maps/test_run";
                saveMap(test_path);
                Global.frame_main.resources = new Resources(test_path);
                Global.frame_main.gamestate.reset();
                repaint();
            }
        });

        btn_undo = new JButton("Undo",new ImageIcon("img/reload.png"));
        btn_undo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                if(world_objects.size()>0)
                {
                    world_objects.remove(world_objects.size()-1);
                    selected_object = null;
                }
                repaint();
            }
        });
        
        btn_clear = new JButton("Erase All",new ImageIcon("img/recycle-empty.png"));
        btn_clear.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                                
                if(JOptionPane.showConfirmDialog(this_map, "Erase the whole thing ?")==JOptionPane.YES_OPTION)
                {
                    world_objects.clear();
                    selected_object = null;
                }
                repaint();
            }
        });



        btn_delete = new JButton("Delete",new ImageIcon("img/delete-item.png"));
        btn_delete.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                if(selected_object!=null)
                {
                    world_objects.remove(selected_object);
                    selected_object=null;
                }
                repaint();
            }
        });

        btn_terrain = new JButton("Terrain",new ImageIcon("img/line-globe.png"));
        btn_terrain.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                setSelected(BlockType.Terrain);                
            }
        });        

        btn_coin = new JButton("Value",new ImageIcon("img/dollar.png"));
        btn_coin.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                setSelected(BlockType.BonusCoin);

            }
        });
        btn_player = new JButton("Actor",new ImageIcon("img/agent.png"));
        btn_player.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                setSelected(BlockType.Player);

            }
        });

        btn_kills = new JButton("Lose",new ImageIcon("img/light.png"));
        btn_kills.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                setSelected(BlockType.BonusKills);

            }
        });
        btn_win = new JButton("Win",new ImageIcon("img/star.png"));
        btn_win.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                setSelected(BlockType.BonusWin);
                
            }
        });
        
        buttons = new JButton[]{ btn_terrain,btn_kills,btn_coin,btn_win,btn_player};
        for(int i=0;i<buttons.length;++i)
            buttons[i].setBackground(Conf.color_map_not_selected);
        this.setSelected(BlockType.Terrain);        


        
    }
    private void loadMap(String path) {
        Resources resources = new Resources(path);
        world_objects = resources.getAsWorldObjects();
        repaint();
    }
    public void saveMap(String filename)
    {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            for(WorldObject wo : world_objects)
            {

                if(wo instanceof Terrain || wo instanceof Player)
                {
                    WorldObject a = wo;
                    out.write(String.format("%s %d %d %d %d\n", a.getClass().getSimpleName(),
                            (int)a.position.x,(int)a.position.y,a.size.x,a.size.y));
                    
                }
                else if(wo instanceof Bonus)
                {
                    Bonus a = (Bonus)wo;
                    out.write(String.format("%s %d %d %d %d %d\n", a.getClass().getSimpleName(),
                            (int)a.position.x,(int)a.position.y,a.size.x,a.size.y,a.value));
                    
                }
            }
            out.close();
        } catch (IOException e) {
        }
    }
    public void translateDiff(Graphics2D g2, Rectangle box)
    {
        if(box!=null)
            g2.translate(box.width-box.x,box.height-box.y);
    }
    public void translateDiff(Graphics2D g2, Point box)
    {
        if(box!=null)
            g2.translate(box.x,box.y);
    }
    public void translateDiffNeg(Graphics2D g2, Point box)
    {
        if(box!=null)
            g2.translate(-box.x,-box.y);
    }
    public void translateDiffNeg(Graphics2D g2, Rectangle box)
    {
        if(box!=null)
            g2.translate(box.x-box.width,box.y-box.height);
            
    }
    public Point getScreenDragTotal()
    {
        if(screenDrag_tmp==null)
            return screenDrag;
        return new Point(screenDrag.x+(screenDrag_tmp.width-screenDrag_tmp.x),
                         screenDrag.y+(screenDrag_tmp.height-screenDrag_tmp.y));
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        translateDiff(g2, screenDrag);
        translateDiff(g2, screenDrag_tmp);
        RendererBasic.drawGrid(g2, this);
        if(selection!=null)
        {
            g2.drawRect(Math.min(selection.x,selection.width)-screenDrag.x,
                    Math.min(selection.y,selection.height)-screenDrag.y,
                    Math.abs(selection.width-selection.x),
                    Math.abs(selection.height-selection.y));            
        }
        Point screenDragTotal = this.getScreenDragTotal();
//        g2.drawString("Block Type: "+String.valueOf(selected_type), 10-screenDragTotal.x, 30-screenDragTotal.y);
        
        for(WorldObject o : world_objects)
            o.paint(g2);

        g2.setColor(Conf.color_map_object_selected);
        if(selected_object!=null)
        g2.drawRect((int)selected_object.position.x, (int)selected_object.position.y,
                selected_object.size.x, selected_object.size.y);

        translateDiffNeg(g2, screenDrag_tmp);
        translateDiffNeg(g2, screenDrag);
    
    }

     public static void main(String[] args) {

        JFrame frame = new JFrame("Map Editor");
        frame.setLayout(new BorderLayout());
        final MapMain map_main = new MapMain();

        JMenuBar jmb = new JMenuBar();
        JMenu menu = new JMenu("Editor");



        //toolbar z ikonami
        JToolBar toolbar = new JToolBar("Tools", JToolBar.HORIZONTAL);
        toolbar.setFloatable(false);

        toolbar.add(map_main.btn_player);
        toolbar.add(map_main.btn_terrain);
        toolbar.add(map_main.btn_coin);
        toolbar.add(map_main.txt_value_field);
        toolbar.add(map_main.btn_kills);
        toolbar.add(map_main.btn_win);
        
        toolbar.add(map_main.btn_undo);
        toolbar.add(map_main.btn_delete);
        toolbar.add(map_main.btn_clear);
        toolbar.add(map_main.btn_testrun);

        frame.add(toolbar,BorderLayout.NORTH);
                
                
        jmb.add(menu);
        JMenuItem mi_savemap = new JMenuItem("Save map",new ImageIcon("img/save.png"));
        menu.add(mi_savemap);
        mi_savemap.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File("maps"));
                fc.setDialogTitle("Save map");
                int returnVal = fc.showSaveDialog(map_main);
                if(returnVal==JFileChooser.APPROVE_OPTION)
                {

                    File file = fc.getSelectedFile();
                    map_main.saveMap(file.getPath());
                }
            }
        });

        JMenuItem mi_loadmap = new JMenuItem("Load map",new ImageIcon("img/download.png"));
        menu.add(mi_loadmap);
        mi_loadmap.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File("maps"));
                fc.setDialogTitle("Load map");
                int returnVal = fc.showOpenDialog(map_main);
                if(returnVal==JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    map_main.loadMap(file.getPath());
                }
            }
        });

        frame.setJMenuBar(jmb);
        
        frame.setPreferredSize(Conf.window_editor_size);
        int loc_x = 0;
        if(Global.frame_main!=null)
        {
            loc_x = Global.frame_main.getSize().width;
            map_main.world_objects = Global.frame_main.resources.getReopenedResource();
         }
        else
        {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        frame.setLocation(loc_x, 0);
        frame.getContentPane().add(map_main);
        frame.pack();
        frame.setVisible(true);
   }

    public void keyTyped(KeyEvent e) {

    }

    public void setSelected(BlockType type)
    {
        selected_type = type;        
        for(int i=0 ; i < buttons.length;++i)
            buttons[i].setBackground(Conf.color_map_not_selected);
        buttons[type.ordinal()].setBackground(Conf.color_map_selected);
        repaint();
    }
    public void keyPressed(KeyEvent e) {        
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_1:
            {
                selected_type=BlockType.Terrain;
                break;
            }
            case KeyEvent.VK_2:
            {
                selected_type=BlockType.BonusCoin;
                break;
            }
            case KeyEvent.VK_3:
            {
                selected_type=BlockType.BonusKills;
                break;
            }
            case KeyEvent.VK_4:
            {
                selected_type=BlockType.BonusWin;
                break;
            }
            case KeyEvent.VK_5:
            {
                selected_type=BlockType.Player;
                break;
            }            
            case KeyEvent.VK_U:
            {
                if(world_objects.size()>0)
                    world_objects.remove(world_objects.size()-1);
            }
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

        System.out.println(e.getX());
        for(WorldObject o : world_objects)
        {
            if(o.getRectangle().intersects(new Rectangle(e.getX(),e.getY(),1,1)))
            {
                selected_object = o;
                break;
            }
        }
        repaint();
    }

    public void mousePressed(MouseEvent e) {
        if(e.getButton() == e.BUTTON1)
        {
            selection = new Rectangle(e.getX(),e.getY(),e.getX(),e.getY());

        }
        else if(e.getButton() == e.BUTTON3)
        {
            screenDrag_tmp = new Rectangle(e.getX(),e.getY(),e.getX(),e.getY());
        }
        
    }

    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == e.BUTTON1)
        {
            Rectangle new_sel = new Rectangle(Math.min(selection.x,selection.width)-screenDrag.x,
                        Math.min(selection.y,selection.height)-screenDrag.y,
                        Math.abs(selection.width-selection.x),
                        Math.abs(selection.height-selection.y));

            //jesli prostokat o polu 1
            if(new_sel.width<1 && new_sel.height<1)
                return;
            selection = null;
            switch(selected_type)
            {
                case Terrain:
                {
                    world_objects.add(new Terrain(new Vector(new_sel.x,new_sel.y),new Point(new_sel.width,new_sel.height)));
                    break;
                }
                case BonusCoin:
                {

                    Integer val;
                    try
                    {
                        val = Integer.parseInt(txt_value_field.getText());
                    }
                    catch (NumberFormatException ex)
                    {
                        val = 5;
                        txt_value_field.setText("5");
                    }

                    world_objects.add(new BonusCoin(new Vector(new_sel.x,new_sel.y),new Point(new_sel.width,new_sel.height),val));
                    break;
                }
                case BonusKills:
                {
                    world_objects.add(new BonusKills(new Vector(new_sel.x,new_sel.y),new Point(new_sel.width,new_sel.height),-50));
                    break;
                }
                case BonusWin:
                {
                    world_objects.add(new BonusWin(new Vector(new_sel.x,new_sel.y),new Point(new_sel.width,new_sel.height),50));
                    break;
                }
                case Player:
                {
                    world_objects.add(new Player(new Vector(new_sel.x,new_sel.y),new Point(new_sel.width,new_sel.height)));
                    break;
                }

            }
        }
        else if(e.getButton() == e.BUTTON3)
        {
            screenDrag=getScreenDragTotal();
            screenDrag_tmp=null;

        }
        this.repaint();
        
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        
        if(selection!=null)
        {
            selection.width = e.getX();
            selection.height = e.getY();
//            selection.x = Math.min(selection.x, e.getX());
//            selection.y = Math.min(selection.y, e.getY());
//            System.out.println(selection);

        }

        if(screenDrag_tmp!=null)
        {
            screenDrag_tmp.width=e.getX();
            screenDrag_tmp.height=e.getY();
        }
        this.repaint();
    }

    public void mouseMoved(MouseEvent e) {

    }
}
