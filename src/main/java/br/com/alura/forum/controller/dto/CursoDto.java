package br.com.alura.forum.controller.dto;

import br.com.alura.forum.modelo.Curso;

public class CursoDto {
	
	private String nome;
	private String categoria;
	
	public CursoDto(Curso curso) {
		super();
		this.nome = curso.getNome();
		this.categoria = curso.getCategoria();
	}

	public String getNome() {
		return nome;
	}

	public String getCategoria() {
		return categoria;
	}
}
