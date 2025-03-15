package hva;

import hva.calculator.AnimalCalculator;
import hva.search.SearchVisitor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Animal implements Serializable{
    private String _id;
    private String _name;
    private Specie _specie;
    private List<MedicalRecord> _medicalRecord;
    private Habitat _habitat;
    private AnimalCalculator _animalCalculator;
    private List<VaccineApplication> _vaccineApplications;


    public Animal(String id, String name,
                Specie specie, Habitat habitat){
        _id = id;
        _name = name;
        _specie = specie;
        _habitat = habitat;
        _medicalRecord = new ArrayList<>();
        _specie.addAnimal(this);
        _habitat.addAnimal(this);
        _animalCalculator = new AnimalCalculator(this);
        _vaccineApplications = new ArrayList<>();
    }

    /**
     * Gets the id of the animal
     * 
     * @return String - the id of the animal
     */
    public String getId(){return _id;}

    /**
     * Gets the animal's specie
     * @return Specie
     */
    public Specie getSpecie(){return _specie;}

    /**
     * Gets the animal's habitat
     * @return Habitat
     */
    public Habitat getHabitat(){return _habitat;}

    public boolean notVaccinated(){return _vaccineApplications.isEmpty();}

    /**
     * 
     * @return Collection of all the vaccines that the animal has taken
     */
    public Collection<VaccineApplication> getVaccineApplications(){
        return _vaccineApplications;
    }

    /**
     * Calculates the satisfaction of the animal
     * 
     * @return Double - the satisfaction of the animal
     */
    public double calculateSatisfaction(){ 
        return _animalCalculator.calculate();
    }

    /**
     * sets the animal's habitat
     * @param habitat
     */
    public void setHabitat(Habitat habitat){
        _habitat = habitat;
    }

    /**
     * Adds a medical record to the animal in question
     * @param medicalRecord
     */
    public void addMedicalRecord(MedicalRecord medicalRecord){
        _medicalRecord.add(medicalRecord);
    }

    /**
     * Adds a vaccine aplication to the list of all the vaccination that animal
     * has taken
     * @param vaccineApplication
     */
    public void addVaccineApplication(VaccineApplication vaccineApplication){
        _vaccineApplications.add(vaccineApplication);
    }

    /**
     * accepts an visitor to get all it's vaccines
     * @param searchVisitor
     * @return String
     */
    public String accept(SearchVisitor searchVisitor){
        return searchVisitor.allVaccinesOfAnimal(this);
    }
    
    @Override
    public String toString(){
        return "ANIMAL|" + _id + "|"+ _name + "|" +_specie.getId()+ "|"+
            medicalRecordToString()+ "|" + _habitat.getId();
    }

    /**
     * Converts the medical Record to an String
     * 
     * @return String - all medical records in one string
     */
    private String medicalRecordToString(){
        if(_medicalRecord.isEmpty()) return "VOID";
        String aux = "";
        aux += _medicalRecord.get(0).toString();
        if(_medicalRecord.size() > 1){
            for(int i = 1; i < _medicalRecord.size(); i++){
                aux += "," + _medicalRecord.get(i).toString();
            }
        }
        return aux;
    }

}
