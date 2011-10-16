/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.gene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import mariogenetic.Global;

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
        ArrayList<Chromosome> elita = new ArrayList<Chromosome>();
//        for (int i = 0; i < chromosomes.size()/2; i++) {
//            elita.add(chromosomes.get(i));
//        }
        elita.add(chromosomes.get(0));
        
        while(elita.size()<Global.population_size)
        {
            elita.add(new ChromosomeTime());
        }
//        chromosomes.clear();
//        chromosomes.addAll(elita);
        chromosomes = elita;
    }


}
