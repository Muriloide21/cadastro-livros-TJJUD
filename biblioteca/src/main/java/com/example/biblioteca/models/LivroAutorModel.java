package com.example.biblioteca.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Murilo Schirmer
 * Tabela de Relacionamento Livro_Autor
 */
@Entity
@Table(name = "livro_autor")
@IdClass(LivroAutorId.class)
public class LivroAutorModel {

    @Id
    @ManyToOne
    @JoinColumn(name = "livro_codL", insertable = false, updatable = false)
    private LivroModel livro;

    @Id
    @ManyToOne
    @JoinColumn(name = "autor_codAu", insertable = false, updatable = false)
    private AutorModel autor;
    

    public LivroModel getLivro() {
        return livro;
    }

    public void setLivro(LivroModel livro) {
        this.livro = livro;
    }

    public AutorModel getAutor() {
        return autor;
    }

    public void setAutor(AutorModel autor) {
        this.autor = autor;
    }
}
