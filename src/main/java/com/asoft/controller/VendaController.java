package com.asoft.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.asoft.model.Venda;
import com.asoft.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {

	@Autowired
	private VendaService vendaService;
	
	@GetMapping
	public ResponseEntity<List<Venda>> listarTodas(){
		
		return ResponseEntity.ok().body(vendaService.consultarTodas());
		
	}
	
	@GetMapping("/{vendaId}")
	public ResponseEntity<Optional<Venda>> listarId(@PathVariable Long vendaId){
		
		return ResponseEntity.ok().body(vendaService.consultaId(vendaId));
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Venda venda){
	
		try {
			
			return ResponseEntity.ok().body(vendaService.salvar(venda));
			
		} catch (CodigoNaoExisteException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@PutMapping("/{vendaId}")
	public ResponseEntity<?> atualizar(@RequestBody Venda venda, @PathVariable Long vendaId ){
		
		try {
			
		Optional<Venda> vendaAtual = vendaService.consultaId(vendaId);
		
		if(vendaAtual.isPresent()) {
		
			BeanUtils.copyProperties(venda, vendaAtual.get() , "id");
			
			Venda vendaSalva = vendaService.salvar( vendaAtual.get() );
			
			return ResponseEntity.ok().body(vendaSalva);
			
		}

		} catch (CodigoNaoExisteException e) {	
			return ResponseEntity.badRequest().body(e.getMessage());
		} 
		   return ResponseEntity.badRequest().build();
		
	}
	
	@DeleteMapping("/{vendaId}")
	public ResponseEntity<?> remover(@PathVariable Long vendaId){
		try {
			
			vendaService.excluir(vendaId);
			
		} catch (CodigoNaoExisteException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
		}
			return ResponseEntity.ok().build();
	}
}
