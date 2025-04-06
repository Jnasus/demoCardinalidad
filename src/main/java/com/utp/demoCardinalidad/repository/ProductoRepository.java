/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.utp.demoCardinalidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utp.demoCardinalidad.model.*;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jtorr
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
