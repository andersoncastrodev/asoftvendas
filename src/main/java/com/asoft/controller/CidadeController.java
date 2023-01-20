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

import com.asoft.dto.CidadeEstado;
import com.asoft.exception.CodigoNaoExisteException;
import com.asoft.model.Cidade;
import com.asoft.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping()
	public ResponseEntity<List<Cidade>> listarTodas(){
		
		return ResponseEntity.ok(cidadeService.consultarTodas());  
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> listarId(@PathVariable Long id){
		
		Optional<Cidade> cidade = cidadeService.consultarId(id);
		
		if(cidade.isEmpty()) { //Caso não encontre mostra um erro.
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(cidade.get());
	}
	
	@GetMapping("/por-nome")
	public ResponseEntity<List<Cidade>> consultarLikeNome(String nome){
	
		return ResponseEntity.ok().body(cidadeService.consultarLikeNome(nome));
	}
	
	@GetMapping("/cidade-estado")
	public ResponseEntity<List<CidadeEstado>> consultarCidadeEstado(){
		return ResponseEntity.ok().body(cidadeService.consultaCidadeEstado());
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade){
		
		try {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.salvar(cidade));
		}
		catch (CodigoNaoExisteException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Cidade cidade, @PathVariable Long id){
		
		try {
			
	    Optional<Cidade> cidadeAtual = cidadeService.consultarId(id);
		
	    if(cidadeAtual.isPresent() ) {
	    	
	    	BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
	    	
	    	Cidade cidadeSalva = cidadeService.salvar( cidadeAtual.get() );
	    	
	    	return ResponseEntity.ok().body(cidadeSalva);
	    }
		
			return ResponseEntity.notFound().build(); 
		
		}catch (CodigoNaoExisteException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
		} 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		try {
			
		cidadeService.excluir(id);
		
		return ResponseEntity.ok("Deletado com sucesso");
	
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
}
