/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import mariogenetic.gene.GeneticsConfig.Parameter;

/**
 *
 * @author alice
 */
public class Locals {

    private Locals(Language l)
    {
        registerLanguage(Language.EN);
        registerLanguage(Language.PL);
        loadLocals("none");
        setActiveLanguage(l);
    }
    public static enum Language{
        EN,PL
    }
    private void loadLocals(String filename)
    {
        //open file and load locals
        Language lang = Language.EN;

        registerString(Parameter.CROSSING_PARAMETER.toString(),"Crossing parameter",lang);
        registerString(Parameter.POPULATION_SIZE.toString(),"Size of the population",lang);
        registerString(Parameter.CROSSING_ELITE_SIZE.toString(),"Size of the elite in population",lang);
        registerString(Parameter.CROSSING_OFFSPRING_COUNT.toString(),"Number of newly generated chromosomes through crossing",lang);
        registerString(Parameter.CROSSING_PARENT_SET_MIN.toString(),"Mininum size of parent group in population",lang);
        registerString(Parameter.CROSSING_PARENT_SET_MAX.toString(),"Maximum size of parent group in population",lang);
        registerString(Parameter.MUTATION_MOVES_PROBABILITY.toString(),"Probability that moves array in chromosome will mutate",lang);
        registerString(Parameter.MUTATION_MOVES_BREADTH.toString(),"Breadth of mutation across the array (eg. 0.2 -> 20%)",lang);
        registerString(Parameter.MUTATION_SPECIAL_PROBABILITY.toString(),"Probability that special actions array in chromosome will mutate",lang);
        registerString(Parameter.MUTATION_SPECIAL_BREADTH.toString(),"Breadth of mutation across the array (eg. 0.2 -> 20%)",lang);
        registerString(Parameter.CROSSING_ELITE_IS_PARENTS.toString(),"If set to true, elite will be used as a parent population.",lang);
        registerString(Parameter.MAXIMUM_TIME.toString(),"Maximum time the chromosome is given during each simulation (in miliseconds) - requires restart",lang);
        registerString(Parameter.MOVES_PER_SECOND.toString(),"Amount of moves per second each chromosome should generate - requires restart",lang);
        registerString(Parameter.FUNCTION_TIMEOUT_MULTIPLIER.toString(),"If run result ends up being RESULT_TIMEOUT the final score is multiplied by this constant",lang);
        registerString(Parameter.FUNCTION_WON_MULTIPLIER.toString(),"If run result ends up being RESULT_WON the final score is multiplied by this constant",lang);
        registerString(Parameter.FUNCTION_DEAD_MULTIPLIER.toString(),"If run result ends up being RESULT_DEAD the final score is multiplied by this constant",lang);
        registerString(Parameter.FUNCTION_TIME_MULTIPLIER.toString(),"Additional score each run is given according to formula: (maximum_time/run_time) * time_multiplier",lang);

    }
    private static Locals singleton;
    public static Locals getInstance()
    {
        if(singleton==null)
            singleton = new Locals(Language.EN);
        return singleton;
    }
    private static Language active_language;
    private static HashMap<Language, HashMap<String,String>> strings = new HashMap<Language, HashMap<String,String>>();
    private static HashMap<String,String> dictionary;
    private void registerLanguage(Language l)
    {
        strings.put(l, new HashMap<String,String>());
    }
    public static void setActiveLanguage(Language l)
    {
        active_language = l;
        dictionary = strings.get(l);
    }
    public Language getActiveLanguage()
    {
        return active_language;
    }
    public String getString(String key)
    {
        String s = dictionary.get(key);
        if(s==null)
            s = key+"!";
        return s;
    }
    public void registerString(String key, String value, Language lang)
    {
        strings.get(lang).put(key, value);
    }

}
