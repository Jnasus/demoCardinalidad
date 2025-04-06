/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.utp.demoCardinalidad.service;

import com.utp.demoCardinalidad.model.Categoria;
import java.util.List;

/**
 *
 * @author jtorr
 */
public interface ICategoriaService {
    List<Categoria> findAll();

    Categoria findById(Integer id);

    Categoria save(Categoria categoria);

    void deleteById(Integer id);

}
