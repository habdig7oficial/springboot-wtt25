package br.mackenzie.mackleaps.api.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Marvel {

    private static final List<String> HEROES = new ArrayList<>(Arrays.asList(
        "Homem de Ferro", 
        "Capitão América", 
        "Thor", 
        "Hulk", 
        "Viúva Negra", 
        "Homem-Aranha", 
        "Pantera Negra"
    ));
    
    public static List<String> getCharacters() {
        return HEROES;
    }

    public static void addCharacter(String character) {
        HEROES.add(character);
    }

    public static boolean updateCharacter(String oldName, String newName) {
        int index = HEROES.indexOf(oldName);
        if (index != -1) {
            HEROES.set(index, newName);
            return true;
        }
        return false;
    }
    
}
