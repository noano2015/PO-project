package hva.app.main;

import hva.HotelManager;
import hva.exceptions.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

import java.io.FileNotFoundException;
import java.io.IOException;


class DoSaveFile extends Command<HotelManager> {
    DoSaveFile(HotelManager receiver) {
        super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
    }

    @Override
    protected final void execute() {

        //Tenta guardar o programa no ficheiro com o nome indicado
    	try {
            _receiver.save();
        }
        //Caso não exista o nome do ficheiro associado
        catch (MissingFileAssociationException e) {
            try {
                _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
            } catch (MissingFileAssociationException e1) {
                e1.printStackTrace();
            }catch (IOException e1) {
            e1.printStackTrace();
            }
        } 
        catch (FileNotFoundException e) {
            _display.popup(Message.fileNotFound());
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
