package hva.tree;
import hva.Habitat;
import hva.Hotel;

public class Deciduous extends Tree{
    
    private DeciduousState _state;
    private DeciduousState _originalState;
    private DeciduousCalculator _calculator;
    
    public Deciduous(String id, String name, int age, 
                     int cleaningDifficulty, Habitat habitat,
                     Hotel hotel){
                        
        super(id, name, age, cleaningDifficulty, habitat);

        _calculator = new DeciduousCalculator(this);
        
        switch (hotel.getSeason().getName()) {
            case "Spring" -> {
                _state = new DeciduousSpring(this);
                _originalState = new DeciduousSpring(this);
            }
            
            case "Summer" -> {
                _state = new DeciduousSummer(this);
                _originalState = new DeciduousSummer(this);
            }

            case "Autumn" -> {
                _state = new DeciduousAutumn(this);
                _originalState = new DeciduousAutumn(this);
            }

            case "Winter" -> {
                _state = new DeciduousWinter(this);
                _originalState = new DeciduousWinter(this);
            }
            default -> {
            }
        }
    }

    void setState(DeciduousState state){ _state = state;}
    public DeciduousState getState(){return _state;}

    @Override
    public void ageUp(){
        _state.nextSeason();
        if(_state.equals(_originalState))
            increaseAge();
    }
    

    @Override
    public String toString(){
        return super.toString() + "CADUCA|" + _state.getBiologicalCycle();
    }

    @Override
    public double seasonalEffort() {
        return _calculator.seasonalEffort();
    }
}
