package com.ecommerce.usuarios.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ClienteDTO> obterListaClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(c -> c.converterParaDTO())
                .collect(Collectors.toList());
    }

    public ClienteDTO obterCliente(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            return cliente.get().converterParaDTO();
        }

        return null;
    }

    @Autowired
    private ClienteRepository clienteRepository;

}
