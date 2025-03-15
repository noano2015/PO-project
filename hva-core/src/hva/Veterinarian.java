package hva;
import hva.calculator.VeterinarianCalculator;
import hva.search.SearchVisitor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Veterinarian extends Employee{

    private Map<String, Specie> _responsabilities;
    private List<VaccineApplication> _vaccineApplications;
    private VeterinarianCalculator _veterinarianCalculator;

    public Veterinarian(String id, String name){
        super(id, name, "VET");
        _responsabilities = new TreeMap<>();
        _vaccineApplications = new ArrayList<>();
        _veterinarianCalculator = new VeterinarianCalculator(this);

    }

    /**
     * 
     * @param id
     * @return String
     */
    public Specie getSpecie(String id){return _responsabilities.get(id);}
    
    /**
     * Adds a specie into the responsabilitys of the VET
     * 
     * @param specie - Specie
     */
    public void addResponsability(Specie specie){
        if(_responsabilities.get(specie.getId())!= null)return;
        specie.addVeterinarian(this);
        _responsabilities.put(specie.getId(), specie);
    }

    /**
     * Removes a specie of the responsabilitys of the VET
     * 
     * @param specie -Specie
     */
    public void removeResponsability(Specie specie){ 
        if(_responsabilities.get(specie.getId()) == null)return;
        specie.removeVeterinarian(this);
        _responsabilities.remove(specie.getId());
    }

    @Override
    public double calculateSatisfaction(){
        return _veterinarianCalculator.calculate();
    }
    /**
     * Adds a VaccineApplication into the VET's records
     * 
     * @param vaccineApplication - VaccineAplication
     */
    public void addVaccineApplication(VaccineApplication vaccineApplication){
        _vaccineApplications.add(vaccineApplication);
    }

    public String accept(SearchVisitor searchVisitor){
        return searchVisitor.allMedicalActs(this);
    }
    
    @Override
    protected String getResponsibilities() {
        StringBuilder responsibilities = new StringBuilder();
        
        if (!_responsabilities.isEmpty()) {
            for (var specie : _responsabilities.values()){
                if (responsibilities.length() > 0) {
                    responsibilities.append(",");
                }
                responsibilities.append(specie.getId());
            }
        }
        
        return responsibilities.toString();
    }
    
    /**
     * Gets the species witch this veterinarian is responsable
     * 
     * @return Collection of Species
     */
    public Collection<Specie> getSpecies(){return _responsabilities.values();}

    public Collection<VaccineApplication> getVaccineApplications(){
        return _vaccineApplications;
    }

    @Override
    public boolean responsabilitiesIsEmpty() {
        return _responsabilities.isEmpty();
    }
}
    

