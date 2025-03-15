package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownHabitatException;



class DoTransferToHabitat extends Command<Hotel> {

    DoTransferToHabitat(Hotel hotel) {
        super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String animalId = Form.requestString(Prompt.animalKey());
            String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());
            _receiver.transferAnimalToHabitat(animalId, habitatId);

        } catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(e.getKey());
            
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getKey());
        }
    }

}
