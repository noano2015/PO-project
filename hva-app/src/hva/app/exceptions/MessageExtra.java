package hva.app.exceptions;

public class MessageExtra {
    static String duplicateSpecieKey(String key){
        return "A espécie '" + key + "' já existe.";
    }

    static String duplicateSpecieName(String name){
        return "A espécie '" + name + "' já existe.";
    }
}
