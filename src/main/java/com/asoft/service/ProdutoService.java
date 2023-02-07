package com.asoft.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asoft.exception.CodigoNaoExisteException;
import com.asoft.model.Produto;
import com.asoft.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> consultarTodos(){
		return produtoRepository.findAll();
	}
	
	public Optional<Produto> consultaId(Long produtoId) {
		return produtoRepository.findById(produtoId);
	}
	
	public Produto salvar(Produto produto) {
		
		return  produtoRepository.save(produto);
	}
	
	public void excluir(Long produtoId) {
		
		try {
			
			produtoRepository.deleteById(produtoId);
			
		} catch (NoSuchElementException e) {
			
			throw new CodigoNaoExisteException(String.format("Não existe um Produto com o codigo %d ", produtoId));
		}
		
	}
}
