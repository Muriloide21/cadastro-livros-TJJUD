/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.controllers;

import com.example.biblioteca.models.LivroModel;
import com.example.biblioteca.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Murilo Schirmer
 */
@Controller
public class LivroController {
    
    @Autowired
    private LivroRepository lr;

    @RequestMapping(value="/cadastrarLivro", method=RequestMethod.GET)
    public String form() {
        return "livro/formLivro";
    }
    
    @RequestMapping(value="/cadastrarLivro", method=RequestMethod.POST)
    public String form(LivroModel livro) {
        
        lr.save(livro);
        
        return "livro/formLivro";
    }

//    // Exibir detalhes de um livro específico;
//    @GetMapping("/{id}")
//    public String detalhesLivro(@PathVariable Long id, Model model) {
//        LivroModel livro = lr.findById(id)
//            .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado: " + id));
//        model.addAttribute("livro", livro);
//        return "livro/detalhesLivro";
//    }
//
//    // Deletar um livro
//    @GetMapping("/deletar/{id}")
//    public String deletarLivro(@PathVariable Long id) {
//        LivroModel livro = lr.findById(id)
//            .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado: " + id));
//        lr.delete(livro);
//        return "redirect:/livros";
//    }
//
//    // Listar todos os livros
//    @GetMapping
//    public String listarLivros(Model model) {
//        model.addAttribute("livros", lr.findAll());
//        return "livro/listarLivros";
//    }
    
}
