package com.service.evolution;

import com.model.Detail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mantixop on 4/21/15.
 */
public class Evolution {

    private static final int detailTypeCount = 5;
    private static final int maxPopulationSize = 5;
    private static final int maxAttempts = 100;

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
            if(this.isAlive(rndUnit)) {
                population.add(rndUnit);
                System.out.print("ADDED: \n" + rndUnit.toString());
            } else {
                attempts++;
            }
        }
        if (population.size() == 0)
            return false;
        else
            return true;
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
}
