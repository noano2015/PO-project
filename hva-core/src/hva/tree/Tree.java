package hva.tree;
import hva.Habitat;
import java.io.Serializable;

public abstract class Tree implements Serializable{
    private final String _id;
    private final String _name;
    private int _age;
    private Habitat _habitat;
    private final int _cleaningDifficulty;

    public Tree(String id, String name, int age,
                int cleaningDifficulty, Habitat habitat){
        
        _id = id;
        _name = name;
        _age = age;
        _cleaningDifficulty = cleaningDifficulty;
        _habitat = habitat;
    }

    public abstract double seasonalEffort();

    public abstract void ageUp();
    /**
     * Gets the Tree's id
     * 
     * @return String - the Tree's id
     */
    public String getId(){return _id;}

    /**
     * Sets the habitat of the tree
     * @param habitat
     */
    public void setHabitat(Habitat habitat){_habitat = habitat;}

    public Habitat getHabitat(){return _habitat;}
    /**
     * Gets the Tree's cleaningDificulty
     * 
     * @return int - Tree's cleaning dificulty
     */
    public int getCleaningDifficulty(){return _cleaningDifficulty;}
    public int getAge(){return _age;}
    public void increaseAge(){_age++;}

    @Override
    public String toString(){
        return "ÁRVORE|" + _id + "|" + _name + "|" + _age + "|" + _cleaningDifficulty
        + "|";
    }

    
}
