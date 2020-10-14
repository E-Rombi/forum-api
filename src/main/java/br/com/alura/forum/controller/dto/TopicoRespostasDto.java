package br.com.alura.forum.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.alura.forum.modelo.Resposta;
import br.com.alura.forum.modelo.Topico;

public class TopicoRespostasDto {
	
	private String titulo;
	
	private List<Resposta> respostas;
	
	public TopicoRespostasDto() {	}
	
	public TopicoRespostasDto(Topico topico) {
		this.titulo = topico.getTitulo();
		this.respostas = topico.getRespostas();
	}
	
	public String getTitulo() {
		return titulo;
	}
	public List<Resposta> getRespostas() {
		return respostas;
	}
	
}
