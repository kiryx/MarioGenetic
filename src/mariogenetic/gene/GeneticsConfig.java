/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.gene;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import mariogenetic.main.Dbg;
import mariogenetic.main.GlobalVariables;

/**
 *
 * @author alice
 */
public class GeneticsConfig {

    public enum Keys
    {
        NONE,UP,DOWN,LEFT,RIGHT,A,B,C,D
    }
    public static Keys moveKeys[] = {Keys.UP, Keys.DOWN, Keys.LEFT, Keys.RIGHT, Keys.NONE};
    public static Keys specialKeys[] = {Keys.A, Keys.B, Keys.C, Keys.D, Keys.NONE};
    private GeneticsConfig()
    {
        generator = new Random();

        for(GeneticsConfig.Keys k : GeneticsConfig.moveKeys)
        {
            moveProb.put(k, 0.0);
        }

        moveProb.put(GeneticsConfig.Keys.LEFT, 0.4);
        moveProb.put(GeneticsConfig.Keys.RIGHT, 0.7);
        moveProb.put(GeneticsConfig.Keys.NONE, 0.0);

        for(GeneticsConfig.Keys k : GeneticsConfig.specialKeys)
        {
            specialProb.put(k, 0.0);
        }

        specialProb.put(GeneticsConfig.Keys.A, 0.2);
        specialProb.put(GeneticsConfig.Keys.NONE, 0.8);
        reCalcProbabilities();


        register_parameter(Param.CROSSING_PARAMETER, 5.0, Double.class);
        register_parameter(Param.POPULATION_SIZE, 15, Integer.class);
        register_parameter(Param.CROSSING_ELITE_SIZE, 2, Integer.class);
        register_parameter(Param.CROSSING_PARENT_SET_MIN, 1, Integer.class);
        register_parameter(Param.CROSSING_PARENT_SET_MAX, 3, Integer.class);
        register_parameter(Param.CROSSING_OFFSPRING_COUNT, 5, Integer.class);
        register_parameter(Param.MUTATION_MOVES_PROBABILITY, 0.02, Double.class);
        register_parameter(Param.MUTATION_MOVES_BREADTH, 0.015, Double.class);
        register_parameter(Param.MUTATION_SPECIAL_PROBABILITY, 0.02, Double.class);
        register_parameter(Param.MUTATION_SPECIAL_BREADTH, 0.015, Double.class);
        register_parameter(Param.CROSSING_ELITE_IS_PARENTS, false, Boolean.class);
        register_parameter(Param.MAXIMUM_TIME, 10000, Integer.class); 
        register_parameter(Param.MOVES_PER_SECOND, 20, Integer.class);
        register_parameter(Param.FUNCTION_DEAD_MULTIPLIER, 0.5, Double.class);
        register_parameter(Param.FUNCTION_WON_MULTIPLIER, 2.0, Double.class);
        register_parameter(Param.FUNCTION_TIMEOUT_MULTIPLIER, 1.0, Double.class);
        register_parameter(Param.FUNCTION_TIME_MULTIPLIER, 50.0, Double.class);
        
    }
    public static enum Param {
       CROSSING_PARAMETER,POPULATION_SIZE,CROSSING_ELITE_SIZE,
       CROSSING_OFFSPRING_COUNT,CROSSING_PARENT_SET_MIN, CROSSING_PARENT_SET_MAX,
       MUTATION_MOVES_PROBABILITY,MUTATION_MOVES_BREADTH,
       MUTATION_SPECIAL_PROBABILITY, MUTATION_SPECIAL_BREADTH,
       CROSSING_ELITE_IS_PARENTS,MAXIMUM_TIME,MOVES_PER_SECOND,
       FUNCTION_TIMEOUT_MULTIPLIER, FUNCTION_WON_MULTIPLIER, FUNCTION_DEAD_MULTIPLIER,
       FUNCTION_TIME_MULTIPLIER
    }

    public static GeneticsConfig singleton;

    public static GeneticsConfig getInstance()
    {
        
        if(singleton == null)
            singleton = new GeneticsConfig();
        return singleton;
    }
    
    private HashMap<Param,Object> genetic_params = new HashMap<Param,Object>();
    private HashMap<Param,Class> genetic_param_classes = new HashMap<Param,Class>();

    private HashMap<GeneticsConfig.Keys,Double> moveProb = new HashMap<GeneticsConfig.Keys,Double>();
    private double totalMove;
    
    private HashMap<GeneticsConfig.Keys,Double> specialProb = new HashMap<GeneticsConfig.Keys,Double>();
    private double totalSpecial;
    
