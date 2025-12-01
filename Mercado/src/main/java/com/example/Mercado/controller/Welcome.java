package com.example.Mercado.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Welcome {
    @GetMapping("/")
    public String mensagem() {
        return "Bem-vindo";
    }
    
    
}
