package com.example.biblioteca.models;

import java.io.Serializable;
import java.util.Objects;

public class LivroAssuntoId implements Serializable {

    private Long livro;  // Nome do campo na entidade LivroAssuntoModel
    private Long assunto; // Nome do campo na entidade LivroAssuntoModel

    // Construtores
    public LivroAssuntoId() {}

    public LivroAssuntoId(Long livro, Long assunto) {
        this.livro = livro;
        this.assunto = assunto;
    }

    // Getters e Setters
    public Long getLivro() {
        return livro;
    }

    public void setLivro(Long livro) {
        this.livro = livro;
    }

    public Long getAssunto() {
        return assunto;
    }

    public void setAssunto(Long assunto) {
        this.assunto = assunto;
    }

    // HashCode e Equals (necessários para JPA)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivroAssuntoId that = (LivroAssuntoId) o;
        return Objects.equals(livro, that.livro) && Objects.equals(assunto, that.assunto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro, assunto);
    }
}
