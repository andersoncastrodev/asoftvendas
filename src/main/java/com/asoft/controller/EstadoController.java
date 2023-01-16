package com.asoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.asoft.model.Estado;
import com.asoft.service.EstadoService;

@RestController
@RequestMapping("/estado")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public void hello() {
		System.out.println(">>> TESTE <<<<");
	}
	
	@GetMapping("/estados")
	public List<Estado> findEstados() {
		
	     return estadoService.listEstados();
	}
}
