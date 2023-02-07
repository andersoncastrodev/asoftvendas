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
import com.asoft.model.Carrinho;
import com.asoft.service.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoService carrinhoService;
	
	@GetMapping
	public ResponseEntity<List<Carrinho>> listarTodos(){
		
		return ResponseEntity.ok().body(carrinhoService.consultarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Carrinho>> listarId(@PathVariable Long id){
		
		return ResponseEntity.ok().body(carrinhoService.consultaId(id));
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Carrinho carrinho){
		
		try {

			return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoService.salvar(carrinho));
			
		} catch (CodigoNaoExisteException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());

		}
		catch (ErroChaveEstrangueiraEmUsoException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Carrinho carrinho, @PathVariable Long id){
		
		try {
			Optional<Carrinho> carrinhoAtual = carrinhoService.consultaId(id);
			
			if(carrinhoAtual.isPresent()) {
				
				BeanUtils.copyProperties(carrinho, carrinhoAtual.get(), "id");
				
				Carrinho carrinhoSalvo = carrinhoService.salvar(carrinhoAtual.get());
				
				return ResponseEntity.ok().body(carrinhoSalvo);
			}
			
		}catch (CodigoNaoExisteException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());

		}
		catch (ErroChaveEstrangueiraEmUsoException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}

		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		
		try {
			carrinhoService.excluir(id);
			return ResponseEntity.noContent().build();
			
		} catch (CodigoNaoExisteException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage()); //Se o codigo NÃO Existir
		} 
		  catch (ErroChaveEstrangueiraEmUsoException e) {
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); //Erro de chave estrangueira 
		}
		
	}
	
	
	
}
