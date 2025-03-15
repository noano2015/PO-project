package hva.exceptions;

import java.io.Serial;

public class DuplicateSpecieByNameException extends Exception {
    
    @Serial
    private static final long serialVersionUID = 202410061830L;

    private final String _name;
    public DuplicateSpecieByNameException(String name){
        _name = name;
    }

    public String getName(){return _name;}
}
