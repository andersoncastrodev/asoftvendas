package com.asoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asoft.model.Estado;
import com.asoft.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> listaEstados(){
		List<Estado> lista = estadoRepository.findAll();
		return lista;
	}
	
	public Optional<Estado> buscaId(Long id) {	
		return estadoRepository.findById(id);
	}
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
}
