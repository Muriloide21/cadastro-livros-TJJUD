/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.controllers;

import com.example.biblioteca.models.AutorModel;
import com.example.biblioteca.repository.AutorRepository;
import com.example.biblioteca.repository.LivroAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Murilo Schirmer
 */
@Controller
public class AutorController {
    
    @Autowired
    AutorRepository autorRepository;
    
    @Autowired
    private LivroAutorRepository livroAutorRepository;
    
    @RequestMapping(value = "/cadastrarAutor", method = RequestMethod.GET)
    public String form() {
        return "autor/formAutor";
    }
    
    @RequestMapping(value = "/cadastrarAutor", method = RequestMethod.POST)
    public String form(AutorModel autor) {
        autorRepository.save(autor);
        
        return "autor/formAutor";
    }
    
    @RequestMapping(value = "/autores")
    public ModelAndView listaAutores() {
        ModelAndView mv = new ModelAndView("autor/listaAutores.html");
        Iterable<AutorModel> autores = autorRepository.findAll();
        mv.addObject("autores", autores);
        return mv;
    }

    @RequestMapping(value = "/autores/editar/{codigoAutor}", method = RequestMethod.GET)
    public ModelAndView editarAutor(@PathVariable("codigoAutor") Long codigoAutor) {
        AutorModel autor = autorRepository.findByCodAu(codigoAutor);

        if (autor == null) {
            throw new IllegalArgumentException("Autor não encontrado para o código: " + codigoAutor);
        }

        ModelAndView mv = new ModelAndView("autor/editarAutor.html");
        mv.addObject("autor", autor);
        return mv;
    }

    @RequestMapping(value = "/autores/editar", method = RequestMethod.POST)
    public String salvarAlteracoesAutor(@ModelAttribute AutorModel autor) {
        autorRepository.save(autor);

        return "redirect:/autores";
    }

    @RequestMapping(value = "/autores/deletar/{codigoAutor}", method = RequestMethod.GET)
    public String deletarAutor(@PathVariable("codigoAutor") Long codigoAutor) {
        AutorModel autor = autorRepository.findByCodAu(codigoAutor);

        if (autor == null) {
            throw new IllegalArgumentException("Autor não encontrado para o código: " + codigoAutor);
        }

        livroAutorRepository.deleteByAutorId(autor.getCodAu());
        autorRepository.delete(autor);
        return "redirect:/autores";
    }
    
}
