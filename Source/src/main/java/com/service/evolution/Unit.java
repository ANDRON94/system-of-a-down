package com.service.evolution;

import com.model.Detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mantixop on 4/21/15.
 */
public class Unit {
    public static int[] detCount;
    private List<Detail> details;
    private int totalPrise;
    private float averageQuality;
    private float averagePower;
    private boolean full;
    public static final int detailTypeCount = 5;

    Unit() {
        totalPrise = 0;
        averagePower = 0;
        averageQuality = 0;
        details = new ArrayList<Detail>();
        full = false;
    }

    public static Unit getRandomUnit(List<List<Detail>> db){
        Unit rndUnit = new Unit();
        Random rnd = new Random();
        for(int i = 0; i < detailTypeCount; i++){
            rndUnit.setDetail(db.get(i).get(rnd.nextInt(db.get(i).size())));
        }
        return rndUnit;
    }

    private void recount(){
        int sumQuality = 0;
        int sumPower = 0;
        totalPrise = 0;
        for(int i = 0; i <details.size(); i++ ){
            sumPower += details.get(i).getPower();
            sumQuality += details.get(i).getQuality();
            totalPrise += details.get(i).getPrice();
        }
        averageQuality = (float) sumQuality / details.size();
        averagePower = (float) sumPower / details.size();
        if (details.size() == detailTypeCount){
            full = true;
        } else {
            full = false;
        }
    }

    public List<Unit> crossWith(Unit parent){
        int crossPoint = new Random().nextInt(detailTypeCount);
        Unit firstChild = new Unit();
        Unit secondChild = new Unit();
        for(int i = 0; i < crossPoint; i++){
            firstChild.setDetail(this.getDetails().get(i));
            secondChild.setDetail(parent.getDetails().get(i));
        }
        for (int i = crossPoint; i < detailTypeCount; i++){
            secondChild.setDetail(this.getDetails().get(i));
            firstChild.setDetail(parent.getDetails().get(i));
        }
        List<Unit> rez = new ArrayList<Unit>(2);
        rez.add(0, firstChild);
        rez.add(1, secondChild);
        return rez;
    }

    @Override
    public String toString() {
        String str = "--------------------" + (full ? "Full" : "ТotFull") + "----------------------\n";
        for (int i = 0; i < details.size(); i++){
            str += (i+1) + ")" + details.get(i).toString() + "\n";
        }
        str += "AverQuality: " + averageQuality + " AverPower: " + averagePower + " total Prise: " + totalPrise + "\n";
        str += "----------------------------------------------------\n";
        return str;
    }

    public void setDetail(Detail detail){
        details.add(detail.getDetailType().getId() - 1, detail);
        recount();
    }

    public boolean theSameAs(Unit unit){
        for (int i = 0; i < detailTypeCount; i++){
            if (this.details.get(i) != unit.details.get(i))
                return false;
        }
        return true;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public boolean isBetterThan(Unit anotherUnit){
        if(this.getTotalPrise() < anotherUnit.getTotalPrise()) {
            return true;
        } else if((this.getTotalPrise() == anotherUnit.getTotalPrise()) && (this.getAveragePower() + this.getAverageQuality() > anotherUnit.getAveragePower() + anotherUnit.getAverageQuality())) {
            return true;
        } else {
            return false;
        }
    }

    public int getTotalPrise() {
        return totalPrise;
    }

    public float getAverageQuality() {
        return averageQuality;
    }

    public float getAveragePower() {
        return averagePower;
    }

    public boolean isFull() {
        return full;
    }
}
