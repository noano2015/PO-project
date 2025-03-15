package hva.calculator;
import hva.Habitat;
import hva.Zookeeper;
import hva.tree.Tree;
import java.io.Serializable;

public class ZookeeperCalculator implements Calculator, Serializable {
    
    private Zookeeper _zookeeper;

    public ZookeeperCalculator(Zookeeper zookeeper){_zookeeper = zookeeper;}

    /**
     * Calculates the satisfaction of the zookeeper
     */
    @Override
    public double calculate(){
        return 300 - work();
    }

    /**
     * Determines the amount of work witch the employee is subjected to in all habitats
     * @return
     */
    private double work(){
        double acumulator = 0.0;
        for(Habitat habitat : _zookeeper.getHabitats()){
            acumulator += (workInHabitat(habitat)/((double)habitat.getNumberZookeepers()));
        }
        return  acumulator;
    }

    /**
     * Determines the work of witch the zookeeper is subjected to
     * @param habitat - the habitat in a specific habitat
     * @return double
     */
    private double workInHabitat(Habitat habitat){
        double cleaningDificulty = 0;
        for(Tree tree : habitat.getTrees()){
            cleaningDificulty += (double)tree.seasonalEffort();
        }
        return (double)habitat.getArea() + 3*((double)habitat.getAnimals().size()) + cleaningDificulty;
    }


}
