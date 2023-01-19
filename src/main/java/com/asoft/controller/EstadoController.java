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
import com.asoft.exception.ErroChaveEstrangueiraEmUsoException;
import com.asoft.model.Estado;
import com.asoft.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;
		
	@GetMapping
	public ResponseEntity<List<Estado>> listarTodos() {
			
	     return ResponseEntity.ok( estadoService.consultarTodos() ) ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> listarId(@PathVariable Long id) {
		
		Optional<Estado> estado = estadoService.consultarId(id);
		
		if (estado.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(estado.get());	
	}
	
	@PostMapping
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {

		Estado estadoSalvo =  estadoService.salvar(estado);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long id,@RequestBody Estado estado) {
		
		Optional<Estado> estadoAtual = estadoService.consultarId(id);
		
		if(estadoAtual.isPresent() ) {
			
			BeanUtils.copyProperties(estado, estadoAtual.get(),"id");
			
			Estado estadoSalvo = estadoService.salvar(estadoAtual.get());
			
			return ResponseEntity.ok(estadoSalvo);	
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		
		try {
			
			estadoService.excluir(id);
			return ResponseEntity.noContent().build();
			
		} catch (CodigoNaoExisteException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage()); //Se o codigo NÃO Existir
		} 
		  catch (ErroChaveEstrangueiraEmUsoException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage()); //Erro de chave estrangueira 
		}
			
	}
	
	
	
	
	
}
