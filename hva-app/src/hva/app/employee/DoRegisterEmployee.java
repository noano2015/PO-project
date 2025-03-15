package hva.app.employee;

import hva.Hotel;
import hva.app.exceptions.DuplicateEmployeeKeyException;
import hva.exceptions.DuplicateEmployeeException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoRegisterEmployee extends Command<Hotel> {

    DoRegisterEmployee(Hotel receiver) {
        super(Label.REGISTER_EMPLOYEE, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        try{
            String id = Form.requestString(Prompt.employeeKey());
            String name = Form.requestString(Prompt.employeeName());

            String type = Form.requestOption(Prompt.employeeType(), "VET", "TRT");
            _receiver.registerEmployee(new String[] {type, id ,name});

        } catch(DuplicateEmployeeException e){
            throw new DuplicateEmployeeKeyException(e.getKey());
        }
    }

}
