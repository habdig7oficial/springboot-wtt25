package br.mackenzie.mackleaps.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.mackenzie.mackleaps.api.entity.StarWars;
import br.mackenzie.mackleaps.api.entity.Marvel;

import java.util.List;
import java.util.Arrays;



@RequestMapping("/franchises")
@RestController
public class FranchisesController {

    @GetMapping("/{domain}")
    public List<String> listAssets(@PathVariable String domain) {
        if ("starwars".equalsIgnoreCase(domain)) {
            return StarWars.getCharacters();
        } else if ("marvel".equalsIgnoreCase(domain)) {
            return Marvel.getCharacters();
        } else {
            return Arrays.asList("Asset default for unknown domain");
        }
    }

    @PostMapping("/{domain}")
    public ResponseEntity<String> addAsset(
            @PathVariable String domain,
            @RequestBody String asset) {

        String message;
        if ("starwars".equalsIgnoreCase(domain)) {
            StarWars.addCharacter(asset);
            message = String.format(
                "Character '%s' was successfully created in the '%s' franchise.",
                asset, "Star Wars");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(message);
        } else if ("marvel".equalsIgnoreCase(domain)) {
            Marvel.addCharacter(asset);
            message = String.format(
                "Character '%s' was successfully created in the '%s' franchise.",
                asset, "Marvel");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(message);
        } else {
            message = "Cannot add asset to unknown franchise.";
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(message);
        }
    }

    @PutMapping("/{domain}")
    public ResponseEntity<String> updateAsset(
            @PathVariable String domain,
            @RequestBody List<String> data) {

        String oldName = data.get(0);
        String newName = data.get(1);
        boolean updated = false;

        if ("starwars".equalsIgnoreCase(domain)) {
            updated = StarWars.updateCharacter(oldName, newName);
        } else if ("marvel".equalsIgnoreCase(domain)) {
            updated = Marvel.updateCharacter(oldName, newName);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Franchise not found.");
        }

        if (!updated) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(String.format("Character '%s' not found.", oldName));
        }

        return ResponseEntity
                .ok(String.format("Character '%s' updated to '%s'.", oldName, newName));
    }
}



