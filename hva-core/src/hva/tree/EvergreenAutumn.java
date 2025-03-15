package hva.tree;

class EvergreenAutumn extends EvergreenState {
    public EvergreenAutumn(Evergreen evergreen){
        super(evergreen, "COMFOLHAS", 1);
    }
    
    @Override
    public void nextSeason() {
        getEvergreen().setState(new EvergreenWinter(getEvergreen()));
    }
}
