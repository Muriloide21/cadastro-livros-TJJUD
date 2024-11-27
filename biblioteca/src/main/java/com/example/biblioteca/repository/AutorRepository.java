/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.biblioteca.repository;

import com.example.biblioteca.models.AutorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Murilo Schirmer
 */

@Repository
public interface AutorRepository extends CrudRepository<AutorModel,Long> {
    AutorModel findByCodAu(Long codAu);
}
