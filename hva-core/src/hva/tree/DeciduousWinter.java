package hva.tree;

class DeciduousWinter extends DeciduousState{

    public DeciduousWinter(Deciduous deciduous){
        super(deciduous, "SEMFOLHAS", 0);
    }

    @Override
    public void nextSeason() {
        getDeciduous().setState(new DeciduousSpring(getDeciduous()));
    } 
    
}
