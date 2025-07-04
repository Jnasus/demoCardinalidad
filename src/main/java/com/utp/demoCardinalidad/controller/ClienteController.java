package com.utp.demoCardinalidad.controller;

import com.utp.demoCardinalidad.DTO.ClienteDTO;
import com.utp.demoCardinalidad.model.Cliente;
import com.utp.demoCardinalidad.service.IClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    private static final ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteDTO> clienteDTOs = clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(clienteDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Cliente nuevoCliente = clienteService.save(cliente);
        ClienteDTO nuevoClienteDTO = modelMapper.map(nuevoCliente, ClienteDTO.class);
        return new ResponseEntity<>(nuevoClienteDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente.setId(id);
        Cliente clienteActualizado = clienteService.save(cliente);
        ClienteDTO clienteActualizadoDTO = modelMapper.map(clienteActualizado, ClienteDTO.class);
        return new ResponseEntity<>(clienteActualizadoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
