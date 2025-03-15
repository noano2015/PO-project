package hva.app.main;

import hva.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoNewFile extends Command<HotelManager> {
    /** @param manager */
    DoNewFile(HotelManager receiver) {
        super(Label.NEW_FILE, receiver);
    }

    /** @see ist.po.ui.Command#execute() */
    @Override
    protected final void execute() throws CommandException {
        if (_receiver.changed() && Form.confirm(Prompt.saveBeforeExit())) {
            DoSaveFile cmd = new DoSaveFile(_receiver);
            cmd.execute();
        }
        _receiver.reset();
    }
}

