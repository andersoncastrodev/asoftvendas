package com.asoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asoft.exception.CodigoNaoExisteException;
import com.asoft.model.Endereco;
import com.asoft.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping()
	public ResponseEntity<List<Endereco>> listarTodos(){
		
		return ResponseEntity.ok().body(enderecoService.consultarTodos()) ; 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> listarId(@PathVariable Long id){
		
		Optional<Endereco> endereco = enderecoService.consultarId(id);
		
		if(endereco.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().body( endereco.get() );
	}
	
	public ResponseEntity<Endereco> consultarLikeNome(){
		return null;
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Endereco endereco){
		
		try {
			Endereco enderecoSalvo = enderecoService.salvar(endereco);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(enderecoSalvo);
			
		} catch (CodigoNaoExisteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Endereco endereco, @PathVariable Long id){
		
			Optional<Endereco> enderecoAtual = enderecoService.consultarId(id);
			
			if(enderecoAtual.isPresent()) {
				
				BeanUtils.copyProperties(endereco, enderecoAtual.get(), "id");
				
				return ResponseEntity.ok(enderecoService.salvar(endereco));
			}

			return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		
		try {
			
			enderecoService.excluir(id);
			
		} catch (Exception e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	
}
