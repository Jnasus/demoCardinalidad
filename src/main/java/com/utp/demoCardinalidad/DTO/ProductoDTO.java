/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.demoCardinalidad.DTO;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jtorr
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO implements Serializable{
    
    private Integer idProducto;
    private String nombre;
    private Double precio;
    private CategoriaDTO categoriaDTO;
    
        
}
