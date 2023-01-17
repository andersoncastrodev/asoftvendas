package com.asoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asoft.model.Estado;
import com.asoft.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;
		
	@GetMapping
	public List<Estado> findEstados() {
		
	     return estadoService.listaEstados();
	}
	
	@GetMapping("/id")
	public Optional<Estado> buscar(@PathVariable Long id) {
		
		return estadoService.buscaId(id);
		
	}
	
	@PostMapping
	public Estado adicionar(@RequestBody Estado estado) {

		return estadoService.salvar(estado);
	}
	
}
