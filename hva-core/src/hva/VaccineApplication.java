package hva;

import java.io.Serializable;

public class VaccineApplication implements Serializable{

    private Animal _animal;
    private Veterinarian _veterinarian;
    private Vaccine _vaccine;
    private String _state;

    public VaccineApplication(  Animal animal,
                                Veterinarian veterinarian,
                                Vaccine vaccine,
                                String state){                        
        _animal = animal;
        _veterinarian = veterinarian;
        _vaccine = vaccine;
        _state = state;
    }

    public String getHealthState(){return _state;}

    @Override
    public String toString(){
        return  "REGISTO-VACINA|" + _vaccine.getId() + "|"+ _veterinarian.getId() + "|"+
                _animal.getSpecie().getId();
    }
}
