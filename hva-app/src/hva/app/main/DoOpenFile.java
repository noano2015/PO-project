package hva.app.main;

import java.io.IOException;

import hva.HotelManager;
import hva.exceptions.UnavailableFileException;
import hva.app.exceptions.FileOpenFailedException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoOpenFile extends Command<HotelManager> {
    DoOpenFile(HotelManager receiver) {
        super(Label.OPEN_FILE, receiver);
    }

    /** @see ist.po.ui.Command#execute() */
    @Override
    protected final void execute() throws CommandException {
        try {
            if (_receiver.changed() && Form.confirm(Prompt.saveBeforeExit())) {
                DoSaveFile cmd = new DoSaveFile(_receiver);
                cmd.execute();
              }
            _receiver.load(Form.requestString(Prompt.openFile()));
        } 
        catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
        //Should not happened
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {  
            e.printStackTrace();
        }
    }
}
