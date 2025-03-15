package hva.app.search;

import hva.Hotel;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.UnknownHabitatException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

class DoShowAnimalsInHabitat extends Command<Hotel> {

    DoShowAnimalsInHabitat(Hotel receiver) {
        super(Label.ANIMALS_IN_HABITAT, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        try{

            String habitatKey = Form.requestString(hva.app.habitat.Prompt.habitatKey());
            _display.popup(_receiver.getAllAnimalsOfHabitat(habitatKey));
        }
        catch(UnknownHabitatException e){
            throw new UnknownHabitatKeyException(e.getKey());
        }
    }

}
