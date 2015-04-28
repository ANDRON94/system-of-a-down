package com.service.evolution;

import com.model.Detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mantixop on 4/21/15.
 */
public class Evolution {

    private Population population;

    public Evolution(int prise, float power, float quality, List<List<Detail>> db) {
        population = new Population(prise, power, quality, db);
    }

    public Population getPopulation(){
        return population;
    }

    public boolean createPopulation(){
        return  population.selectThePoplation(100000);
    }

    public void makeStep(int count){
        for (int i = 0; i < count; i++) {
            List<Unit> childrens = population.getAlpha().crossWith(population.getRandomUnitFromPopulation());
            for (int j = 0; j < 2; j++) {
                Unit tempChild = childrens.get(j);
                if ((population.isAlive(tempChild)) && (population.notInPopulation(tempChild)) && (tempChild.isBetterThan(population.getWorst()))) {
                    population.add(tempChild);
                    population.remove(population.getWorst());
                } else {

                }
            }
        }
    }


}
