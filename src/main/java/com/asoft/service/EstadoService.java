package com.asoft.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asoft.exception.CodigoNaoExisteException;
import com.asoft.model.Estado;
import com.asoft.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> consultarTodos(){
		List<Estado> lista = estadoRepository.findAll();
		return lista;
	}
	
	public Optional<Estado> consultarId(Long id) {	
		return estadoRepository.findById(id);
	}
	
	public Estado salvar(Estado estado) {
		
		return estadoRepository.save(estado);
	}
	
	public void excluir(Long estadoId) {
		
		try {

			Optional<Estado> estado = estadoRepository.findById(estadoId);
			
			estadoRepository.deleteById( estado.get().getId() );
			
		} catch (NoSuchElementException e) {
			
			throw new CodigoNaoExisteException(String.format("Não existe um Estado com o codigo %d ", estadoId));
		}

		
	}
}
