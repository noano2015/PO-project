package hva;

import java.io.Serializable;

public class MedicalRecord implements Serializable{
    private final String _healthState;

    public MedicalRecord(String healthState){
        _healthState = healthState;
    }

    @Override
    public String toString(){return _healthState;}
}
