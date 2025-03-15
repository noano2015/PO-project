package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpecieException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;



class DoChangeHabitatInfluence extends Command<Hotel> {

    DoChangeHabitatInfluence(Hotel receiver) {
        super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
        
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String habitatId = Form.requestString(Prompt.habitatKey());
            String specieId = Form.requestString(hva.app.animal.Prompt.speciesKey());
            String influence = Form.requestOption(Prompt.habitatInfluence(),
                                                 "POS", "NEG", "NEU");
            _receiver.changeHabitatInfluence(habitatId, specieId, influence);

        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getKey());
            
        } catch (UnknownSpecieException e) {
            throw new UnknownSpeciesKeyException(e.getKey());
            
        }
    }

}
