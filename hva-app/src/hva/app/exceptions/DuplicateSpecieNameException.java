package hva.app.exceptions;

import java.io.Serial;

import pt.tecnico.uilib.menus.CommandException;

public class DuplicateSpecieNameException extends CommandException{
    @Serial
    private static final long serialVersionUID = 202410061830L;

    public DuplicateSpecieNameException(String name) {
        super(MessageExtra.duplicateSpecieName(name));
    }
}
