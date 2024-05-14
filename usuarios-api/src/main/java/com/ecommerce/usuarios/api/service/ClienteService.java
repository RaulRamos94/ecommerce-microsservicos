package com.ecommerce.usuarios.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.usuarios.api.dto.ClienteDTO;
import com.ecommerce.usuarios.api.model.Cliente;
import com.ecommerce.usuarios.api.repository.ClienteRepository;

@Service
public class ClienteService {

    public ClienteDTO salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente).converterParaDTO();
    }

    @Autowired
    private ClienteRepository clienteRepository;
    
}
