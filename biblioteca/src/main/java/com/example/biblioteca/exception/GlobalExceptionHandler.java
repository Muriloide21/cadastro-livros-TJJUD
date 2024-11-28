package com.example.biblioteca.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex) {
        ModelAndView mv = new ModelAndView("error/400");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ModelAndView mv = new ModelAndView("error/404");
        mv.addObject("message", "Página não encontrada: " + ex.getRequestURL());
        return mv;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ModelAndView mv = new ModelAndView("error/409");
        mv.addObject("message", "Erro de integridade dos dados: " + ex.getMessage());
        return mv;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        ModelAndView mv = new ModelAndView("error/404");
        mv.addObject("message", "O recurso solicitado não foi encontrado. " + ex.getMessage() );
        return mv;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleGeneralException(Exception ex) {
        ModelAndView mv = new ModelAndView("error/500");
        mv.addObject("message", "Ocorreu um erro inesperado: " + ex.getMessage());
        return mv;
    }
}
