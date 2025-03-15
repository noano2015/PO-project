package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

public class DoShowAllEmployeesWithoutResponsabilities extends Command<Hotel> {
    
    DoShowAllEmployeesWithoutResponsabilities(Hotel receiver){
        super(Label.EMPLOYEES_WITHOUT_RESPONSABILITIES, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        _display.popup(_receiver.getAllEmployeesWithOutResponsabilities());
    }
}
