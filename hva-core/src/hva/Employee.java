package hva;

import java.io.Serializable;

public abstract class Employee implements Serializable {
    private final String _id;
    private final String _name;
    private final String _type; 

    public Employee(String id, String name, String type){
        _id = id;
        _name = name;
        _type = type;
    }

    /**
     * Gets the employee's id
     * 
     * @return String -the employee's id
     */
    public String getId(){ return _id;}

    /**
     * Gets the employee's name
     * 
     * @return String - the employee's name
     */
    public String getName(){ return _name;}

    protected abstract String getResponsibilities();
    public abstract double calculateSatisfaction();
    public abstract boolean responsabilitiesIsEmpty();

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder(_type + "|" + _id + "|" + _name);
        String responsibilities = getResponsibilities();
        
        if (responsibilities != null && !responsibilities.isEmpty()) {
            result.append("|").append(responsibilities);
        }
        
        return result.toString();
    }


}
