/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.biblioteca.repository;

import com.example.biblioteca.models.LivroAutorId;
import com.example.biblioteca.models.LivroAutorModel;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Murilo Schirmer
 */
public interface LivroAutorRepository extends CrudRepository<LivroAutorModel, LivroAutorId> {
    List<LivroAutorModel> findByLivroCodL(Long livroCodL);

    List<LivroAutorModel> findByAutorCodAu(Long autorCodAu);
    
    @Query("SELECT la.autor.codAu FROM LivroAutorModel la WHERE la.livro.codL = :livroId")
    List<Long> findAutoresByLivroId(@Param("livroId") Long livroId);

    @Transactional
    @Modifying
    @Query("DELETE FROM LivroAutorModel la WHERE la.livro.codL = :livroId")
    void deleteByLivroId(@Param("livroId") Long livroId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM LivroAutorModel la WHERE la.autor.codAu = :autorId")
    void deleteByAutorId(@Param("autorId") Long autorId);
}
