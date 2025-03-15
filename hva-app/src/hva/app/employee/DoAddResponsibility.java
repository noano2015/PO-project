package hva.app.employee;

import hva.Hotel;
import hva.app.exceptions.NoResponsibilityException;
import hva.app.exceptions.UnknownEmployeeKeyException;
import hva.exceptions.NoResponsabilityFoundException;
import hva.exceptions.UnknownEmployeeException;


import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoAddResponsibility extends Command<Hotel> {

    DoAddResponsibility(Hotel receiver) {
        super(Label.ADD_RESPONSABILITY, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String employeeId = Form.requestString(Prompt.employeeKey());
            String responsabilityId = Form.requestString(Prompt.responsibilityKey());
            _receiver.addResponsability(employeeId, responsabilityId);

            
        } catch(UnknownEmployeeException e){
            throw  new UnknownEmployeeKeyException(e.getKey());
        }   
        catch (NoResponsabilityFoundException e) {
            throw new NoResponsibilityException(e.getEmployeeKey(), e.getResponsabilityKey());
        }
    }

}
