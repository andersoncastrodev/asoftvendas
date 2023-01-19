package com.asoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.asoft.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	//Like por nome do estado.
	List<Estado> findByNomeContaining(String nome);
}
