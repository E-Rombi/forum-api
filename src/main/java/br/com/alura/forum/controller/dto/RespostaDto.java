package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.alura.forum.modelo.Resposta;

public class RespostaDto {

	private Long id;
	private String mensagem;
	
	private LocalDateTime dataCriacao;
	private String nomeAutor;
	
	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.nomeAutor = (!(resposta.getAutor() == null)) ? resposta.getAutor().getNome() : "";
	}

	public RespostaDto(long id, String mensagem, LocalDateTime dataCriacao, String nomeAutor) {
		this.id = id;
		this.mensagem = mensagem;
		this.dataCriacao = dataCriacao;
		this.nomeAutor = nomeAutor;
	}

	public Long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}
	
	public static Page<RespostaDto> converter(Page<Resposta> respostas) {
		return respostas.map(RespostaDto::new);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespostaDto other = (RespostaDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RespostaDto [id=" + id + ", mensagem=" + mensagem + ", dataCriacao=" + dataCriacao + ", nomeAutor="
				+ nomeAutor + "]";
	}
}
