package hva.app.habitat;


import hva.Hotel;
import hva.app.exceptions.DuplicateHabitatKeyException;
import hva.exceptions.DuplicateHabitatException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoRegisterHabitat extends Command<Hotel> {

    DoRegisterHabitat(Hotel receiver) {
        super(Label.REGISTER_HABITAT, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        try{
            String id, name, area;

            id = Form.requestString(Prompt.habitatKey());
            name = Form.requestString(Prompt.habitatName());
            area = Form.requestString(Prompt.habitatArea());
            _receiver.registerHabitat("HABITAT", id, name, area);
        }
        catch(DuplicateHabitatException e){
            throw new DuplicateHabitatKeyException(e.getKey());
        }
    }

}
