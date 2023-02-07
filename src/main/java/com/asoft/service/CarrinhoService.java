package com.asoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.asoft.exception.CodigoNaoExisteException;
import com.asoft.exception.ErroChaveEstrangueiraEmUsoException;
import com.asoft.model.Carrinho;
import com.asoft.model.Produto;
import com.asoft.model.Venda;
import com.asoft.repository.CarrinhoRepository;
import com.asoft.repository.ProdutoRepository;
import com.asoft.repository.VendaRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private VendaRepository vendaRepository;
	

	public List<Carrinho> consultarTodos(){
		
		return carrinhoRepository.findAll();
	}
	
	public Optional<Carrinho> consultaId(Long carrinhoId) {
		
		return carrinhoRepository.findById(carrinhoId);
	}
	
	public Carrinho salvar(Carrinho carrinho) {
		
		try {
			
			Long produtoId = carrinho.getProduto().getId();
			Optional<Produto> produto = produtoRepository.findById(produtoId);
			
			if(produto.isEmpty()) {
				
				throw new CodigoNaoExisteException(String.format("Não existe um produto de codigo: %d", produtoId ));
			}
			
			Long vendaId = carrinho.getVenda().getId();
			Optional<Venda> venda = vendaRepository.findById(vendaId);
			
			if(venda.isEmpty()) {
				
				throw new CodigoNaoExisteException(String.format("Não existe uma venda de codigo: %d", vendaId ));
			}
			
			return carrinhoRepository.save(carrinho);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new ErroChaveEstrangueiraEmUsoException(String.format("Codigo %d não existe com chave estrangueira", carrinho.getProduto().getId()));
		}
		
		
	}
	
	public void excluir(Long carrinhoId) {
		
		try {
			
			carrinhoRepository.deleteById(carrinhoId);
			
		}catch (EmptyResultDataAccessException e) {
			throw new CodigoNaoExisteException(String.format("Não existe um carrinho com o codigo %d ", carrinhoId));
		}
		catch (DataIntegrityViolationException e) {
			throw new ErroChaveEstrangueiraEmUsoException(String.format("Esse codigo: %d esta sendo usado como FK", carrinhoId));
		}
		
	}
	
	
	
}
