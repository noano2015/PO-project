package hva;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Specie implements Serializable{
    private final String _id;
    private final String _name;
    private Map<String,Animal> _animals;
    private Map<String,Veterinarian> _veterinarians;


    public Specie(String id, String name){
        _id = id;
        _name = name;
        _animals = new TreeMap<>();
        _veterinarians = new TreeMap<>();
    }

    /**
     * @brief adds an Animal to _animals
     * @param animal - Animal
     */
    public void addAnimal(Animal animal){
        _animals.put(animal.getId(), animal);
    }

    /**
     * @brief gets the specie's id
     * 
     * @return String - the specie's id
     */
    public String getId(){return _id;}

    /**
     * Gets the specie's name
     * @return String - the specie's name
     */
    public String getName(){return _name;}
    
    public int getNumberOfVeterinarians(){
        return _veterinarians.values().size();
    }

    /**
     * Gets the animals that are on the same specie
     * @return
     */
    public Collection<Animal> getAnimals(){return _animals.values();}
    
    /**
     * Increases the number of veterinarians
     */
    public void addVeterinarian(Veterinarian veterinarian){
        _veterinarians.put(veterinarian.getId(), veterinarian);
    }

    /**
     * Decreases the number of veterinarians
     */
    public void removeVeterinarian(Veterinarian veterinarian){
        _veterinarians.remove(veterinarian.getId());
    }
}
