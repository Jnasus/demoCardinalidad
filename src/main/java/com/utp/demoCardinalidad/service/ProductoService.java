/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.demoCardinalidad.service;

import com.utp.demoCardinalidad.DTO.CategoriaDTO;
import com.utp.demoCardinalidad.DTO.ProductoDTO;
import com.utp.demoCardinalidad.model.Producto;
import com.utp.demoCardinalidad.repository.ProductoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jtorr
 */
@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> findAll() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        List<Producto> productos = productoRepository.findAll();

        return productos.stream().map(this::convertirAProductoDTO).collect(Collectors.toList());
    }

    @Override
    public Producto findById(Integer id){
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        //Producto producto = productoRepository.findById(id).orElse(null);
        
        return productoRepository.findById(id).orElse(null);
        //return this.convertirAProductoDTO(producto);
    }

    @Override
    public Producto save(Producto producto) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        productoRepository.deleteById(id);
    }

    private ProductoDTO convertirAProductoDTO(Producto producto) {
        CategoriaDTO categoriaDTO = new CategoriaDTO(
                producto.getCategoria().getNombre()
        );

        return new ProductoDTO(
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getPrecio(),
                categoriaDTO
        );
    }

}
