package hva.tree;
import hva.Habitat;
import hva.Hotel;

public class Evergreen extends Tree{

    private EvergreenState _state;
    private EvergreenState _originalState;
    private EvergreenCalculator _calculator;

    public Evergreen(String id, String name, int age,
                     int cleaningDifficulty, Habitat habitat,
                     Hotel hotel){
                        
        super(id, name, age, cleaningDifficulty, habitat);
        
        _calculator = new EvergreenCalculator(this);

        switch (hotel.getSeason().getName()) {
            case "Spring" -> {
                _state = new EvergreenSpring(this);
                _originalState = new EvergreenSpring(this);
            }
            
            case "Summer" -> {
                _state = new EvergreenSummer(this);
                _originalState = new EvergreenSummer(this);
            }

            case "Autumn" -> {
                _state = new EvergreenAutumn(this);
                _originalState = new EvergreenAutumn(this);
            }

            case "Winter" -> {
                _state = new EvergreenWinter(this);
                _originalState = new EvergreenWinter(this);
            }
            default -> {
            }
        }


    }

    void setState(EvergreenState state){ _state = state;}
    public EvergreenState getState(){return _state;}

    @Override
    public void ageUp() {
        _state.nextSeason();
        if(_state.equals(_originalState))
            increaseAge();
    }
    
    @Override
    public String toString(){
        return super.toString() + "PERENE|" + _state.getBiologicalCycle();
    }

    @Override
    public double seasonalEffort() {
        return _calculator.seasonalEffort();
    }

}
