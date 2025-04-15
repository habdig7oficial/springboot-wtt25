package br.mackenzie.mackleaps.api.controller;

import org.springframework.web.bind.annotation.*;


//@RequestMapping("/")
@RestController
public class Index {
    @GetMapping("/")
    public String[] index(){
        String paths[] = {"/franchises/starwars", "/franchises/marvel"};
        return paths;
    }
}
