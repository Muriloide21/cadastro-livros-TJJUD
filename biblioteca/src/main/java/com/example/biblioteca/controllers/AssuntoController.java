/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.controllers;

import com.example.biblioteca.models.AssuntoModel;
import com.example.biblioteca.repository.AssuntoRepository;
import com.example.biblioteca.repository.LivroAssuntoRepository;
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
public class AssuntoController {
    @Autowired
    AssuntoRepository assuntoRepository;
    
    @Autowired
    private LivroAssuntoRepository livroAssuntoRepository;
    
    @RequestMapping(value = "/cadastrarAssunto", method = RequestMethod.GET)
    public String form() {
        return "assunto/formAssunto";
    }
    
    @RequestMapping(value = "/cadastrarAssunto", method = RequestMethod.POST)
    public String form(AssuntoModel assunto) {
        assuntoRepository.save(assunto);
        
        return "assunto/formAssunto";
    }
    
    @RequestMapping(value = "/assuntos")
    public ModelAndView listaAssuntos() {
        ModelAndView mv = new ModelAndView("assunto/listaAssuntos.html");
        Iterable<AssuntoModel> assuntos = assuntoRepository.findAll();
        mv.addObject("assuntos", assuntos);
        return mv;
    }

    @RequestMapping(value = "/assuntos/editar/{codigoAssunto}", method = RequestMethod.GET)
    public ModelAndView editarAssunto(@PathVariable("codigoAssunto") Long codigoAssunto) {
        AssuntoModel assunto = assuntoRepository.findByCodAs(codigoAssunto);

        if (assunto == null) {
            throw new IllegalArgumentException("Assunto não encontrado para o código: " + codigoAssunto);
        }

        ModelAndView mv = new ModelAndView("autor/editarAssunto.html");
        mv.addObject("assunto", assunto);
        return mv;
    }

    @RequestMapping(value = "/assuntos/editar", method = RequestMethod.POST)
    public String salvarAlteracoesAssunto(@ModelAttribute AssuntoModel assunto) {
        assuntoRepository.save(assunto);

        return "redirect:/assuntos";
    }

    @RequestMapping(value = "/assuntos/deletar/{codigoAssunto}", method = RequestMethod.GET)
    public String deletarAssunto(@PathVariable("codigoAssunto") Long codigoAssunto) {
        AssuntoModel assunto = assuntoRepository.findByCodAs(codigoAssunto);

        if (assunto == null) {
            throw new IllegalArgumentException("Assunto não encontrado para o código: " + codigoAssunto);
        }

        livroAssuntoRepository.deleteByAssuntoId(assunto.getCodAs());
        assuntoRepository.delete(assunto);
        return "redirect:/assuntos";
    }
}
