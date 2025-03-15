package hva.app.animal;

import hva.Hotel;
import hva.app.exceptions.DuplicateAnimalKeyException;
import hva.app.exceptions.DuplicateSpecieKeyException;
import hva.app.exceptions.DuplicateSpecieNameException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.DuplicateSpecieByNameException;
import hva.exceptions.DuplicateSpecieException;
import hva.exceptions.UnknownHabitatException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoRegisterAnimal extends Command<Hotel> {

    DoRegisterAnimal(Hotel receiver) {
        super(Label.REGISTER_ANIMAL, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        
        try{
            String specieId, id, name, habitatId;

            id = Form.requestString(Prompt.animalKey());
            name = Form.requestString(Prompt.animalName());

            specieId = Form.requestString(Prompt.speciesKey());

            if(_receiver.getSpecie(specieId) == null){
                String specieName = Form.requestString(Prompt.speciesName());
                _receiver.registerSpecie("Specie", specieId, specieName);
            }
          
            habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());

            _receiver.registerAnimal(new String[]{
                "Animal", id, name, specieId, habitatId
            });
        }
        catch(DuplicateAnimalException e){
            throw new DuplicateAnimalKeyException(e.getKey());
        }
        catch(DuplicateSpecieException e){
            throw new DuplicateSpecieKeyException(e.getKey());
        }
        catch(DuplicateSpecieByNameException e){
            throw new DuplicateSpecieNameException(e.getName());
        }
        catch(UnknownHabitatException e){
            throw new UnknownHabitatKeyException(e.getKey());
        }

    }

}
