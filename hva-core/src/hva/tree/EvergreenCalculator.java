package hva.tree;

import java.io.Serializable;

public class EvergreenCalculator implements SeasonalEffort, Serializable{
    
    private Evergreen _evergreen;

    public EvergreenCalculator(Evergreen evergreen){_evergreen = evergreen;}

    @Override
    public double seasonalEffort(){
        return (double)_evergreen.getCleaningDifficulty() * 
                (double)_evergreen.getState().getSeasonalEffort() *
                Math.log(_evergreen.getAge() + 1);
    }
}
