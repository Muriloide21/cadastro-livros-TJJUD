/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.controllers;

import com.example.biblioteca.models.AssuntoModel;
import com.example.biblioteca.repository.AssuntoRepository;
import com.example.biblioteca.repository.LivroAssuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.biblioteca.models.AssuntoModel;
import com.example.biblioteca.repository.AssuntoRepository;
import com.example.biblioteca.repository.LivroAssuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

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
    public String form(@Valid AssuntoModel assunto, BindingResult bindingResult, Model model) {
        // Se houver erros de validação, retornar o formulário com os erros
        if (bindingResult.hasErrors()) {
            model.addAttribute("assunto", assunto);

            // Crie um mapa para erros, associando cada erro com um campo específico
            Map<String, String> erros = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                erros.put(error.getField(), error.getDefaultMessage());
            }

            model.addAttribute("erros", erros);
            return "assunto/formAssunto";
        }

        assuntoRepository.save(assunto);
        return "redirect:/assuntos";
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
            throw new EmptyResultDataAccessException("Assunto não encontrado para o código: " + codigoAssunto, 1);
        }

        ModelAndView mv = new ModelAndView("assunto/editarAssunto.html");
        mv.addObject("assunto", assunto);
        return mv;
    }

    @RequestMapping(value = "/assuntos/editar", method = RequestMethod.POST)
    public String salvarAlteracoesAssunto(@Valid @ModelAttribute AssuntoModel assunto, BindingResult bindingResult, Model model) {
        // Se houver erros de validação, retornar o formulário com os erros
        if (bindingResult.hasErrors()) {
            model.addAttribute("assunto", assunto);
            // Crie um mapa para erros, associando cada erro com um campo específico
            Map<String, String> erros = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                erros.put(error.getField(), error.getDefaultMessage());
            }

            model.addAttribute("erros", erros);
            return "assunto/editarAssunto";
        }

        assuntoRepository.save(assunto);
        return "redirect:/assuntos";
    }

    @RequestMapping(value = "/assuntos/deletar/{codigoAssunto}", method = RequestMethod.GET)
    public String deletarAssunto(@PathVariable("codigoAssunto") Long codigoAssunto) {
        AssuntoModel assunto = assuntoRepository.findByCodAs(codigoAssunto);

        if (assunto == null) {
            throw new EmptyResultDataAccessException("Assunto não encontrado para o código: " + codigoAssunto, 1);
        }

        livroAssuntoRepository.deleteByAssuntoId(assunto.getCodAs());
        assuntoRepository.delete(assunto);
        return "redirect:/assuntos";
    }
}
