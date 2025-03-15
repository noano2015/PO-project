package hva.app.search;

import hva.Hotel;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.exceptions.UnknownAnimalException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

class DoShowMedicalActsOnAnimal extends Command<Hotel> {

    DoShowMedicalActsOnAnimal(Hotel receiver) {
        super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String animalKey = Form.requestString(hva.app.animal.Prompt.animalKey());

            _display.popup(_receiver.getAllVaccinationsOfAnimal(animalKey));
            
        } catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(e.getKey());
        }
    }

}
