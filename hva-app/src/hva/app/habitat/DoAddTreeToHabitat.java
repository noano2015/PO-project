package hva.app.habitat;

/*import java.text.Normalizer.Form;*/
import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.app.exceptions.DuplicateTreeKeyException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.DuplicateTreeException;
import pt.tecnico.uilib.forms.Form;



class DoAddTreeToHabitat extends Command<Hotel> {

    DoAddTreeToHabitat(Hotel receiver) {
        super(Label.ADD_TREE_TO_HABITAT, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String habitatId = Form.requestString(Prompt.habitatKey());
            String treeId = Form.requestString(Prompt.treeKey());
            String treeName = Form.requestString(Prompt.treeName());
            String treeAge = Form.requestString(Prompt.treeAge());
            String treeDifficulty = Form.requestString(Prompt.treeDifficulty());
            
            String treeType = Form.requestOption(Prompt.treeType(), "CADUCA", "PERENE");

            _display.popup(_receiver.plantNewTreeInHabitat(habitatId, treeId, treeName, treeAge, treeDifficulty, treeType));
        }
        catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getKey());
        }
        catch (DuplicateTreeException e) {
            throw new DuplicateTreeKeyException(e.getKey());
        }
    }


}
