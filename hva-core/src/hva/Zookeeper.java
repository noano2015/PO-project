package hva;

import hva.calculator.ZookeeperCalculator;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Zookeeper extends Employee {

    private Map<String, Habitat> _responsabilities;
    private ZookeeperCalculator _zookeeperCalculator;

    public Zookeeper(String id, String name){
        super(id, name, "TRT");
        _responsabilities = new TreeMap<>();
        _zookeeperCalculator = new ZookeeperCalculator(this);
    }

    public Collection<Habitat> getHabitats(){return _responsabilities.values();}

    /**
     * Adds an habitat into the zookeeper responsabilitys
     * 
     * @param habitat - Habitat
     */
    public void addResponsability(Habitat habitat){
        if(_responsabilities.get(habitat.getId()) != null)return;
        habitat.addZookeeper(this);
        _responsabilities.put(habitat.getId(), habitat);
    }

    /**
     * Removes an habitat into the zookeeper responsabilitys
     * 
     * @param habitat - Habitat
     */
    public void removeResponsability(Habitat habitat){
        if(_responsabilities.get(habitat.getId())==null)return; 
        habitat.removeZookeeper(this);
        _responsabilities.remove(habitat.getId());
    }

    @Override
    public double  calculateSatisfaction(){
        return _zookeeperCalculator.calculate();
    }

    
    @Override
    protected String getResponsibilities() {
        StringBuilder responsibilities = new StringBuilder();
        
        if (_responsabilities != null && !_responsabilities.isEmpty()) {
            for (var habitat : _responsabilities.values()){
                if (responsibilities.length() > 0) {
                    responsibilities.append(",");
                }
                responsibilities.append(habitat.getId());
            }
        }
        
        return responsibilities.toString();
    }

    @Override
    public boolean responsabilitiesIsEmpty() {
        return _responsabilities.isEmpty();
    }

}
