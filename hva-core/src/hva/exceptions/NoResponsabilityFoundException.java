package hva.exceptions;

import java.io.Serial;

public class NoResponsabilityFoundException extends Exception{
    @Serial
    private static final long serialVersionUID = 202410061830L;

    private final String _employeeKey, _responsabilityKey;

    public NoResponsabilityFoundException(String employeeKey, String responsabilityKey){
        _employeeKey = employeeKey;
        _responsabilityKey = responsabilityKey;
    }

    public String getEmployeeKey(){return _employeeKey;}
    public String getResponsabilityKey(){return _responsabilityKey;}
    
}
