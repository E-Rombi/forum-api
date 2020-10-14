package br.com.alura.forum.builders;

import java.time.LocalDateTime;

import br.com.alura.forum.modelo.Resposta;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.modelo.Usuario;

public class RespostaBuilder {

	private Resposta resposta;
	
	public RespostaBuilder() {
		resposta = new Resposta();
	}
	
	public RespostaBuilder setId(Long id) {
		resposta.setId(id);
		return this;
	}
	
	public RespostaBuilder setMensagem(String mensagem) {
		resposta.setMensagem(mensagem);
		return this;
	}
	
	public RespostaBuilder setTopico(Topico topico) {
		resposta.setTopico(topico);
		return this;
	}
	
	public RespostaBuilder setMensagem(LocalDateTime dataCriacao) {
		resposta.setDataCriacao(dataCriacao);
		return this;
	}
	
	public RespostaBuilder setUsuario(Usuario usuario) {
		resposta.setAutor(usuario);
		return this;
	}
	
	public RespostaBuilder setSolucao(Boolean solucao) {
		resposta.setSolucao(solucao);
		return this;
	}
	
	public Resposta build() {
		return resposta;
	}
	
}
