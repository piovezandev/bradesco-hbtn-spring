package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Produto;

@Service
public class ProdutoService {
	
	private List<Produto> produtos = new ArrayList<>();
	private Long contadorId = 1L;

	public List<Produto> listarProdutos() {
		return produtos;
	}

	public Produto adicionarProduto(Produto produto) {
		Produto produtoAux = produto;
		produtoAux.setId(contadorId);
		produtos.add(produtoAux);
		return produto;
	}

	public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
	    return produtos
	            .stream()
	            .filter(p -> p.getId().equals(id))
	            .findFirst()
	            .map(p -> {
	                p.setNome(produtoAtualizado.getNome());
	                p.setPreco(produtoAtualizado.getPreco());
	                return p;
	            })
	            .get();
	}

	public boolean deletarProduto(Long id) {
		Optional<Produto> produtoEncontrado = produtos
				.stream()
				.filter(p -> p.getId().equals(id))
				.findFirst();

		if (produtoEncontrado.isPresent()) {
			produtos.remove(produtoEncontrado.get());
			return true;
		}

		return false;
	}
}
