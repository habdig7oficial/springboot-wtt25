package br.mackenzie.mackleaps.api.entity;

import java.util.List;
import java.util.Arrays;

public class StarWars {

    // Lista de personagens da franquia Star Wars
    private static final List<String> CHARACTERS = Arrays.asList(
        "Obi-Wan Kenobi", 
        "Qui-Gon Jinn", 
        "Anakin Skywalker", 
        "Mace Windu", 
        "Yoda", 
        "Kit Fisto", 
        "Luminara Unduli", 
        "Ki-Adi-Mundi", 
        "Shaak Ti", 
        "Plo Koon", 
        "Eeth Koth", 
        "Yarael Poof", 
        "Saesee Tiin", 
        "Luke Skywalker"
    );
    
    public static List<String> getCharacters() {
        return CHARACTERS;
    }
}
