package hva.tree;

class DeciduousSummer extends DeciduousState{

    public DeciduousSummer(Deciduous deciduous){
        super(deciduous,"COMFOLHAS", 2);
    }
    
    @Override
    public void nextSeason() {
        getDeciduous().setState(new DeciduousAutumn(getDeciduous()));
    }
}
