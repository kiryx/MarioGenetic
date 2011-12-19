/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.gene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import mariogenetic.Global;
import mariogenetic.game.GameState;
import mariogenetic.gene.Chromosome;

/**
 *
 * @author alice
 */
public class Population {
    public ArrayList<Chromosome> chromosomes = new ArrayList<Chromosome>();
    public Population(){}
    public Population(ArrayList<Chromosome> parent)
    {
        chromosomes = new ArrayList<Chromosome>();
        chromosomes.addAll(parent);
    }
    
    public Population(int size)
    {
        generateNew(size);
    }
    public void generateNew(int count)
    {
        chromosomes.clear();
        for (int i = 0; i < count; i++) {
            chromosomes.add(new ChromosomeTime());
        }
    }

    public static Chromosome[] getParents(int min, int max, ArrayList<Chromosome> population )
    {
        Random rand = new Random();
        Integer size = rand.nextInt((max-min)+1)+min;        
        Chromosome[] parents = new Chromosome[size];
        double d = rand.nextDouble();

        
        double total = 0;
        for(Chromosome e : population)
        {
            total+=e.resultData.getFinalScore();
        }

        if(total==0)
        {
            for (int i = 0; i <size ; i++) {
                parents[i]=population.get(i);

            }
            return parents;
        }
        
        d*=total; //normalize random value

        double sum = 0.0;

        //Iterate over whole move set to determine random Key
        
        for (int i = 0; i < parents.length; i++) {
            
            for(Chromosome e : population)
            {
                if(d>=sum && d<=(sum+e.resultData.getFinalScore()))
                {
                    parents[i]=e;
                    break;
                }
                sum+=e.resultData.getFinalScore();
            }
        }
        return parents;        
    }
    
    public Population nextPopulation() {
        Collections.sort(chromosomes);

        Collections.reverse(chromosomes);

        ArrayList<Chromosome> new_population = new ArrayList<Chromosome>();
//        for (int i = 0; i < chromosomes.size()/2; i++) {
//            elita.add(chromosomes.get(i));
//        }
        GeneticsConfig gc = GeneticsConfig.getInstance();
        
        Integer population_size = (Integer)gc.get_parameter(GeneticsConfig.Param.POPULATION_SIZE);
        Integer elite_size = (Integer) gc.get_parameter(GeneticsConfig.Param.CROSSING_ELITE_SIZE);
        Integer offspring_count = (Integer)gc.get_parameter(GeneticsConfig.Param.CROSSING_OFFSPRING_COUNT);
        Integer remaining = population_size-(elite_size+offspring_count);
        Boolean elite_parents = (Boolean)gc.get_parameter(GeneticsConfig.Param.CROSSING_ELITE_IS_PARENTS);
                
        for (int i = 0; i < elite_size; i++) {
            new_population.add(chromosomes.get(i));
        }     

        
        //naraize elita == rodzice
        Chromosome[] parents=null;
        if(elite_parents)
            parents = (Chromosome[]) new_population.toArray(new Chromosome[new_population.size()]);
        else
        {
            Integer min = (Integer)gc.get_parameter(GeneticsConfig.Param.CROSSING_PARENT_SET_MIN);
            Integer max = (Integer)gc.get_parameter(GeneticsConfig.Param.CROSSING_PARENT_SET_MAX);
            parents = (Chromosome[]) Population.getParents(min, max, chromosomes);
        }        
        
        while(new_population.size()<remaining) 
        {
//            System.out.println(parents);
//            for (int i = 0; i < parents.length; i++) {
//                System.out.println(parents[i]);
//                
//            }
            new_population.add(new ChromosomeTime(parents));

            
            //new_population.add(new ChromosomeTime());
        }
        while(new_population.size()<population_size)
        {
            new_population.add(new ChromosomeTime());
        }
        
        //krzyzowanie

        Double mut_prob = (Double)gc.get_parameter(GeneticsConfig.Param.MUTATION_MOVES_PROBABILITY);
        Double spec_mut_prob = (Double)gc.get_parameter(GeneticsConfig.Param.MUTATION_SPECIAL_PROBABILITY);
        Double mut_breadth = (Double)gc.get_parameter(GeneticsConfig.Param.MUTATION_MOVES_BREADTH);
        Double spec_mut_breadth = (Double)gc.get_parameter(GeneticsConfig.Param.MUTATION_SPECIAL_BREADTH);
        Random rand = new Random();
        for (int i = 0; i < new_population.size(); i++) {
            Double d = rand.nextDouble();
            if(d<mut_prob)
            {
                ((ChromosomeTime)new_population.get(i)).mutateMoves(mut_breadth);
            }
            d = rand.nextDouble();
            if(d<spec_mut_prob)
            {
                ((ChromosomeTime)new_population.get(i)).mutateSpecial(spec_mut_breadth);
            }
            
        }

//        while(new_population.size()<GeneticsConf.population_size)
//        {
//            new_population.add(new ChromosomeTime());
//        }
//        chromosomes.clear();
//        chromosomes.addAll(elita);

        for(Chromosome c : new_population)
        {
            c.resultData=null;
        }
        return new Population(new_population);
    }


}
