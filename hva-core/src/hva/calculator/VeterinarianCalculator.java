package hva.calculator;
import hva.Specie;
import hva.Veterinarian;
import java.io.Serializable;

public class VeterinarianCalculator implements Calculator, Serializable {
    
    /**
     * the veterinarian witch will be calculated the satisfaction
     */
    private Veterinarian _veterinarian;

    public VeterinarianCalculator(Veterinarian veterinarian){
        _veterinarian = veterinarian;
    }

    /**
     * Calculates the satisfaction of the veterinarian
     * @return double
     */
    @Override
    public  double calculate(){
        return 20 - work();
    }

    /**
     * Determines the amount of work that the vet is subjected to
     * @return double
     */
    private double work(){
        double acumulator = 0.0;

        for(Specie specie : _veterinarian.getSpecies()){
            acumulator += (specie.getAnimals().size()/((double)specie.getNumberOfVeterinarians()));
        }
        return acumulator;
    }
}
