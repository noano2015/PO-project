package hva.tree;

public class EvergreenSummer extends EvergreenState{
    public EvergreenSummer(Evergreen evergreen){
        super(evergreen, "COMFOLHAS", 1);
    }
    
    @Override
    public void nextSeason() {
        getEvergreen().setState(new EvergreenAutumn(getEvergreen()));
    }
}
