package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

public class DoShowAllUnvaccinatedAnimals extends Command<Hotel>{

    DoShowAllUnvaccinatedAnimals(Hotel receiver) {
        super(Label.ANIMALS_NOT_VACCINATED, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        _display.popup(_receiver.allAnimalsNotVaccinated());
    }
    
}