    private Random generator = null;

         
    private void register_parameter(Param p, Object o, Class c )
    {
        genetic_params.put(p, o);
        genetic_param_classes.put(p, c);
    }
    public void update_parameter(Param p, String s)
    {
        Class c = (Class)genetic_param_classes.get(p);
        Method m = null;
        try {
            m = c.getMethod("valueOf", new Class[] {String.class});
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(GeneticsConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GeneticsConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

        Object o = null;
        try {
            o = (Object)m.invoke(null,s);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GeneticsConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GeneticsConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(GeneticsConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        genetic_params.put(p, o);
//        if(c.equals(Double.class))
//        {
//            genetic_params.put(p,Double.valueOf(s));
//        }
//        else if( c.equals(Integer.class))
//        {
//            genetic_params.put(p,Integer.valueOf(s));
//        }
//        else
//        {
//            System.out.println("Not known data type:"+c.getName()+" in GeneticsConfig:update_parameter");
//        }
    }
    public Object get_parameter(Param p)
    {
        return genetic_params.get(p);
    }
    public HashMap<Param,Object> getParamsMap()
    {
        return genetic_params;
    }
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

    public void setMoveKeyProbability(GeneticsConfig.Keys key, Double d)
    {
        
        d = Math.abs(d);

        if(moveProb.get(key)!=null)
        {
            moveProb.put(key, d);
        }       
        else
        {
            System.out.println("Key value does not exist in hashmap");
        }
        reCalcProbabilities();
    }

    public void setSpecialKeyProbability(GeneticsConfig.Keys key, Double d)
    {
        d = Math.abs(d);

        if(specialProb.get(key)!=null)
        {
            specialProb.put(key, d);
        }
        else
        {
            System.out.println("Key value does not exist in hashmap");
        }
        reCalcProbabilities();
    }

    public Double getSpecialKeyProbability(GeneticsConfig.Keys key)
    {
        return specialProb.get(key);
    }
    public Double getMoveKeyProbability(GeneticsConfig.Keys key)
    {
        return moveProb.get(key);
    }
    
    private Random getGenerator()
    {
        return generator;
    }

    public GeneticsConfig.Keys getRandomMove()
    {
        return getRandom(moveProb,totalMove);
    }
    public GeneticsConfig.Keys getRandomSpecial()
    {
        return getRandom(specialProb,totalSpecial);
    }

    public GeneticsConfig.Keys getRandomMove(GeneticsConfig.Keys[] modifiers)
    {
        return getRandom(modifiers, moveProb);
    }
    public GeneticsConfig.Keys getRandomSpecial(GeneticsConfig.Keys[] modifiers)
    {
        return getRandom(modifiers,specialProb);
    }

    private GeneticsConfig.Keys getRandom(HashMap<GeneticsConfig.Keys,Double> map, double total)
    {
        double d = getGenerator().nextDouble();

        d*=total; //normalize random value

        double sum = 0.0;

        //Iterate over whole move set to determine random Key
        for(Map.Entry<GeneticsConfig.Keys,Double> e : map.entrySet())
        {
            if(d>=sum && d<(sum+e.getValue()))
            {
                return e.getKey();
            }
            sum+=e.getValue();
        }

        Dbg.o("Had to return NONE! GeneticsConf:getRandom");
        return GeneticsConfig.Keys.NONE;
    }

    private GeneticsConfig.Keys getRandom(GeneticsConfig.Keys[] modifiers, HashMap<GeneticsConfig.Keys,Double> map)
    {
        double d = getGenerator().nextDouble();        

        HashMap<GeneticsConfig.Keys,Double> map2 = (HashMap<GeneticsConfig.Keys, Double>) map.clone();

        Double crossing_parameter = (Double)get_parameter(Param.CROSSING_PARAMETER);

        for(GeneticsConfig.Keys k : modifiers)
        {
            Double new_val= map2.get(k)*crossing_parameter;
            
            map2.put(k, new_val);
        }
        
        double totalMove2 = 0.0;
        for(Double p : map2.values())
        {
            totalMove2 += p;
        }
        
        d*=totalMove2;
        
        double sum = 0.0;
        
        for(Map.Entry<GeneticsConfig.Keys,Double> e : map2.entrySet())
        {
            if(d>=sum && d<(sum+e.getValue()))
            {                
                return e.getKey();
            }
            sum+=e.getValue();
        }
        
        return GeneticsConfig.Keys.NONE;
    }

    

}
