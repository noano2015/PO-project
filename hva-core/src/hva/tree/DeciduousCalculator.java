package hva.tree;

import java.io.Serializable;

public class DeciduousCalculator implements SeasonalEffort, Serializable{
    
    private Deciduous _deciduous;

    public DeciduousCalculator(Deciduous deciduous){_deciduous = deciduous;}

    @Override
    public double seasonalEffort(){
        return (double)_deciduous.getCleaningDifficulty() * 
                (double)_deciduous.getState().getSeasonalEffort() *
                Math.log(_deciduous.getAge() + 1);
    }
}
