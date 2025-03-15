package hva;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

public class Vaccine implements Serializable{
    private final String _id;
    private final String _name;
    private final Map<String, Specie> _species;
    private List<VaccineApplication> _vaccineApplications = new ArrayList<VaccineApplication>();

    /**
     * Creates a new Vaccine
     * @param id
     * @param name
     * @param species
     */
    public Vaccine(String id, String name, TreeMap<String, Specie> species){
        _id = id;
        _name = name;
        _species = species;
    }

    /**
     * Gets the id of the vaccine
     * @return String
     */
    public String getId(){return _id;}
    /**
     * Adds a specie to the authorized species of the vaccines
     * @param specie
     */
    public void addSpecie(Specie specie){
        _species.put(specie.getId(), specie);
    }

    /**
     * Adds a vaccineApllication to the aplications of the vaccine
     * @param vaccineApplication
     */
    public void addVaccineApplication(VaccineApplication vaccineApplication){
        _vaccineApplications.add(vaccineApplication);
    }

    /**
     * Vaccinates an Animal
     * @param animal
     * @param veterinarian
     * @return VaccineApplication
     */
    public VaccineApplication vaccinateAnimal(Animal animal, Veterinarian veterinarian){
        String state;
        if(_species.get(animal.getSpecie().getId()) != null){
            animal.addMedicalRecord(new MedicalRecord("NORMAL"));
            state = "NORMAL";
        }
        else{
            state = damage(animal);
            animal.addMedicalRecord(new MedicalRecord(state));
        }

        VaccineApplication registo = new VaccineApplication(animal, veterinarian, this,state);
        veterinarian.addVaccineApplication(registo);
        _vaccineApplications.add(registo);
        animal.addVaccineApplication(registo);

        return registo;
    }

    /**
     * Determines the type of error of the vaccination
     * @param animal
     * @return String
     */
    private String damage(Animal animal){
        int maxValue = 0;
        for(var specie : _species.values()){
            int newValue = lengthNames(specie,animal) - compareNames(animal, specie);
            if(maxValue < newValue) maxValue = newValue;
        }

        if(maxValue == 0) return "CONFUSÃO";
        if(maxValue>= 1 && maxValue <=4) return "ACIDENTE";
        return "ERRO";
    }

    /**
     * Return the longest name of all species or the length of animal's name if it is bigger
     * @param animal
     * @return int 
     */
    private int lengthNames(Specie specie, Animal animal){
        int maxLength = animal.getSpecie().getName().length();
        int specieLength = specie.getName().length();

        return (maxLength > specieLength)? maxLength : specieLength;
    }

    /**
     * Calculates the number of commmon characteres
     * @param animal
     * @param specie
     * @return int
     */
    private int compareNames(Animal animal, Specie specie){
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();


        
        // Add characters from str1 to set1
        for (char c : animal.getSpecie().getName().toCharArray()) {
            set1.add(c);
        }
        
        // Add characters from str2 to set2
        for (char c : specie.getName().toCharArray()) {
            set2.add(c);
        }
        
        // Find intersection (common characters)
        set1.retainAll(set2);
        
        // Return the number of common characters
        return set1.size();  
    }
    
    @Override
    public String toString(){
        String aux = "VACINA|" + _id + "|" + _name + "|" + _vaccineApplications.size();
        if(_species != null && !_species.isEmpty())
            return aux + "|" + speciesToString();
        return aux;
    }

    /**
     * Combines the collection of species' ids into String format
     * 
     * @return String - all the species' id separated by commas
     */
    private String speciesToString(){
        String aux = "";

        for(String i : _species.keySet()){
            aux += i + ",";
        }
        return aux.substring(0, aux.length() -1);
    }

}