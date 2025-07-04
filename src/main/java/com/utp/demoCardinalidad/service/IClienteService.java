package com.utp.demoCardinalidad.service;

import com.utp.demoCardinalidad.model.Cliente;
import java.util.List;

public interface IClienteService {
    List<Cliente> findAll();
    Cliente findById(Integer id);
    Cliente save(Cliente cliente);
    void deleteById(Integer id);
}
