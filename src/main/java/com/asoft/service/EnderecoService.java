package com.asoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	public Endereco salvar(Endereco endereco) {
		
		Long cidadeId = endereco.getCidade().getId();
		
		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
		
		endereco.setCidade( cidade.get() );
		
		return enderecoRepository.save(endereco);
	}
	
	public void excluir() {
		
	}
	
	
	
}
