package hva.exceptions;

import java.io.Serial;

public class DuplicateEmployeeException extends Exception{
    @Serial
    private static final long serialVersionUID = 202410061830L;

    private final String _key;

    public DuplicateEmployeeException(String key){
        _key = key;
    }

    public String getKey(){return _key;}
}