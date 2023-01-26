package com.asoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asoft.exception.CodigoNaoExisteException;
import com.asoft.model.Cidade;
import com.asoft.model.Endereco;
import com.asoft.repository.CidadeRepository;
import com.asoft.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Endereco> consultarTodos(){
		
		return enderecoRepository.findAll();
	}
	
	public Optional<Endereco> consultarId(Long enderecoId) {
	
		return enderecoRepository.findById(enderecoId);
	}
	
	public List<Endereco> consultaLikeNome(String lograduro){
		
		return enderecoRepository.findByLograduroContaining(lograduro);
	}
	
	public Endereco salvar(Endereco endereco) {
		
		Long cidadeId = endereco.getCidade().getId();
		
		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
		
		if(cidade.isEmpty()) {
			throw new CodigoNaoExisteException(String.format("Não existe uma cidade de codigo: %d", cidadeId) );
		}
		
		endereco.setCidade( cidade.get() );
		
		return enderecoRepository.save(endereco);
	}
	
	public void excluir(Long EnderecoId) {
		
		Optional<Endereco> endereco = enderecoRepository.findById(EnderecoId);
		
		if (endereco.isEmpty()) {
			throw new CodigoNaoExisteException(String.format("Não existe uma cidade de codigo: %d", EnderecoId));
		}
		enderecoRepository.deleteById(EnderecoId);
		
	}
	
	
	
}
