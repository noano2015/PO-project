package hva.tree;

class DeciduousAutumn extends  DeciduousState{
    
    public DeciduousAutumn(Deciduous deciduous){
        super(deciduous, "LARGARFOLHAS", 5);
    }
    
    @Override
    public void nextSeason() {
        getDeciduous().setState(new DeciduousWinter(getDeciduous()));
    }
}
