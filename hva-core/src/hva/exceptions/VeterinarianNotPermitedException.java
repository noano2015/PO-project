package hva.exceptions;

import java.io.Serial;

public class VeterinarianNotPermitedException extends Exception{
    @Serial
    private static final long serialVersionUID = 202410061830L;

    private final String _veterinarianKey, _specieKey;
    public VeterinarianNotPermitedException(String veterinarianKey, String specieKey){
        _veterinarianKey = veterinarianKey;
        _specieKey = specieKey;
    }

    public String getVeterinarianKey(){return _veterinarianKey;}
    public String getSpecieKey(){return _specieKey;}
}