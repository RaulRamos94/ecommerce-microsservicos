package com.ecommerce.usuarios.api.model;

import java.time.LocalDate;
import java.time.Period;

import com.ecommerce.usuarios.api.dto.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Embedded
    private Endereco endereco;

    public ClienteDTO converterParaDTO() {
        ClienteDTO dto = new ClienteDTO();

        dto.setId(id);
        dto.setNome(nome);
        dto.setCpf(cpf);
        dto.setEmail(email);
        dto.setTelefone(telefone);

        Period periodo = Period.between(dataNascimento, LocalDate.now());

        dto.setIdade(periodo.getYears());

        dto.setCep(endereco.getCep());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setCidade(endereco.getCidade());
        dto.setUf(endereco.getUf());
        dto.setBairro(endereco.getBairro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());

        return dto;
    }

}
