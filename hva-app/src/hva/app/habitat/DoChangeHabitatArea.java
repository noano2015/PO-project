package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.UnknownHabitatException;
import pt.tecnico.uilib.forms.Form;


class DoChangeHabitatArea extends Command<Hotel> {

    DoChangeHabitatArea(Hotel receiver) {
        super(Label.CHANGE_HABITAT_AREA, receiver);
        
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String habitatId = Form.requestString(Prompt.habitatKey());
            String area = Form.requestString(Prompt.habitatArea());
            _receiver.changeHabitatArea(habitatId, area);
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getKey());
        }
    }

}
