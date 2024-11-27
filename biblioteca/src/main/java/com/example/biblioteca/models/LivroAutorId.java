package com.example.biblioteca.models;

import java.io.Serializable;
import java.util.Objects;

public class LivroAutorId implements Serializable {
    
    private Long livro; 
    private Long autor; 

    public LivroAutorId() {}

    public LivroAutorId(Long livro, Long autor) {
        this.livro = livro;
        this.autor = autor;
    }

    public Long getLivro() {
        return livro;
    }

    public void setLivro(Long livro) {
        this.livro = livro;
    }

    public Long getAutor() {
        return autor;
    }

    public void setAutor(Long autor) {
        this.autor = autor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro, autor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LivroAutorId other = (LivroAutorId) obj;
        return Objects.equals(livro, other.livro) &&
               Objects.equals(autor, other.autor);
    }
}
