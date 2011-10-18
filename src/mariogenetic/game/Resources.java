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
    public Actor main_actor;

    public Resources(String file){
        Global.shuffling_resources=true;
        actors = new ArrayList<Actor>();
        terrain = new ArrayList<Terrain>();
        bonus = new ArrayList<Bonus>();
        Global.shuffling_resources=false;
        resourceName = file;
        loadResources(file);
    }
    public void loadResources(String file)
    {
        Global.shuffling_resources=true;
//        synchronized(actors)
//        {
//            actors.clear();
//        }
//        synchronized(terrain)
//        {
//            terrain.clear();
//        }
//        synchronized(bonus)
//        {
//            bonus.clear();
//        }
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

                else if(arr[0].equals(BonusKills.class.getSimpleName()))
                {
                    bonus.add(new BonusKills(
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
