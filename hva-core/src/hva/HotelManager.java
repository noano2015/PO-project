package hva;

import hva.exceptions.*;
import java.io.*;


/**
 * Class that represents the hotel application.
 */
public class HotelManager {

    /**The Hotel Manager */
    private String _filename = "";
    /** This is the current hotel. */
    private Hotel _hotel = new Hotel();

    /**
     * Saves the serialized application's state into the file associated to the current Hotel.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current Hotel does not have a file.
     * @throws IOException if there is some error while serializing the state of the Hotel to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        if (_filename == null || _filename.equals(""))
            throw new MissingFileAssociationException();
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)))) {
            oos.writeObject(_hotel);
            _hotel.setChanged(false);
        }
    }

    /**
     * Saves the serialized application's state into the file associated to the current Hotel.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current Hotel does not have a file.
     * @throws IOException if there is some error while serializing the state of the Hotel to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
        _filename = filename;
        save();
    }

    /**
     * @param filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException, IOException, ClassNotFoundException{
        _filename = filename;
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            _hotel = (Hotel) ois.readObject();
            _hotel.setChanged(false);
        }
    }

    /**
     * 
     * @return A string with the rounded value of the satisfaction of every entity
     */
    public String globalSatisfaction(){
        return _hotel.globalSatisfaction();
    }

    /**
     * Advances season
     */
    public String advanceSeason(){
        return _hotel.advanceSeason();
    }

    /**
     * Read text input file.
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */
    public void importFile(String filename) throws ImportFileException{

        _hotel.importFile(filename);
    }

    /**
    *@return changed?
    */
    public boolean changed() {
        return _hotel.hasChanged();
    }

    /**
     * Gets the created hotel
     * 
     * @return hotel
     */
    public Hotel getHotel(){ return _hotel;}

    /**
    * Reset the Hotel.
    */
    public void reset() {
        _hotel = new Hotel();
        _filename = null;
    }

}
