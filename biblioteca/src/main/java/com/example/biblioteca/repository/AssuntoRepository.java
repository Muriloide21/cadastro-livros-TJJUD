/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.biblioteca.repository;

import com.example.biblioteca.models.AssuntoModel;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Murilo Schirmer
 */
public interface AssuntoRepository extends CrudRepository<AssuntoModel,Long> {
    AssuntoModel findByCodAs(Long codAs);
}
