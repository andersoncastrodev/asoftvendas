package com.asoft.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asoft.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>  {

	List<Cliente> findByNomeContaining(String nome);
	
	Cliente findByDataNascimento(LocalDate dataNasc);
	
	Cliente findByTelefone(String telefone);
}
