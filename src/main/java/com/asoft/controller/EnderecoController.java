package com.asoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	public ResponseEntity<Endereco> adicionar(@RequestBody Endereco endereco){
		
		Endereco enderecoSalvo = enderecoService.salvar(endereco);
		
		return ResponseEntity.ok().body(enderecoSalvo);
	}
	
	
}
