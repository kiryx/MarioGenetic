/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.gene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import mariogenetic.GeneticsConf;
import mariogenetic.Global;
import mariogenetic.game.GameState;

/**
 *
 * @author alice
 */
public class Population {
    public ArrayList<Chromosome> chromosomes = new ArrayList<Chromosome>();
    public Population()
    {

    }
    public void generateNew(int count)
    {
        chromosomes.clear();
        for (int i = 0; i < count; i++) {
            chromosomes.add(new ChromosomeTime());
        }
    }

    public void nextPopulation() {
        Collections.sort(chromosomes);

        Collections.reverse(chromosomes);
//        System.out.println("SORTED:\n------");
//        for (int i = 0; i < chromosomes.size(); i++) {
//            System.out.println(chromosomes.get(i));
//        }
//        System.out.println("------");
        ArrayList<Chromosome> new_population = new ArrayList<Chromosome>();
//        for (int i = 0; i < chromosomes.size()/2; i++) {
//            elita.add(chromosomes.get(i));
//        }
        for (int i = 0; i < chromosomes.size() && (new_population.size()< (GeneticsConf.population_size/2)); i++) {
            if(chromosomes.get(i).resultData.final_state==GameState.RESULT_WON)
                new_population.add(chromosomes.get(i));

        }
        
        Chromosome[] parents = (Chromosome[]) new_population.toArray(new Chromosome[new_population.size()]);
        while(new_population.size()<GeneticsConf.population_size)
        {
            new_population.add(new ChromosomeTime(parents));
        }

//        while(new_population.size()<GeneticsConf.population_size)
//        {
//            new_population.add(new ChromosomeTime());
//        }
//        chromosomes.clear();
//        chromosomes.addAll(elita);
        chromosomes = new_population;
    }


}
