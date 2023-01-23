package com.asoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.asoft.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	List<Cidade> findByNomeContaining(String nome);
	
	@Query( nativeQuery = true, value = "select c.nome as cidade, e.nome as estado from Cidade c inner join Estado e on c.id = e.id ")
	List<String> buscaCidadeEstado();
	
}
