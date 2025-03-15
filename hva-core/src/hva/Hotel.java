package hva;

import hva.exceptions.*;
import hva.search.SearchCommands;
import hva.search.SearchVisitor;
import hva.tree.Deciduous;
import hva.tree.Evergreen;
import hva.tree.Tree;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;



public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    /**
     * A bolean atribute to verify if the hotel has suffered any changes
     */
    private boolean _changed = false;

    /**
     * A map with all the animals of the hotel witch are sorted by their key
     */
    private Map<String, Animal> _animals;

    /**
     * A map with all the the trees of the hotel witch are sorted by their key
     */
    private Map<String, Tree> _trees;
    
    /**
     * A map with all the species of the hotel witch are sorted by their key
     */
    private Map<String, Specie> _speciesByKeys;

    /**
     * A map with all the species of the hotel witch are sorted by their name
     */
    private Map<String, Specie> _speciesByNames;

    /**
     * A map with all the employees of the hotel witch are sorted by their key
     */
    private Map<String, Employee> _employees;

    /**
     * A map with all the habitats of the hotel witch are sorted by their key
     */
    private Map<String, Habitat> _habitats;

    /**
     * A map with all the vaccines of the hotel witch are sorted by their key
     */
    private Map<String, Vaccine> _vaccines;

    /**
     * A list with all the vaccines witch have dealt damage to an animal in the hotel
     */
    private List<VaccineApplication> _wrongVaccineApplications;

    /**
     * A list with all the vaccines witch habe been taken in the hotel
     */
    private List<VaccineApplication> _allVaccineApplications;

    /**
     * Keeps track of the current season of the hotel
     */
    private Season _currentSeason;


    /**
     * Creates an empty hotel and incializes the season at the spring
     */
    public Hotel(){
        _animals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        _speciesByKeys = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        _speciesByNames = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        _trees = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        _employees = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        _habitats = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        _vaccines = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        _wrongVaccineApplications = new ArrayList<>();
        _allVaccineApplications = new ArrayList<>();
        _currentSeason = new Spring(this);
    }

    /*| IMPORTING FILES METHODS |*/

    /**
     * Read text input file and create domain entities.
     *
     * @param filename -name of the text input file
     * @throws ImportFileException - if isn't possible to import the file
     */
    public void importFile(String filename) throws ImportFileException{

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                try{
                    registerEntry(fields);

                }catch(UnrecognizedEntryException | DuplicateAnimalException |
                    DuplicateEmployeeException | DuplicateTreeException |
                    DuplicateHabitatException | DuplicateVaccineException |
                    UnknownHabitatException | UnknownSpecieException |
                    DuplicateSpecieException | DuplicateSpecieByNameException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new ImportFileException(filename, e);
        }
    }

    /*| STATE OF HOTEL |*/

    /**
   * @return changed
   */
    public boolean hasChanged() {
        return _changed;
    }

    /**
    * @param changed
    */
    public void setChanged(boolean changed) {
        _changed = changed;
    }

    /*| METHODS THAT GET ELEMENTS FROM THE HOTEL(GETTERS) |*/

    /**
     * Gets the specie with the specified id
     * 
     * @param id String- Specie's id 
     * @return Specie - the specie with the specified key or null
     */
    public Specie getSpecie(String id){
        return _speciesByKeys.get(id);
    }

    /**
     * Gets the habitat specie with the specified id
     * 
     * @param id String - Habitat's id
     * @return Habitat - the habitat with the specified key
     */
    public Habitat getHabitat(String id){
        return _habitats.get(id);
    }

    public Collection<Habitat> getHabitats(){return _habitats.values();}

    public Collection<Animal> getAnimals(){return _animals.values();}



    /*| METHODS THAT PERMIT THE REGISTRY OF ELEMENTS INTO THE HOTEL |*/

    /** 
      * Converts all animals into a string format
      * 
      * @return String - A string of all animals
     */
    public String allAnimalsToString(){
        if(_animals.isEmpty()) return "" + _animals.values().size();
        String aux = "" + _animals.values().size() + "\n";
        for(Animal animal : _animals.values()){
            aux += animal.toString() + "\n";
        }
        return aux.substring(0, aux.length()-1);
    }

    /**
     * Converts all habitats into a string
     * 
     * @return A string with all information of the habitats
     */
    public String allHabitatsToString(){
        if(_habitats.isEmpty()) return "0";
        String aux =""+ _habitats.values().size() + "\n";
        for(Habitat habitat : _habitats.values()){
            aux += habitat.toString() + "\n";
        }
        return aux.substring(0, aux.length()-1);
    }

    /**
     * Converts all Employees into a string Format
     * 
     * @return A string with all the information of the employess
     */
    public String allEmployeesToString(){
        if(_employees.isEmpty())return "";
        String aux = "";
        for(Employee employee : _employees.values()){
            aux += employee.toString() + "\n";
        }
        return aux.substring(0, aux.length()-1);
    }

    /**
     * Converts all Vaccines into string format
     * 
     * @return A string with all the information of the vaccines
     */
    public String allVaccinesToString(){
        if(_vaccines.isEmpty()) return "";
        String aux = "";
        for(Vaccine vaccine : _vaccines.values()){
            aux += vaccine.toString() + "\n";
        }
        return aux.substring(0, aux.length()-1);
    }

    /*| MÉTODOS QUE REGISTAM ELEMENTOS DO HOTEL|*/

    /**
     * Registers an entry of the file which was imported
     * 
     * @param fields
     * @throws UnrecognizedEntryException if the entry isn't recognized
     * @throws DuplicateAnimalException if there is already a Animal with the introduced key
     * @throws DuplicateEmployeeException if there is already a Employee with the introduced key
     * @throws DuplicateTreeException if there is already a Tree with the introduced key
     * @throws DuplicateHabitatException if there is already a Habitat with the introduced key
     * @throws DuplicateVaccineException if there is already a Vaccine with the introduced key
     * @throws UnknownHabitatException if there isn't an Habitat with the introduced key
     * @throws UnknownSpecieException if there isn't a Specie with the introduced key
     */
    public void registerEntry(String... fields)throws  UnrecognizedEntryException,
        DuplicateAnimalException, DuplicateEmployeeException, DuplicateTreeException,
        DuplicateHabitatException, DuplicateVaccineException, UnknownHabitatException,
        UnknownSpecieException, DuplicateSpecieException, DuplicateSpecieByNameException{

        switch(fields[0]){
        case "ESPÉCIE"-> registerSpecie(fields);
        case "ANIMAL"-> registerAnimal(fields);
        case "HABITAT"-> registerHabitat(fields);
        case "ÁRVORE"-> registerTree(fields);
        case "TRATADOR"-> registerZookeeper(fields);
        case "VETERINÁRIO"-> registerVeterinarian(fields);
        case "VACINA"-> registerVaccine(fields);
        default -> throw  new UnrecognizedEntryException(fields[0]);
        }
    }

     
    /**
     * Registers an animal into the hotel
     * 
     * @param fields 0-"ANIMAL", 1-key, 2-name, 3-specieId, 4- habitatId
     * @throws DuplicateAnimalException if there is an animal with the introduced key
     * @throws UnknownHabitatException if there isn't an habitat with the introduced key
     */
    public void registerAnimal(String... fields) throws DuplicateAnimalException, UnknownHabitatException{

        //Verifies if the animal exist
        if(_animals.get(fields[1]) != null) throw  new DuplicateAnimalException(fields[1]);

        //verifies if the habitat exist
        if(_habitats.get(fields[4]) == null) throw new UnknownHabitatException(fields[4]);

        Animal animal = new Animal(fields[1], fields[2], getSpecie(fields[3]), getHabitat(fields[4]));
        _animals.put(fields[1], animal);
        setChanged(true);
    }

    /**
     * Registers an specie into the hotel
     * 
     * @param fields 0- "SPECIE", 1- id, 2-name
     */
    public void registerSpecie(String... fields)throws DuplicateSpecieException, 
    DuplicateSpecieByNameException{
        if(_speciesByKeys.get(fields[1]) != null) throw  new DuplicateSpecieException(fields[1]);
        if(_speciesByNames.get(fields[2]) != null) throw new DuplicateSpecieByNameException(fields[2]);
        Specie specie = new Specie(fields[1], fields[2]);
        _speciesByKeys.put(fields[1], specie);
        _speciesByNames.put(fields[2], specie);
        setChanged(true);
    }

    /**
     * Registers an habitat into the hotel
     * 
     * @param fields 0- "ANIMAL", 1- id, 2- name, 3- area
     * @throws DuplicateHabitatException if there is an  habitat with the introduced key
     */
    public void registerHabitat(String... fields) throws DuplicateHabitatException {

        //verifies if the habitat already exist
        if(_habitats.get(fields[1]) != null) throw  new DuplicateHabitatException(fields[1]);

        Habitat habitat = new Habitat(fields[1], fields[2], 
                            Integer.parseInt(fields[3]));

        //if the habitat has already a few trees already associated
        if(fields.length > 4){
            String[] treeKeys = fields[4].split(",");

            for(String key : treeKeys){
                Tree tree = _trees.get(key);
                habitat.plantNewTree(tree);
                tree.setHabitat(habitat);
            }
        }
        _habitats.put(fields[1], habitat);
        setChanged(true);
    }


    public void registerEmployee(String...fields) throws DuplicateEmployeeException{
        //verifies if the employee already exists
        if(_employees.get(fields[1])!= null) throw new DuplicateEmployeeException(fields[1]);

        switch (fields[0]) {
            case "VET" -> registerVeterinarian(fields);
            case "TRT" -> registerZookeeper(fields);
        }
    }

    /**
     * Registers a Veterinarian into the hotel
     * 
     * @param fields 0- "VETERINÀRIO", 1- id, 2-name
     * @throws DuplicateEmployeeException if there is a Veterinarian with the introduced key
     */
    public void registerVeterinarian(String... fields)throws DuplicateEmployeeException{

        //verifies if the employee already exists
        if(_employees.get(fields[1])!= null) throw new DuplicateEmployeeException(fields[1]);

        Veterinarian employee = new Veterinarian(fields[1], fields[2]);

        //verifys if the veterinarian comes with a list of responsabilities
        if(fields.length > 3){
            String[] speciesKeys = fields[3].split(",");
            for(var key : speciesKeys ){
                Specie specie = _speciesByKeys.get(key);
                employee.addResponsability(specie);
            }  
        }

        _employees.put(fields[1], employee);
        setChanged(true);
    }

    /**
     * Registers a Zookeeper to the hotel
     * 
     * @param fields 0- "TRATADOR", 1- id, 2- name
     * @throws DuplicateEmployeeException if there is a Veterinarian with the introduced key
     */
    public void registerZookeeper(String... fields) throws DuplicateEmployeeException{

        //verifies if the zookeeper already exist
        if(_employees.get(fields[1]) != null) throw new DuplicateEmployeeException(fields[1]);
        Zookeeper employee = new Zookeeper(fields[1], fields[2]);

        //verifies if it is necessary to add an list of responsabilities
        if(fields.length > 3){
            String[] habitatKeys = fields[3].split(",");
            for(var key : habitatKeys ){
                Habitat habitat= _habitats.get(key);
                employee.addResponsability(habitat);
            }  
        }

        _employees.put(fields[1], employee);
        setChanged(true);
    }

    /**
     * Registers a Tree to the hotel
     * 
     * @param fields 0- "ÁRVORE", 1- id, 2- name, 3-age, 4-cleaningDificulty, 5-type
     * @throws DuplicateTreeException if there is a Tree with the introduced key
     */
    public void registerTree(String... fields)throws DuplicateTreeException{
        if(_trees.get(fields[1]) != null) throw  new DuplicateTreeException(fields[1]);
        switch(fields[5]){
            case "CADUCA" -> registerDeciduous(fields);
            case "PERENE" -> registerEvergreen(fields);

        }
        setChanged(true);
    }

    /**
     * Registers a EverGreen Tree to the hotel
     * 
     * @param fields 0- "ÁRVORE", 1- id, 2- name, 3-age, 4-cleaningDificulty, 5-type
     */
    private void registerEvergreen(String... fields) {
        Evergreen tree = new Evergreen(fields[1], fields[2],
                            Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), 
                            null, this);
        _trees.put(fields[1], tree);
    }

    /**
     * Registers a Deciduous Tree to the hotel
     * 
     * @param fields 0- "ÁRVORE", 1- id, 2- name, 3-age, 4-cleaningDificulty, 5-type
     */
    private void registerDeciduous(String... fields) {
        Deciduous tree = new Deciduous(fields[1], fields[2],
                            Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), 
                            null, this);
        _trees.put(fields[1], tree);
    }

    /**
     * Registers a Vaccine to the hotel
     * 
     * @param fields 0- "VACINA", 1-id, 2-name, 3-species/null
     * @throws DuplicateVaccineException if there is a Vaccine with the introduced key
     * @throws UnknownSpecieException if there isn't a Specie with the introduced keys in species
     */
    public void registerVaccine(String... fields) throws DuplicateVaccineException,
        UnknownSpecieException{
        if(_vaccines.get(fields[1]) != null)
            throw  new DuplicateVaccineException(fields[1]);
        
        //In case the vaccine hasn't got any authorized species to be aplied to
        if(fields.length < 4){
            Vaccine vaccine = new Vaccine(fields[1], fields[2], null);
            _vaccines.put(fields[1], vaccine);
            setChanged(true);
            return;
        }

        //To obtain all the speciesKeys
        String[] speciesKeys = fields[3].split(",");

        //To incialize and store all the values on a map
        TreeMap<String, Specie> species = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for(String key : speciesKeys){
            key = key.strip();
            Specie aux = _speciesByKeys.get(key);
            if(aux == null) throw  new UnknownSpecieException(key);

            species.put(key, aux);
        }

        Vaccine vaccine = new Vaccine(fields[1], fields[2], species);
        _vaccines.put(fields[1], vaccine);
        setChanged(true);
    }

    /*| SPECIFIC METHODS THAT INVOLVE EMPLOYEES |*/
    
    /**
     * Adds a responsability to the employee in question
     * 
     * @param employeeId - the id of the employee
     * @param responsabilityId - the responsability in question
     * @throws NoResponsabilityFoundException - if the responsability is not found
     * @throws UnknownEmployeeException - if there isn't an employee in that id
     */
    public void addResponsability(String employeeId, String responsabilityId)
    throws NoResponsabilityFoundException, UnknownEmployeeException{

        Employee employee = _employees.get(employeeId);

        //verifies if the employee exist
        if(employee == null) throw new UnknownEmployeeException(employeeId);

        //adds an specie if the employee is a veterinarian
        if(employee instanceof Veterinarian veterinarian){
            Specie specie = _speciesByKeys.get(responsabilityId);
            if(specie == null) 
                throw new NoResponsabilityFoundException(employeeId, responsabilityId);

            veterinarian.addResponsability(specie);
        }

        //adds an habitat if the employee is a zookeeper
        if(employee instanceof Zookeeper zookeeper){
            Habitat habitat = _habitats.get(responsabilityId);
            if(habitat == null)
                throw new NoResponsabilityFoundException(employeeId, responsabilityId);

            zookeeper.addResponsability(habitat);
        }
    }

    /**
     * Removes a responsability to the employee in question
     * 
     * @param employeeId - the id of the employee
     * @param responsabilityId - the responsability in question
     * @throws NoResponsabilityFoundException - if the responsability is not found
     * @throws UnknownEmployeeException - if there isn't an employee in that id
     */
    public void removeResponsability(String employeeId, String responsabilityId)
    throws NoResponsabilityFoundException, UnknownEmployeeException{

        Employee employee = _employees.get(employeeId);

        //verifies if the employee exist
        if(employee == null) throw new UnknownEmployeeException(employeeId);

        //removes an specie if the employee is a veterinarian
        if(employee instanceof Veterinarian veterinarian){
            Specie specie = _speciesByKeys.get(responsabilityId);
            if(specie == null)
                throw new NoResponsabilityFoundException(employeeId, responsabilityId);
            veterinarian.removeResponsability(specie);
        }

        //removes an habitat if the employee is a zookeeper
        if(employee instanceof Zookeeper zookeeper){
            Habitat habitat = _habitats.get(responsabilityId);
            if(habitat == null)
                throw new NoResponsabilityFoundException(employeeId, responsabilityId);
            zookeeper.removeResponsability(habitat);
        }
        
    }
    
    /**
     * Calculates the satisfaction in question
     * 
     * @param employeeId - the employee's id
     * @return a String with the number of the calculated Satisfaction
     * @throws UnknownEmployeeException - if the employee doesn't exist
     */
    public String employeeSatisfaction(String employeeId)throws UnknownEmployeeException{
        Employee employee = _employees.get(employeeId);

        //verifies if the employee exist
        if(employee == null) throw new UnknownEmployeeException(employeeId);

        return Long.toString(Math.round(employee.calculateSatisfaction()));
    }

    /*| VACCINES |*/

    /**
     * Vaccinates an animal of the hotel
     * 
     * @param animalKey - the animal's id
     * @param veterinarianKey - the veterinarian's id
     * @param vaccineKey - the vaccine's id
     * @return true if the vaccination has occured without any issues, false if the contrary
     * @throws UnknownAnimalException if the animal doesn't exist
     * @throws UnknownVeterinarianException if the veterinarian doesn't exist
     * @throws UnknownVaccineException if the vaccine doesn't exist
     */
    public boolean  vaccinateAnimalInQuestion(String animalKey, String veterinarianKey, String vaccineKey)
    throws UnknownAnimalException, UnknownVeterinarianException, UnknownVaccineException,
    VeterinarianNotPermitedException{

        // Verify if the animal exists
        Animal animal = _animals.get(animalKey);

        if(animal == null) throw new UnknownAnimalException(animalKey);

        //Verify if the veterinarian exists
        Employee employee = _employees.get(veterinarianKey);

        if(employee == null || !(employee instanceof Veterinarian))
            throw new UnknownVeterinarianException(veterinarianKey);

        //verify if the veterinarian has permision to vaccinate
        Veterinarian veterinarian = (Veterinarian) employee;

        if(veterinarian.getSpecie(animal.getSpecie().getId()) == null) 
            throw new VeterinarianNotPermitedException(veterinarianKey, animal.getSpecie().getId());

        //verify if the vaccine exists
        Vaccine vaccine = _vaccines.get(vaccineKey);
        if(vaccine == null) throw new UnknownVaccineException(vaccineKey);

        //creates the vaccine aplication
        VaccineApplication registo = vaccine.vaccinateAnimal(animal, veterinarian);
        _allVaccineApplications.add(registo);

        //In case any porblem occurs during the vaccination
        if(!registo.getHealthState().equals("NORMAL")){
            _wrongVaccineApplications.add(registo);
            return true;
        }
        return false;
    }

    /**
     * shows all vaccineVaccinations associated to the vaccine
     * 
     * @param vaccineKey - the vaccine's id
     * @return String with all the vaccinations
     * @throws UnknownVaccineException
     */
    public String showVaccinations(){
        String result = "";
        if(_allVaccineApplications.isEmpty()) return result;

        for(VaccineApplication vaccineApplication : _allVaccineApplications)
            result += vaccineApplication.toString()+"\n";
        return result.substring(0, result.length()-1);
    }

    /*| SPECIFIC METHODS THAT INVOLVE ANIMALS |*/

    /**
     * Changes the habitat of the animal
     * 
     * @param animalKey - the animal
     * @param habitatKey - the new habitat
     * @throws UnknownAnimalException - if the animal is not found
     * @throws UnknownHabitatException - if the habitat is not found
     */
    public void transferAnimalToHabitat(String animalKey, String habitatKey) 
    throws UnknownAnimalException, UnknownHabitatException{
        Animal animal = _animals.get(animalKey);

        //verifies if the animal exists
        if(animal == null) throw new UnknownAnimalException(animalKey);

        Habitat habitat = _habitats.get(habitatKey);

        //verifies if the habitat exists
        if(habitat == null) throw new UnknownHabitatException(habitatKey);

        Habitat oldHabitat = animal.getHabitat();

        //removes the animal of the old associated habitat
        if (oldHabitat != null) {oldHabitat.removeAnimal(animal);}

        animal.setHabitat(habitat);
        habitat.addAnimal(animal);
    }

    /**
     * Calculates the satisfaction of the animal in question
     * 
     * @param animalKey - the animal's key
     * @return the String value with the corresponded calculated satisfaction
     * @throws UnknownAnimalException if the animal doesn't exist
     */
    public String animalSatisfaction (String animalKey) 
    throws UnknownAnimalException{
        Animal animal = _animals.get(animalKey);
        if(animal == null) throw new UnknownAnimalException(animalKey);

        return Long.toString(Math.round(animal.calculateSatisfaction()));
    }

    /*| SPECIFIC METHODS THAT INVOLVE HABITATS |*/

    /**
     * Changes the influence of the habitat
     * 
     * @param habitatKey - the habitat
     * @param specieKey - the specie
     * @param influence - the influence
     * @throws UnknownHabitatException - if the habitat is not found
     * @throws UnknownSpecieException - if the specie is not found
     */
    public void changeHabitatInfluence(String habitatKey, String specieKey, String influence)
    throws UnknownHabitatException, UnknownSpecieException{
        Habitat habitat = _habitats.get(habitatKey);
        if(habitat == null) throw new UnknownHabitatException(habitatKey);

        Specie specie = _speciesByKeys.get(specieKey);
        if(specie == null) throw new UnknownSpecieException(specieKey);

        habitat.changeInfluence(specie, influence);

    }

    /**
     * Changes the habitat's area
     * 
     * @param habitatKey - the habitat's key
     * @param area - the new value of the area
     * @throws UnknownHabitatException if the habitat doesn't exist
     */
    public void changeHabitatArea(String habitatKey, String area) 
    throws UnknownHabitatException{
        Habitat habitat = _habitats.get(habitatKey);
        if(habitat == null) throw new UnknownHabitatException(habitatKey);
        habitat.setArea(Integer.parseInt(area));

    }

    /**
     * Plants a new tree to the habitat in question
     * 
     * @param habitatKey - the habitat's key
     * @param treeKey - the tree's key
     * @param treeName - the tree's name
     * @param treeAge - the tree's age
     * @param treeDifficulty - the tree's Difficulty
     * @param treeType - the tree's type
     * @return A string with correponded format of the Tree witch was planted
     * @throws UnknownHabitatException if the habitat doesn't exist
     * @throws DuplicateTreeException if there already exists a tree with the same key
     */
    public String plantNewTreeInHabitat(String habitatKey, String treeKey, String treeName, 
    String treeAge, String treeDifficulty, String treeType)
    throws UnknownHabitatException, DuplicateTreeException{
        Habitat habitat = _habitats.get(habitatKey);
        if(habitat == null) throw new UnknownHabitatException(habitatKey);

        Tree tree = _trees.get(treeKey);
        if(tree != null && tree.getHabitat() != null) throw  new DuplicateTreeException(treeKey);
        if(tree == null) {
            registerTree("ÁRVORE", treeKey, treeName, treeAge,
                            treeDifficulty, treeType);
            tree = _trees.get(treeKey);  
        }
        tree.setHabitat(habitat);
        habitat.plantNewTree(_trees.get(treeKey));
        
        return _trees.get(treeKey).toString();

    }

    /**
     * Shows all the trees in the habitat in question
     * 
     * @param habitatKey - the habitat's key
     * @return a string with all the trees in the habitat
     * @throws UnknownHabitatException if the habitat doesn't exist
     */
    public String showAllTreesInHabitat(String habitatKey) throws UnknownHabitatException{
        Habitat habitat = _habitats.get(habitatKey);
        if(habitat == null) throw new UnknownHabitatException(habitatKey);

        return habitat.showAllTrees();

    }

    /*| SEARCH MENU |*/

    /**
     * Gets all the animals of an habitats and returns all of them in string format
     * 
     * @param habitatKey - the habitat's key
     * @return all animals of a specific habitat in question in String format
     * @throws UnknownHabitatException if the habitat doesn't exist
     */
    public String getAllAnimalsOfHabitat(String habitatKey)throws UnknownHabitatException{
        Habitat habitat = _habitats.get(habitatKey);
        if(habitat == null) throw new UnknownHabitatException(habitatKey);

        return habitat.accept(new SearchCommands());
    }

    /**
     * Gets all the vaccinations that the animal in question has suffered
     * 
     * @param animalKey - the animal's key
     * @return all vaccinations that have affected the animal in string format
     * @throws UnknownAnimalException if the animal doesn't exist
     */
    public String getAllVaccinationsOfAnimal(String animalKey) throws UnknownAnimalException{
        Animal animal = _animals.get(animalKey);
        if(animal == null) throw new UnknownAnimalException(animalKey);

        return animal.accept(new SearchCommands());
    }

    /**
     * Gets all the veterinarians keys
     * 
     * @param veterinarianKey - the veterinarian's key
     * @return a String with all the vaccine aplications done by veterinarian
     * @throws UnknownVeterinarianException if the veterinarian doesn't exist
     */
    public String getAllMedicalActsByVeterinarian(String veterinarianKey) 
    throws UnknownVeterinarianException{
        Employee employee = _employees.get(veterinarianKey);
        if(employee == null || !(employee instanceof Veterinarian))
            throw new UnknownVeterinarianException(veterinarianKey);
        
        Veterinarian veterinarian = (Veterinarian) employee;
        return veterinarian.accept(new SearchCommands());
    }


    /**
     * 
     * @return a String with all the wrong vaccinations
     */
    public String getAllWrongVaccineAplications(){
        return acceptVaccinations(new SearchCommands());
    }

    public String getAllEmployeesWithOutResponsabilities(){
        return acceptEmployees(new SearchCommands());
    }

    public String getAllHabitatsWithOutAnimals(){
        return acceptHabitats(new SearchCommands());
    }


    /**
     * 
     * @param searchVisitor - a vistior witch vists all the vaccines
     * @return returns a String with all the wrong vaccinations
     */
    private String acceptVaccinations(SearchVisitor searchVisitor){
        return searchVisitor.allWrongAplications(_wrongVaccineApplications);
    }

    private String acceptHabitats(SearchVisitor searchVisitor){
        return searchVisitor.allHabitatsWithOutAnimals(_habitats.values());
    }

    private String acceptEmployees(SearchVisitor searchVisitor){
        return searchVisitor.allEmployeesWithOutResponsabilities(_employees.values());
    }

    public String acceptAnimals(SearchVisitor search){ return search.allAnimalsNotVaccinated(this);}

    public String acceptHabitat(SearchVisitor search){ return search.allHabitatsWithOutTrees(this);}

    public String allAnimalsNotVaccinated(){return acceptAnimals(new SearchCommands());}

    public String allHabitatsWithOutTrees(){return acceptHabitat(new SearchCommands());}

    /*| AUXILIAR FUNCTIONS TO BE USED IN THE HOTELMANAGER|*/

    /**
     * 
     * @return the string witch contains the values of the sum of all the
     * satisfaction of all the entities
     */
    public String globalSatisfaction(){

        double result = 0.0;

        for(Animal animal : _animals.values()){
            result += animal.calculateSatisfaction();
        }

        for(Employee employee : _employees.values()){
            result += employee.calculateSatisfaction();
        }

        return Long.toString(Math.round(result));
    }

    /*| HOTEL SEASONS |*/

    /**
     * Advances to the next season in question
     * 
     * @return 0 - String, 1 - Summer , 2 - Autumn, 3 - Winter
     */
    public String advanceSeason(){
        _currentSeason.next();
        for(Tree tree : _trees.values()){
            tree.ageUp();
        }
        return _currentSeason.getIndex();
    }

    /**
     * sets the season of the hotel
     * 
     * @param season - the season in question
     */
    public void setSeason(Season season){_currentSeason = season;}

    /**
     * gets the current season of the hotel
     * 
     * @return the current Season
     */
    public Season getSeason(){return _currentSeason;}

    public abstract class Season implements Serializable{

        /**
         * The hotel witch is affected by the state
         */
        private Hotel _hotel;

        /**
         * the name of the season
         */
        private String _name;

        /**
         * the season's index in String format
         */
        private String _index;

        /**
         * Creates a new season
         * 
         * @param hotel - Hotel
         * @param name - String
         * @param index - String
         */
        public Season(Hotel hotel, String name, String index){
            _hotel = hotel;
            _name = name;
            _index = index;
        }

        /**
         * changes the season to the next season
         */
        public abstract void next();

        /**
         * gets the hotel witch affects
         * 
         * @return Hotel
         */
        public Hotel getHotel(){return _hotel;};

        /**
         * Gets the season's name
         * 
         * @return the season's name
         */
        public String getName(){return _name;}

        /**
         * Gets the index's name
         * 
         * @return the season's index
         */
        public String getIndex(){return _index;}

    }

    public class Spring extends Season{

        public Spring(Hotel hotel){super(hotel, "Spring", "0");}

        @Override
        public void next(){
            getHotel().setSeason(new Summer(getHotel()));
        }

    }
    public class Summer extends Season{

        public Summer(Hotel hotel){super(hotel, "Summer", "1");}
        
        @Override
        public void next(){
            getHotel().setSeason(new Autumn(getHotel()));
        }
    }
    public class Autumn extends Season{

        public Autumn(Hotel hotel){super(hotel, "Autumn", "2");}

        @Override
        public void next(){
            getHotel().setSeason(new Winter(getHotel()));
        }
    }
    public class Winter extends Season{

        public Winter(Hotel hotel){super(hotel, "Winter", "3");}

        @Override
        public void next(){
            getHotel().setSeason(new Spring(getHotel()));
        }

    }
}
