/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import mariogenetic.*;
import mariogenetic.objects.*;


/**
 *
 * @author alice
 */
public class Resources {

    
    public ArrayList<Actor> actors;
    public ArrayList<Terrain> terrain;
    public ArrayList<Bonus> bonus;
    public String resourceName;
//    public Actor main_actor;

    public Resources(String file){
        Global.shuffling_resources=true;
        actors = new ArrayList<Actor>();
        terrain = new ArrayList<Terrain>();
        bonus = new ArrayList<Bonus>();
        Global.shuffling_resources=false;
        resourceName = file;
        loadResources(file);
    }
    public Actor getMainActor()
    {
        if(actors.size()>0)
        {
            return actors.get(0);
        }
        return null;
    }
    public void loadResources(String file)
    {
        if(Global.shuffling_resources)
        {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Global.shuffling_resources=true;

        actors = new ArrayList<Actor>();
        terrain = new ArrayList<Terrain>();
        bonus = new ArrayList<Bonus>();
        
        this.resourceName = file;
           try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null) {
                String[] arr = str.split(" ");
                if(arr[0].equals(Player.class.getSimpleName()))
                {
                    actors.add(new Player(
                            new Vector(Integer.parseInt(arr[1]),
                                       Integer.parseInt(arr[2])),
                            new Point(Integer.parseInt(arr[3]),
                                      Integer.parseInt(arr[4]))
                                      ));                    
                }
                else if(arr[0].equals(Terrain.class.getSimpleName()))
                {
                    terrain.add(new Terrain(
                            new Vector(Integer.parseInt(arr[1]),
                                       Integer.parseInt(arr[2])),
                            new Point(Integer.parseInt(arr[3]),
                                      Integer.parseInt(arr[4]))
                                      ));
                }
                else if(arr[0].equals(BonusCoin.class.getSimpleName()))
                {
                    bonus.add(new BonusCoin(
                            new Vector(Integer.parseInt(arr[1]),
                                       Integer.parseInt(arr[2])),
                            new Point(Integer.parseInt(arr[3]),
                                      Integer.parseInt(arr[4])),
                            Integer.parseInt(arr[5])));
                }
                else if(arr[0].equals(BonusWin.class.getSimpleName()))
                {
                    bonus.add(new BonusWin(
                            new Vector(Integer.parseInt(arr[1]),
                                       Integer.parseInt(arr[2])),
                            new Point(Integer.parseInt(arr[3]),
                                      Integer.parseInt(arr[4])),Integer.parseInt(arr[5])));
                }

                else if(arr[0].equals(BonusLose.class.getSimpleName()))
                {
                    bonus.add(new BonusLose(
                            new Vector(Integer.parseInt(arr[1]),
                                       Integer.parseInt(arr[2])),
                            new Point(Integer.parseInt(arr[3]),
                                      Integer.parseInt(arr[4])),Integer.parseInt(arr[5])));
                }

            }
            in.close();
        } catch (IOException e) {
        }

        Global.shuffling_resources=false;
    }

    public ArrayList<WorldObject> getReopenedResource()
    {
        Resources r = new Resources(resourceName);
        return r.getAsWorldObjects();
    }

    public ArrayList<WorldObject> getAsWorldObjects()
    {
        ArrayList<WorldObject> al = new ArrayList<WorldObject>();
        al.addAll(actors);
        al.addAll(terrain);
        al.addAll(bonus);
        return al;
    }
    public void reset()
    {
        loadResources(resourceName);        
    }

}
