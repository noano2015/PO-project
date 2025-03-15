package hva.app.exceptions;

import java.io.Serial;

import pt.tecnico.uilib.menus.CommandException;

public class DuplicateSpecieKeyException extends CommandException{
    @Serial
    private static final long serialVersionUID = 202410061830L;

    public DuplicateSpecieKeyException(String id) {
        super(MessageExtra.duplicateSpecieKey(id));
    }
}
