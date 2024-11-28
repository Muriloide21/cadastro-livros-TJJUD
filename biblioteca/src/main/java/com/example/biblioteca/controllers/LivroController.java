/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.controllers;

import com.example.biblioteca.models.AssuntoModel;
import com.example.biblioteca.models.AutorModel;
import com.example.biblioteca.models.LivroAssuntoModel;
import com.example.biblioteca.models.LivroAutorModel;
import com.example.biblioteca.models.LivroModel;
import com.example.biblioteca.repository.AssuntoRepository;
import com.example.biblioteca.repository.AutorRepository;
import com.example.biblioteca.repository.LivroAssuntoRepository;
import com.example.biblioteca.repository.LivroAutorRepository;
import com.example.biblioteca.repository.LivroRepository;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.FieldError;

/**
 *
 * @author Murilo Schirmer
 */
@Controller
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private AssuntoRepository assuntoRepository;
    
    @Autowired
    private LivroAutorRepository livroAutorRepository;
    
    @Autowired
    private LivroAssuntoRepository livroAssuntoRepository;

   
    
    @RequestMapping(value = "/cadastrarLivro", method = RequestMethod.GET)
    public ModelAndView form() {
        List<AutorModel> autores = (List<AutorModel>) autorRepository.findAll();
        List<AssuntoModel> assuntos = (List<AssuntoModel>) assuntoRepository.findAll();
        
        
        ModelAndView mv = new ModelAndView("livro/formLivro.html");
        mv.addObject("autores", autores);
        mv.addObject("assuntos", assuntos);
        return mv;
    }

    @RequestMapping(value = "/cadastrarLivro", method = RequestMethod.POST)
    public String form(@Valid LivroModel livro, BindingResult bindingResult, Model model) {
        // Se houver erros de validação, retornar o formulário com os erros
        if (bindingResult.hasErrors()) {
            model.addAttribute("livro", livro);
            // Crie um mapa para erros, associando cada erro com um campo específico
            Map<String, String> erros = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                erros.put(error.getField(), error.getDefaultMessage());
            }

            model.addAttribute("erros", erros);
            return "livro/formLivro";
        }

        livroRepository.save(livro);
        return "redirect:/livros";
    }

    @RequestMapping(value = "/livros")
    public ModelAndView listaLivros() {
        ModelAndView mv = new ModelAndView("index.html");
        Iterable<LivroModel> livros = livroRepository.findAll();
        mv.addObject("livros", livros);
        return mv;
    }

    @RequestMapping(value = "/livros/{codigoLivro}")
    public ModelAndView detalhesLivro(@PathVariable("codigoLivro") Long codigoLivro) {
        LivroModel livro = livroRepository.findByCodL(codigoLivro);
        ModelAndView mv = new ModelAndView("detalhesLivro.html");
        mv.addObject("livro", livro);
        return mv;
    }

    @RequestMapping(value = "/livros/editar/{codigoLivro}", method = RequestMethod.GET)
    public ModelAndView editarLivro(@PathVariable("codigoLivro") Long codigoLivro) {
        LivroModel livro = livroRepository.findByCodL(codigoLivro);

        if (livro == null) {
            throw new EmptyResultDataAccessException("Livro não encontrado para o código: " + codigoLivro,1);
        }
        
        List<AutorModel> autores = (List<AutorModel>) autorRepository.findAll();
        List<Long> autoresAssociados = livroAutorRepository.findAutoresByLivroId(codigoLivro);
        
        List<AssuntoModel> assuntos = (List<AssuntoModel>) assuntoRepository.findAll();
        List<Long> assuntosAssociados = livroAssuntoRepository.findAssuntosByLivroId(codigoLivro);

        ModelAndView mv = new ModelAndView("livro/editarLivro.html");
        mv.addObject("livro", livro);
        mv.addObject("autores", autores);
        mv.addObject("assuntos", assuntos);
        mv.addObject("autoresAssociados", autoresAssociados);
        mv.addObject("assuntosAssociados", assuntosAssociados);
        return mv;
    }

    @RequestMapping(value = "/livros/editar", method = RequestMethod.POST)
    public String salvarAlteracoesLivro(@Valid @ModelAttribute LivroModel livro, 
                                        @RequestParam(value = "autoresIds", required = false) List<Long> autoresIds,
                                        @RequestParam(value = "assuntosIds", required = false) List<Long> assuntosIds, 
                                        BindingResult bindingResult, Model model) 
    {
        // Se houver erros de validação, retornar o formulário com os erros
        if (bindingResult.hasErrors()) {
            model.addAttribute("livro", livro);
            // Crie um mapa para erros, associando cada erro com um campo específico
            Map<String, String> erros = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                erros.put(error.getField(), error.getDefaultMessage());
            }

            model.addAttribute("erros", erros);
            return "livro/editarLivro";
        }

        livroRepository.save(livro);

        livroAutorRepository.deleteByLivroId(livro.getCodL());
        
        if (autoresIds != null) {
            List<LivroAutorModel> novasAssociacoes = new ArrayList<>();
            for (Long autorId : autoresIds) {
                AutorModel autor = autorRepository.findByCodAu(autorId);
                LivroAutorModel associacao = new LivroAutorModel();
                associacao.setLivro(livro);
                associacao.setAutor(autor);
                novasAssociacoes.add(associacao);
            }
            livroAutorRepository.saveAll(novasAssociacoes);
        }
        
        livroAssuntoRepository.deleteByLivroId(livro.getCodL());
        
        if (assuntosIds != null) {
            List<LivroAssuntoModel> novasAssociacoes = new ArrayList<>();
            for (Long assuntoId : assuntosIds) {
                AssuntoModel assunto = assuntoRepository.findByCodAs(assuntoId);
                LivroAssuntoModel associacao = new LivroAssuntoModel();
                associacao.setLivro(livro);
                associacao.setAssunto(assunto);
                novasAssociacoes.add(associacao);
            }
            livroAssuntoRepository.saveAll(novasAssociacoes);
        }

        return "redirect:/livros";
    }

    @RequestMapping(value = "/livros/deletar/{codigoLivro}", method = RequestMethod.GET)
    public String deletarLivro(@PathVariable("codigoLivro") Long codigoLivro) {
        LivroModel livro = livroRepository.findByCodL(codigoLivro);

        if (livro == null) {
            throw new EmptyResultDataAccessException("Livro não encontrado para o código: " + codigoLivro,1);
        }

        livroAutorRepository.deleteByLivroId(livro.getCodL());
        livroAssuntoRepository.deleteByLivroId(livro.getCodL());
        livroRepository.delete(livro);
        return "redirect:/livros";
    }
}

