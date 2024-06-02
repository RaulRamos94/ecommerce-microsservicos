package com.ecommerce.usuarios.api.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.compras.client.usuario.ClienteDTO;
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
public class Cliente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    private boolean administrador;

    @Column(nullable = false, name = "usuario_externo")
    private boolean usuarioExterno;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.administrador) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        if (this.usuarioExterno) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
