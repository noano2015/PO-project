package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

public class DoShowAllHabitatsWithOutTrees extends Command<Hotel>{
    
    DoShowAllHabitatsWithOutTrees(Hotel receiver){
        super(Label.HABITAS_WITH_OUT_TREES, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        _display.popup(_receiver.allHabitatsWithOutTrees());
    }
}
