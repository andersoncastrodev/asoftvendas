package com.asoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asoft.model.Estado;
import com.asoft.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> listEstados(){
		List<Estado> lista = estadoRepository.findAll();
		return lista;
	}
}
