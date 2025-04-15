package br.mackenzie.mackleaps.api.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.*;
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
