package br.mackenzie.mackleaps.api.entity;

import java.util.ArrayList;
import java.util.List;

public class StarWars {
    // Utiliza uma lista mutável para armazenar os personagens
    private static final List<String> characters = new ArrayList<>();

    static {
        characters.add("Luke Skywalker");
        characters.add("Leia Organa");
        characters.add("Han Solo");
        characters.add("Rey");
        characters.add("Finn");
        characters.add("Poe Dameron");
        characters.add("Kylo Ren");
        characters.add("BB-8");
        characters.add("C-3PO");
        characters.add("R2-D2");
        characters.add("Obi-Wan Kenobi");
        characters.add("Yoda");
        characters.add("Darth Vader");
        characters.add("Emperor Palpatine");
    }
    
    public static List<String> getCharacters() {
        return characters;
    }

    public static void addCharacter(String character) {
        characters.add(character);
    }

    public static boolean updateCharacter(String oldName, String newName) {
        int index = characters.indexOf(oldName);
        if (index != -1) {
            characters.set(index, newName);
            return true;
        }
        return false;
    }
    
}
