package com.service.evolution;

import com.model.Detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mantixop on 4/21/15.
 */
public class Evolution {

    private static final int maxPopulationSize = 20;
    private static final int maxAttempts = 1000;

    private List<List<Detail>> db;
    private List<Unit> population = new ArrayList<Unit>();

    private int maxPrise;
    private float minPower;
    private float minQuality;

    public Evolution(int prise, float power, float quality, List<List<Detail>> db) {
        this.db = db;
        minPower = power;
        maxPrise = prise;
        minQuality = quality;
    }

    private boolean isAlive(Unit unit){
        return !(unit.getAveragePower() < minPower || unit.getAverageQuality() < minQuality || unit.getTotalPrise() > maxPrise);
    }

    public boolean createPopulation(){
        int attempts = 0;
        while ((attempts < maxAttempts) && (population.size() < maxPopulationSize)){
            Unit rndUnit = Unit.getRandomUnit(db);
            if(isAlive(rndUnit) && (notInPopulation(rndUnit))) {
                population.add(rndUnit);
                System.out.print("ADDED: \n" + rndUnit.toString());
            } else {
                attempts++;
            }
        }
        if (population.size() == 0) {
            System.out.println("Population not created!");
            return false;
        } else {
            System.out.println("Population seccusfully created! Size: " + population.size());
            return true;
        }
    }

    public void makeStep(int count){
        for (int i = 0; i < count; i++) {
            List<Unit> childrens = getAlpha().crossWith(getRandomUnitFromPopulation());
            for (int j = 0; j < 2; j++) {
                Unit tempChild = childrens.get(j);
                if ((isAlive(tempChild)) && (notInPopulation(tempChild)) && (tempChild.isBetterThan(getWorst()))) {
                    System.out.println("On iteration: " + i);
                    System.out.println("Add to population\n" + tempChild.toString());
                    population.add(tempChild);
                    System.out.println("Remove from population\n" + getWorst().toString());
                    population.remove(getWorst());
                } else {

                }
            }
        }
    }

    public Unit getRandomUnitFromPopulation(){
        return population.get((new Random()).nextInt(population.size()));
    }

    public Unit getAlpha(){
        Unit alpha = population.get(0);
        for (Unit unit : population){
            if (unit.isBetterThan(alpha)){
                alpha = unit;
            }
        }
        return alpha;
    }

    public boolean notInPopulation(Unit unit){
        for (Unit u : population){
            if (unit.theSameAs(u)){
                return false;
            }
        }
        return true;
    }

    public Unit getWorst(){
        Unit worst = population.get(0);
        for (Unit unit : population){
            if (!unit.isBetterThan(worst)){
                worst = unit;
            }
        }
        return worst;
    }
}
