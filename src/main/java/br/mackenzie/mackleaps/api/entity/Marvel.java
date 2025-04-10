package br.mackenzie.mackleaps.api.entity;

import java.util.List;
import java.util.Arrays;

public class Marvel {

    // Lista de personagens/heróis da franquia Marvel
    private static final List<String> HEROES = Arrays.asList(
        "Homem de Ferro", 
        "Capitão América", 
        "Thor", 
        "Hulk", 
        "Viúva Negra", 
        "Homem-Aranha", 
        "Pantera Negra"
    );
    
    public static List<String> getCharacters() {
        return HEROES;
    }
}
