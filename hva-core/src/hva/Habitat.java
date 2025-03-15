package hva;
import hva.search.SearchVisitor;
import hva.tree.Tree;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Habitat implements Serializable{

    private final int POSITIVE_INFLUENCE = 20;
    private final int NEGATIVE_INFLUENCE = -20;
    private final int NEUTRAL_INFLUENCE = 0;
    private String _id;
    private String _name;
    private int _area;

    /**
     * A map with all the habitat's trees sorted by their key
     */
    private Map<String, Tree> _trees;

    /**
     * A map with all the habitat's animals sorted by their key
     */
    private Map<String, Animal> _animals;

    /**
     * A map with all the influence and the correponded key of the specie in question
     */
    private Map<String, Integer> _influence;

    /**
     * A map with all the zookeepers witch are responsable by the habitat
     */
    private Map<String, Zookeeper> _zookeepers;

    public Habitat(String id, String name, int area){
        _id = id;
        _name = name;
        _area = area;
        _trees = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        _animals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        _influence = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        _zookeepers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    /**
     * @brief changes the habitat influence of a specific specie
     * @param specie - Specie
     * @param influence -int
     */
    public void changeInfluence(Specie specie, String influence){
        if(influence.equals("POS"))_influence.put(specie.getId(), POSITIVE_INFLUENCE);
        if(influence.equals("NEG"))_influence.put(specie.getId(), NEGATIVE_INFLUENCE);
        if(influence.equals("NEU"))_influence.put(specie.getId(), NEUTRAL_INFLUENCE);
    }

    /**
     * @brief gets the influence of a specific specie
     * @param specie - Specie
     * @return int - influence
     */
    public int getInfluence(Specie specie){
        return _influence.get(specie.getId());
    }

    /**
     * Adds a tree to the habitat
     * 
     * @param tree - Tree
     */
    public void plantNewTree(Tree tree){
        _trees.put(tree.getId(), tree);
    }

    /**
     * Adds an animal
     * @param animal
     */
    public void addAnimal(Animal animal){
        _animals.put(animal.getId(), animal);
        _influence.put(animal.getSpecie().getId(), NEUTRAL_INFLUENCE);
    }

    /**
     * Remove an animal
     * @param animal
     */
    public void removeAnimal(Animal animal){
        _animals.remove(animal.getId());
    }

    /**
     * Gets the Habitat's id
     * 
     * @return String - the habitat's id
     */
    public String getId(){return _id;}

    /**
     * Gets all the Habitat's trees
     * 
     * @return Collection of trees of the habitat
     */
    public Collection<Tree> getTrees(){return _trees.values();}

    /**
     * Gets all the Habitat's animals
     * 
     * @return Collection of animals of the habitat
     */
    public Collection<Animal> getAnimals(){return _animals.values();}

    /**
     * Gets the habitat's area
     * 
     * @return int - area
     */
    public int getArea(){return _area;}

    /**
     * Sets the area of the habitat
     * 
     * @param area
     */
    public void setArea(int area){_area = area;}

    /**
     * Gets the number of zookeepers
     * @return
     */
    public int getNumberZookeepers(){return _zookeepers.values().size();}

    /**
     * Increases the number of zookeepers
     */
    public void addZookeeper(Zookeeper zookeeper){
        _zookeepers.put(zookeeper.getId(), zookeeper);
    }

    /**
     * Decreases the number of zookeepers
     */
    public void removeZookeeper(Zookeeper zookeeper){
        _zookeepers.remove(zookeeper.getId());
    }

    /**
     * Accpets a visitor to get all the animals of the habitat
     * @param searchVisitor
     * @return
     */
    public String accept(SearchVisitor searchVisitor){
        return searchVisitor.allAnimalsInHabitat(this);
    }

    /**
     * Converts all the trees of the habitat into a String
     * @return
     */
    public String showAllTrees() {
        
        StringBuilder result = new StringBuilder();
        if(!_trees.isEmpty()){
            for (var tree : _trees.values()){
                result.append(tree.toString());
                result.append("\n");
            }
            result.deleteCharAt(result.length() - 1);
        }
        
        return result.toString();
    }


    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("HABITAT|" + _id + "|" + _name + "|" 
                            + _area + "|" + _trees.size());
        if(!_trees.isEmpty()){
            result.append("\n");
            result.append(this.showAllTrees());
        }
        return result.toString();
    }



}
