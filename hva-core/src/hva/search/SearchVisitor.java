package hva.search;

import hva.Animal;
import hva.Employee;
import hva.Habitat;
import hva.Hotel;
import hva.VaccineApplication;
import hva.Veterinarian;
import java.util.Collection;

public interface  SearchVisitor {
    
    public String allAnimalsInHabitat(Habitat habitat);
    public String allVaccinesOfAnimal(Animal animal);
    public String allMedicalActs(Veterinarian veterinarian);
    public String allWrongAplications(Collection<VaccineApplication> wrongApplications);
    public String allAnimalsNotVaccinated(Hotel hotel);
    public String allHabitatsWithOutTrees(Hotel hotel);
    public String allHabitatsWithOutAnimals(Collection<Habitat> habitats);
    public String allEmployeesWithOutResponsabilities(Collection<Employee> employees);
}
