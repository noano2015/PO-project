package hva.app.vaccine;


import hva.Hotel;
import hva.app.exceptions.DuplicateVaccineKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;
import hva.exceptions.DuplicateVaccineException;
import hva.exceptions.UnknownSpecieException;


import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoRegisterVaccine extends Command<Hotel> {

    DoRegisterVaccine(Hotel receiver) {
        super(Label.REGISTER_VACCINE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        try{
            String id = Form.requestString(Prompt.vaccineKey());
            String name = Form.requestString(Prompt.vaccineName());
            String species = Form.requestString(Prompt.listOfSpeciesKeys());

            _receiver.registerVaccine("VACCINE", id, name, species);

        } catch(DuplicateVaccineException e){
            throw new DuplicateVaccineKeyException(e.getKey());
            
        } catch(UnknownSpecieException e){
            throw new UnknownSpeciesKeyException(e.getKey());
        }
    }

}
