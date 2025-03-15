package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

public class DoShowAllHabitatsWithoutAnimals extends Command<Hotel>{

    DoShowAllHabitatsWithoutAnimals(Hotel receiver){
        super(Label.HABITATS_WITHOUT_ANIMALS, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        _display.popup(_receiver.getAllHabitatsWithOutAnimals());
    }
    
}
