package hva.app.vaccine;

import hva.Hotel;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.app.exceptions.UnknownVaccineKeyException;
import hva.app.exceptions.VeterinarianNotAuthorizedException;
import hva.app.exceptions.UnknownVeterinarianKeyException;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownVeterinarianException;
import hva.exceptions.UnknownVaccineException;
import hva.exceptions.VeterinarianNotPermitedException;


import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

class DoVaccinateAnimal extends Command<Hotel> {

    DoVaccinateAnimal(Hotel receiver) {
        super(Label.VACCINATE_ANIMAL, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        try{
            String vaccineId = Form.requestString(Prompt.vaccineKey());
            String veterinarianId = Form.requestString(hva.app.vaccine.Prompt.veterinarianKey());
            String animalId = Form.requestString(hva.app.animal.Prompt.animalKey());

            boolean error = _receiver.vaccinateAnimalInQuestion(animalId, veterinarianId,vaccineId);

            if(error) _display.popup(Message.wrongVaccine(vaccineId, animalId));

        }catch(UnknownAnimalException e){
            throw new UnknownAnimalKeyException(e.getKey());
        }
        catch(UnknownVeterinarianException e){
            throw new UnknownVeterinarianKeyException(e.getKey());
        }
        catch(VeterinarianNotPermitedException e){
            throw new VeterinarianNotAuthorizedException(e.getVeterinarianKey(), e.getSpecieKey());
        }
        catch(UnknownVaccineException e){
            throw new UnknownVaccineKeyException(e.getKey());
        }
    }

}
