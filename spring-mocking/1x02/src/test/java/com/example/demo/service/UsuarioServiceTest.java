package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService usuarioService;

	@Mock
	private UsuarioRepository usuarioRepository;

	@BeforeTestMethod
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void deveRetornarUsuarioQuandoIdExistir() {
		Optional<Usuario> usuario = Optional.of(createMockUsuario());
		Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(usuario);

		Usuario usuarioEncontrado = usuarioService.buscarUsuarioPorId(1L);

		assertNotNull(usuarioEncontrado);
		assertEquals("alan@email.com", usuarioEncontrado.getEmail());
	}

	@Test
	public void deveLancarExcecaoQuandoUsuarioNaoExistir() {
		Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(null);
		assertThrows(RuntimeException.class, () -> {
			usuarioService.buscarUsuarioPorId(1L);
		});
	}

	@Test
	public void deveSalvarUsuarioComSucesso() {
		when(usuarioRepository.save(any())).thenReturn(createMockUsuario());
		Usuario usuarioSalvo = usuarioService.salvarUsuario(new Usuario());
		assertNotNull(usuarioSalvo);
		assertEquals(1L, usuarioSalvo.getId());
		assertEquals("alan@email.com", usuarioSalvo.getEmail());
	}

	private Usuario createMockUsuario() {
		Usuario usuario = new Usuario(1L, "Alan Piovezan", "alan@email.com");
		return usuario;
	}
}
