package com.example.demosecurity.controller;

import com.example.demosecurity.model.bd.Usuario;
import com.example.demosecurity.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/login")
    public String login(){
        return "auth/frmLogin";
    }
    @GetMapping("/registrar")
    public String registrar(){
        return "auth/frmRegistroUsuario";
    }
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario,
                                 Model model){
        usuarioService.guardarUsuario(usuario);
        model.addAttribute("registroCorrecto", true);
        return "auth/frmRegistroUsuario";
    }
}
