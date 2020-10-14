package br.com.alura.forum.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.alura.forum.modelo.Resposta;
import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.TopicoRepository;

public class RespostaForm {
	
	@NotNull @NotEmpty
	private String mensagem;
	
	@NotNull 
	private Long topico;
	
	private LocalDateTime dataCriacao;
	
	@JsonIgnoreProperties(value = {"email", "senha"})
	private Usuario autor;
	private Boolean solucao;	
	
	public RespostaForm() {
		super();
	}

	public RespostaForm(@NotNull @NotEmpty String mensagem, @NotNull Long topico, LocalDateTime dataCriacao,
			Boolean solucao) {
		this.mensagem = mensagem;
		this.topico = topico;
		this.dataCriacao = dataCriacao;
		this.solucao = solucao;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public Long getTopico() {
		return topico;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public Boolean getSolucao() {
		return solucao;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void setTopico(Long topico) {
		this.topico = topico;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public void setSolucao(Boolean solucao) {
		this.solucao = solucao;
	}

	public Resposta converter(TopicoRepository topicoRepository) {
		return new Resposta(this.mensagem, topicoRepository.findById(topico).get(), this.dataCriacao, solucao);
	}
	
}
