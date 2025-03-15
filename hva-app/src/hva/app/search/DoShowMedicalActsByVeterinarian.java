package hva.app.search;

import hva.Hotel;
import hva.app.exceptions.UnknownVeterinarianKeyException;
import hva.exceptions.UnknownVeterinarianException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

    DoShowMedicalActsByVeterinarian(Hotel receiver) {
        super(Label.MEDICAL_ACTS_BY_VET, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        try{

            String veterinarianKey = Form.requestString(hva.app.employee.Prompt.employeeKey());
            _display.popup(_receiver.getAllMedicalActsByVeterinarian(veterinarianKey));
        }
        catch(UnknownVeterinarianException e){
            throw new UnknownVeterinarianKeyException(e.getKey());
        }
    }

}
