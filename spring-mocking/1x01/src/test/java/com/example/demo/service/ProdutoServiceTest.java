package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

	@Mock
	private ProdutoRepository produtoRepository;

	@InjectMocks
	private ProdutoService produtoService;

	
	@Test
	void deveRetornarProdutoQuandoIdExistir() {	
		Optional<Produto> produto = Optional.of(new Produto(1L, "Produto xpto", 100));

		Mockito.when(produtoRepository.findById(anyLong())).thenReturn(produto);
		
		Produto produtoEncontrado = produtoService.buscarPorId(1L);
		assertNotNull(produtoEncontrado);
		assertEquals("Produto xpto", produtoEncontrado.getNome());
	}

	@Test
	void deveLancarExcecaoQuandoProdutoNaoExistir() {
		Mockito.when(produtoRepository.findById(anyLong())).thenReturn(null);
		assertThrows(RuntimeException.class, () -> {
			produtoService.buscarPorId(2L);
		});
	}
}