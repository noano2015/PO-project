package hva.tree;

import java.io.Serializable;

abstract class EvergreenState implements Serializable{
    private int _effort;
    private String _biologicalcycle;
    private Evergreen _evergreen;

    public EvergreenState(Evergreen evergreen, String biologicalcycle, int effort){
        _evergreen = evergreen;
        _biologicalcycle = biologicalcycle;
        _effort = effort;
    }

    public abstract void nextSeason();

    public Evergreen getEvergreen(){return _evergreen;}
    public String getBiologicalCycle(){return _biologicalcycle;}
    public int getSeasonalEffort(){return _effort;}

    @Override
    public boolean equals(Object o){

        if(o instanceof EvergreenState state){
            return this.getClass().getName().equals(state.getClass().getName());
        }
        return false;

    }
}
