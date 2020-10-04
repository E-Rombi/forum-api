package br.com.alura.forum.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Curso;

public class AtualizacaoCursoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	
	@NotNull @NotEmpty @Length(min = 5)
	private String categoria;
	
	

	public String getNome() {
		return nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Curso atualizar(Optional<Curso> curso) {
		curso.get().setNome(this.nome);
		curso.get().setCategoria(this.categoria);
		return curso.get();
	}
	
}
