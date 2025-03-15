package hva.tree;

public class EvergreenSpring extends EvergreenState{

    public EvergreenSpring(Evergreen evergreen){
        super(evergreen, "GERARFOLHAS", 1);
    }
    
    @Override
    public void nextSeason() {
        getEvergreen().setState(new EvergreenSummer(getEvergreen()));
    }
}
