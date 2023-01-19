package com.asoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.asoft.exception.CodigoNaoExisteException;
import com.asoft.model.Cidade;
import com.asoft.model.Estado;
import com.asoft.repository.CidadeRepository;
import com.asoft.repository.EstadoRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Cidade> consultarTodas(){	
		return cidadeRepository.findAll();
	}
	
	public Optional<Cidade> consultarId(Long cidadeId) {
		return cidadeRepository.findById(cidadeId);
	}
	
	public List<Cidade> consultarLikeNome(String nome){
		
		return cidadeRepository.findByNomeContaining(nome);
	}
	
	public Cidade salvar(Cidade cidade){
		
		//Pegar o codigo que vem lá do view no controller.
		Long estadoId = cidade.getEstado().getId();
	
		Estado estado = estadoRepository.findById(estadoId)
				.orElseThrow( ()-> new CodigoNaoExisteException(String.format("Não existe um estado de codigo: %d", estadoId) ));
	
//		Optional<Estado> estado = estadoRepository.findById(estadoId);
//		
//		if(estado.isEmpty()) {
//			
//			throw new CodigoNaoExisteException(String.format("Não existe um estado de codigo: %d", estadoId));
//		
//		}
//
//		cidade.setEstado(estado.get());
	
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
	}
	
	//Aqui vai ter uma chave estrangueira.
	public void excluir(Long cidadeId) {
		
		try {
			
			cidadeRepository.deleteById(cidadeId);
			
		}catch (EmptyResultDataAccessException e) {
			throw new CodigoNaoExisteException(String.format("Não existe uma cidade para o codigo: %d", cidadeId));
		}
		
		
	}
	
	
}
