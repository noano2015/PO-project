package hva.tree;

import java.io.Serializable;

abstract class DeciduousState implements Serializable{

    private int _effort;
    private String _biologicalcycle;
    private Deciduous _deciduous;

    public DeciduousState(Deciduous deciduous, String biologicalcycle, int effort){
        _deciduous = deciduous;
        _biologicalcycle = biologicalcycle;
        _effort = effort;
    }

    public abstract void nextSeason();

    public Deciduous getDeciduous(){return _deciduous;}
    public String getBiologicalCycle(){return _biologicalcycle;}
    public int getSeasonalEffort(){return _effort;}

    @Override
    public boolean equals(Object o){

        if(o instanceof DeciduousState state){
            return this.getClass().getName().equals(state.getClass().getName());
        }
        return false;

    }

}
