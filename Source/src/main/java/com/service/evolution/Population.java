package com.service.evolution;

import com.model.Detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mantixop on 4/22/15.
 */
public class Population {

    private List<Unit> population = new ArrayList<Unit>();
    private static final int maxPopulationSize = 20;
    private List<List<Detail>> db;

    private int maxPrise;
    private float minPower;
    private float minQuality;

    Population(int prise, float power, float quality, List<List<Detail>> db){
        this.db = db;
        minPower = power;
        maxPrise = prise;
        minQuality = quality;
    }

    public Unit selectBestUnitEver(){
        Unit theBest = Unit.getRandomUnit(db);
        for (int i = 0; i < db.get(0).size(); i++){
            for (int j = 0; j < db.get(1).size(); j++){
                for (int k = 0; k < db.get(2).size(); k++){
                    for (int l = 0; l < db.get(3).size(); l++){
                        for (int m = 0; m < db.get(4).size(); m++){
                            Unit temp = new Unit();
                            temp.setDetail(db.get(0).get(i));
                            temp.setDetail(db.get(1).get(j));
                            temp.setDetail(db.get(2).get(k));
                            temp.setDetail(db.get(3).get(l));
                            temp.setDetail(db.get(4).get(m));
                            if (isAlive(temp) && temp.isBetterThan(theBest)){
                                theBest = temp;
                            }
                        }
                    }
                }
            }
        }
        return theBest;
    }

    public boolean selectThePoplation(int maxAttempts){
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
    
    public void remove(Unit unit){
        System.out.println("Remove from population\n" + unit.toString());
        population.remove(unit);
    }

    public void add(Unit unit){
        System.out.println("Add to population\n" + unit.toString());
        population.add(unit);
    }

    public boolean isAlive(Unit unit){
       if (unit.getAveragePower() < minPower || unit.getAverageQuality() < minQuality || unit.getTotalPrise() > maxPrise) {
          // unit.reanimation(db);
       }
       return !(unit.getAveragePower() < minPower || unit.getAverageQuality() < minQuality || unit.getTotalPrise() > maxPrise);
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
