package hva.tree;

class DeciduousSpring extends DeciduousState{
    
    public DeciduousSpring(Deciduous deciduous){
        super(deciduous, "GERARFOLHAS" , 1);
    }
    @Override
    public void nextSeason() {
        getDeciduous().setState(new DeciduousSummer(getDeciduous()));
    }
}
