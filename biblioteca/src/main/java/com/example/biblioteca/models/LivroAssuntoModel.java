package com.example.biblioteca.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Murilo Schirmer
 * Tabela de Relacionamento Livro_Assunto
 */
@Entity
@Table(name = "livro_assunto")
@IdClass(LivroAssuntoId.class)
public class LivroAssuntoModel {

    @Id
    @ManyToOne
    @JoinColumn(name = "livro_codL", nullable = false)
    private LivroModel livro;

    @Id
    @ManyToOne
    @JoinColumn(name = "assunto_codAs", nullable = false)
    private AssuntoModel assunto;

    
    public LivroModel getLivro() {
        return livro;
    }

    public void setLivro(LivroModel livro) {
        this.livro = livro;
    }

    public AssuntoModel getAssunto() {
        return assunto;
    }

    public void setAssunto(AssuntoModel assunto) {
        this.assunto = assunto;
    }
}

