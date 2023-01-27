package com.asoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asoft.exception.CodigoNaoExisteException;
import com.asoft.exception.ErroChaveEstrangueiraEmUsoException;
import com.asoft.model.Cliente;
import com.asoft.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping
	public List<Cliente> listarTodos(){
		
		return clienteService.consultarTodos();
	}
	
	@RequestMapping("/{clienteId}")
	public Optional<Cliente> listarId(@PathVariable Long clienteId) {
		
		return clienteService.consultaId(clienteId);
	}
	
	public Cliente consultarLikeNome() {
		
		return null;
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cliente cliente){
		
		try {
			
			return ResponseEntity.ok().body(clienteService.salvar(cliente)); 	
			
		} catch (CodigoNaoExisteException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
		
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente, @PathVariable Long clienteId){
		
		Optional<Cliente> clienteAtual = clienteService.consultaId(clienteId);
		
		if(clienteAtual.isPresent()) {
			
			BeanUtils.copyProperties(cliente, clienteAtual.get(), "id");
			
			Cliente clienteSalvo = clienteService.salvar( clienteAtual.get() );
			
			return ResponseEntity.ok().body(clienteSalvo);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<?> remover(@PathVariable Long clienteId){
		
		try {
			
			clienteService.excluir(clienteId);
			
		}catch (CodigoNaoExisteException e) {
	    	return ResponseEntity.badRequest().body(e.getMessage());
		} 
		catch (ErroChaveEstrangueiraEmUsoException e) {
	    	return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.ok().build();
	}
	
	
}
