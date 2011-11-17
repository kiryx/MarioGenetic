/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import mariogenetic.Global.Keys;

/**
 *
 * @author alice
 */
public class GeneticsConf {

    public static double move_probability = 0.9;
    public static double right_probability = 0.9;

    public static double modifier_value = 2.5;

    private HashMap<Global.Keys,Double> moveProb = new HashMap<Global.Keys,Double>();
    private double totalMove;
    
    private HashMap<Global.Keys,Double> specialProb = new HashMap<Global.Keys,Double>();
    private double totalSpecial;

//    private ArrayList<ProbPair<Global.Keys> > realMoveProb = new ArrayList<ProbPair<Global.Keys> >();
//    private ArrayList<ProbPair<Global.Keys> > realSpecialProb = new ArrayList<ProbPair<Global.Keys> >();

    public static int population_size = 10;

    public static double jump_probability = 0.45;

    private static GeneticsConf singleton = null;
    private Random generator = null;

    private void reCalcProbabilities()
    {
        totalMove = 0.0;
        for(Double d : moveProb.values())
        {
            totalMove += d;
        }

        totalSpecial = 0.0;
        for(Double d : specialProb.values())
        {
            totalSpecial += d;
        }
    }

    public void setKeyProbability(Global.Keys key, Double d)
    {
        
        d = Math.abs(d);

        if(moveProb.get(key)!=null)
        {
            moveProb.put(key, d);
        }
        else if(specialProb.get(key)!=null)
        {
            specialProb.put(key, d);
        }
        else
        {
            System.out.println("Key value does not exist in hashmap");
        }
        reCalcProbabilities();
    }
    
    private GeneticsConf()
    {
        generator = new Random();
                
        for(Global.Keys k : Global.moveKeys)
        {
            moveProb.put(k, 0.0);
        }
        
        moveProb.put(Global.Keys.LEFT, 0.05);
        moveProb.put(Global.Keys.RIGHT, 0.8);
        moveProb.put(Global.Keys.NONE, 0.1);

        for(Global.Keys k : Global.specialKeys)
        {
            specialProb.put(k, 0.0);
        }

        specialProb.put(Global.Keys.A, 0.8);
        specialProb.put(Global.Keys.NONE, 0.2);
        reCalcProbabilities();

    }

    public static GeneticsConf getInstance()
    {
        if(singleton==null)
            singleton=new GeneticsConf();
        return singleton;
    }

    private Random getGenerator()
    {
        return generator;
    }


    public Global.Keys getRandomMove()
    {
        return getRandom(moveProb,totalMove);
    }
    public Global.Keys getRandomSpecial()
    {
        return getRandom(specialProb,totalSpecial);
    }


    public Global.Keys getRandomMove(Global.Keys[] modifiers)
    {
        return getRandom(modifiers, moveProb);
    }
    public Global.Keys getRandomSpecial(Global.Keys[] modifiers)
    {
        return getRandom(modifiers,specialProb);
    }

    private Global.Keys getRandom(HashMap<Global.Keys,Double> map, double total)
    {
        double d = getGenerator().nextDouble();

        d*=total; //normalize random value

        double sum = 0.0;

        //Iterate over whole move set to determine random Key
        for(Map.Entry<Global.Keys,Double> e : map.entrySet())
        {
            if(d>=sum && d<(sum+e.getValue()))
            {
                return e.getKey();
            }
            sum+=e.getValue();
        }

        Dbg.o("Had to return NONE! GeneticsConf:getRandom");
        return Global.Keys.NONE;

        //        double real_right = right_probability*move_probability;
//        double real_left = (1.0-right_probability)*move_probability;
//
//        if(d>=real_right+real_left)
//            return Global.Keys.NONE;
//        else if(d<real_right+real_left && d>=real_right)
//            return Global.Keys.LEFT;
//        else
//            return Global.Keys.RIGHT;
    }

    private Global.Keys getRandom(Global.Keys[] modifiers, HashMap<Global.Keys,Double> map)
    {
        double d = getGenerator().nextDouble();        

        HashMap<Global.Keys,Double> map2 = (HashMap<Global.Keys, Double>) map.clone();
        

        for(Global.Keys k : modifiers)
        {
            Double new_val = map2.get(k)*modifier_value;
            map2.put(k, new_val);
        }
        
        double totalMove2 = 0.0;
        for(Double p : map2.values())
        {
            totalMove2 += p;
        }
        
        d*=totalMove2;
        
        double sum = 0.0;
        
        for(Map.Entry<Global.Keys,Double> e : map2.entrySet())
        {
            if(d>=sum && d<(sum+e.getValue()))
            {                
                return e.getKey();
            }
            sum+=e.getValue();
        }
        
        return Global.Keys.NONE;
    }

    

}
