package hva.search;

import hva.Animal;
import hva.Employee;
import hva.Habitat;
import hva.Hotel;
import hva.VaccineApplication;
import hva.Veterinarian;
import java.io.Serializable;
import java.util.Collection;

public class SearchCommands implements SearchVisitor, Serializable{

    @Override
    public String allAnimalsInHabitat(Habitat habitat) {
        String result = "";
        if(habitat.getAnimals().isEmpty()) return "";
        for(Animal animal : habitat.getAnimals()){
            result += animal.toString() + "\n";
        }
        return result.substring(0, result.length() - 1);
    }

    @Override
    public String allVaccinesOfAnimal(Animal animal) {
        String result = "";
        if(animal.getVaccineApplications().isEmpty()) return "";
        for(VaccineApplication app : animal.getVaccineApplications()){
            result += app.toString() + "\n";
        }
        return result.substring(0, result.length() - 1);
    }

    @Override
    public String allMedicalActs(Veterinarian veterinarian) {
        String result = "";
        if(veterinarian.getVaccineApplications().isEmpty()) return "";
        for(VaccineApplication app : veterinarian.getVaccineApplications()){
            result += app.toString() + "\n";
        }
        return result.substring(0, result.length() - 1);
    }

    @Override
    public String allWrongAplications(Collection<VaccineApplication> wrongVaccinations){
        
        String result = "";
        if(wrongVaccinations.isEmpty()) return "";
        for(VaccineApplication app : wrongVaccinations){
            result += app.toString() + "\n";
        }
        return result.substring(0, result.length() - 1);
    }

    @Override
    public String allAnimalsNotVaccinated(Hotel hotel) {
        Collection<Animal> animals = hotel.getAnimals(); 

        if(animals.isEmpty()) return "";

        String aux = "";
        for(Animal animal : animals){
            if(animal.notVaccinated())
                aux += animal.toString() + "\n";
        }

        if(aux.equals("")) return aux;
        return aux.substring(0,aux.length() -1);
    }

    @Override
    public String allHabitatsWithOutTrees(Hotel hotel) {
        Collection<Habitat> habitats = hotel.getHabitats();

        String aux = "";
        for(Habitat habitat : habitats){
            if(habitat.getTrees().isEmpty())
                aux += habitat.toString() + "\n";
        }
        if(aux.equals(""))return aux;
        return aux.substring(0, aux.length()-1);
    }

    @Override
    public String allHabitatsWithOutAnimals(Collection<Habitat> habitats) {
        if(habitats.isEmpty())return "";
        String aux = "";
        for(Habitat habitat : habitats){
            if(habitat.getAnimals().isEmpty()) aux += habitat.toString() + "\n"; 
        }
        return aux.substring(0, aux.length() -1);
    }

    @Override
    public String allEmployeesWithOutResponsabilities(Collection<Employee> employees) {
        if(employees.isEmpty()) return "";
        String aux = "";
        for(Employee employee : employees){
            if(employee.responsabilitiesIsEmpty()) aux += employee.toString() + "\n";
        }
        return aux.substring(0, aux.length()-1);
    }

    
}
