package hva.app.employee;

import hva.Hotel;
import hva.app.exceptions.UnknownEmployeeKeyException;
import hva.exceptions.UnknownEmployeeException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoShowSatisfactionOfEmployee extends Command<Hotel> {

    DoShowSatisfactionOfEmployee(Hotel receiver) {
        super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        
        try{
            String id = Form.requestString(Prompt.employeeKey());
            _display.popup(_receiver.employeeSatisfaction(id));
        }
        catch(UnknownEmployeeException e){
            throw new UnknownEmployeeKeyException(e.getKey());
        }
    }

}
