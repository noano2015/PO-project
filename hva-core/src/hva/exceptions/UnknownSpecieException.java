package hva.exceptions;

import java.io.Serial;

public class UnknownSpecieException extends Exception{
    @Serial
    private static final long serialVersionUID = 202410061830L;

    private final String _key;

    public UnknownSpecieException(String key){
        _key = key;
    }

    public String getKey(){return _key;}
}
