package hva.calculator;

import hva.Animal;
import java.io.Serializable;

public class AnimalCalculator implements Calculator, Serializable{
    
    /**
     * the animal witch will be calculated its satisfaction
     */
    private Animal _animal;

    /**
     * Creates an Calculator to calculate independently its satisfaction
     * @param animal
     */
    public AnimalCalculator(Animal animal){
        _animal = animal;
    }

    /**
     * the number of animals witch are the same specie as the animal
     * excluding itself
     * 
     * @return double
     */
    public double sameSpecies(){
        int same = 0;
        for(Animal animal : _animal.getHabitat().getAnimals()){
            if(animal.getSpecie().equals(_animal.getSpecie())){
                same++;
            }
        }
        return (double) (same-1);
    }

    /**
     * all the animals witch are not the same specie as the animal
     * @return double
     */
    public double differentSpecies(){
        return (double) (_animal.getHabitat().getAnimals().size() - sameSpecies()-1);
    }

    /**
     * the area of the habitat in witch it is in
     * @return double
     */
    public double area(){
        return (double) _animal.getHabitat().getArea();
    }

    /**
     * the population of the habitat
     * @return double
     */
    public double population(){
        return (double) _animal.getHabitat().getAnimals().size();
    }

    /**
     * the influence of the habitat
     * @return double
     */
    public double adequacy(){
        return (double) _animal.getHabitat().getInfluence(_animal.getSpecie());
    }

    /**
     * calculates the satisfaction of the animal
     * @return double
     */
    @Override
    public double calculate(){
        return (20.0 + 3.0 * sameSpecies() - 2.0 * differentSpecies() + area() / population() + adequacy());
    }
}
