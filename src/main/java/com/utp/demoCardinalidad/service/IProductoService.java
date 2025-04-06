/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.utp.demoCardinalidad.service;

import com.utp.demoCardinalidad.DTO.ProductoDTO;
import com.utp.demoCardinalidad.model.Producto;
import java.util.List;


/**
 *
 * @author jtorr
 */
public interface IProductoService {
    List<ProductoDTO> findAll();

    Producto findById(Integer id);

    Producto save(Producto producto);

    void deleteById(Integer id);

}
