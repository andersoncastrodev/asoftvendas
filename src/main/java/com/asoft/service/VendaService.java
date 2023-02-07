package com.asoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.asoft.exception.CodigoNaoExisteException;
import com.asoft.model.Cliente;
import com.asoft.model.Venda;
import com.asoft.repository.ClienteRepository;
import com.asoft.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public List<Venda> consultarTodas(){
		
		return vendaRepository.findAll();
	}
	
	public Optional<Venda> consultaId(Long vendaId) {
		
		return vendaRepository.findById(vendaId);	
	}
	
	public Venda salvar(Venda venda) {
		
		Long clienteId = venda.getCliente().getId();
		
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if(cliente.isEmpty()) {
			throw new CodigoNaoExisteException(String.format("Não existe um cliente de codigo: %d", clienteId));
		}
		
		venda.setCliente( cliente.get() );
		
		return vendaRepository.save(venda);
	}
	
	public void excluir(Long vendaId) {
		
		try {
			
		    vendaRepository.deleteById(vendaId);
		
		} catch (EmptyResultDataAccessException e) {
			
			throw new CodigoNaoExisteException(String.format("Não existe uma venda de codigo: %d", vendaId));
		}
		
	}
	
	
}
