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
public class CategoriaDTO implements Serializable{
    
    private String nombre;
    
}
