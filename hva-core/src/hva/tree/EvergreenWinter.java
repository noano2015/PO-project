package hva.tree;

public class EvergreenWinter extends EvergreenState{
    public EvergreenWinter(Evergreen evergreen){
        super(evergreen, "LARGARFOLHAS", 2);
    }
    
    @Override
    public void nextSeason() {
        getEvergreen().setState(new EvergreenSpring(getEvergreen()));
    }
}
