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
import com.asoft.model.Produto;
import com.asoft.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos() {
		return ResponseEntity.ok().body(produtoService.consultarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produto>> listarId(@PathVariable Long id){
		
		Optional<Produto> produto = produtoService.consultaId(id);
		
		if( produto.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().body(produto) ;
	}
	
	@GetMapping("/nome-produto")
	public ResponseEntity<Produto> consultaPorNome(String nome){
		
		return null;
	}
	
	@PostMapping
	public ResponseEntity<Produto> adicionar(@RequestBody Produto produto){

		Produto produtoSalvo = produtoService.salvar(produto);		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);	
	}
	
	@PutMapping("/{produtoId}")
	public ResponseEntity<Produto> atualizar(@RequestBody Produto produto, @PathVariable Long produtoId){
		
		Optional<Produto> produtoAtual = produtoService.consultaId(produtoId);
		
		if (produtoAtual.isPresent()) {
			
			BeanUtils.copyProperties(produto, produtoAtual.get(), "id");
			
			Produto produtoSalvo = produtoService.salvar(produtoAtual.get());
			
			return ResponseEntity.ok().body(produtoSalvo);
		}
	
		return  ResponseEntity.notFound().build(); 
	}
	
	@DeleteMapping("/{produtoId}")
	public ResponseEntity<?> remover(@PathVariable Long produtoId){
		
		try {
			
			produtoService.excluir(produtoId);
			
		} catch (CodigoNaoExisteException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	
		return ResponseEntity.ok().build();
	}
	
	
}
