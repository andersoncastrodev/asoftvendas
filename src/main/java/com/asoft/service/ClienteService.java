package com.asoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.asoft.exception.CodigoNaoExisteException;
import com.asoft.exception.ErroChaveEstrangueiraEmUsoException;
import com.asoft.model.Cliente;
import com.asoft.model.Endereco;
import com.asoft.repository.ClienteRepository;
import com.asoft.repository.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public List<Cliente> consultarTodos(){
		
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> consultaId(Long clienteId) {
		
		return clienteRepository.findById(clienteId);
	}
	
	public Cliente salvar(Cliente cliente){
		
		Long enderecoId = cliente.getEndereco().getId();
		
		Optional<Endereco> endereco = enderecoRepository.findById( enderecoId );
		
		if(endereco.isEmpty()) {
			throw new CodigoNaoExisteException(String.format("Não existe um endereço de codigo: %d", enderecoId));
		}
		
		cliente.setEndereco( endereco.get() );
		
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		
		try {
			
			clienteRepository.deleteById(clienteId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new CodigoNaoExisteException(String.format("Não existe um cliente com o codigo %d ", clienteId));
		}
		catch (DataIntegrityViolationException e) {
			throw new ErroChaveEstrangueiraEmUsoException(String.format("Esse codigo: %d esta sendo usado como FK", clienteId));
		}
		
	}
	
	
	
	
}
