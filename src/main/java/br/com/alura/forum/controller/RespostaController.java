package br.com.alura.forum.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.RespostaDto;
import br.com.alura.forum.controller.form.RespostaForm;
import br.com.alura.forum.modelo.Resposta;
import br.com.alura.forum.repository.RespostaRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/respostas")
public class RespostaController {
	
	@Autowired
	private RespostaRepository respostaRepository;
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE,  MediaType.APPLICATION_JSON_VALUE})
	public Page<RespostaDto> listar(@PageableDefault(direction = Direction.ASC, sort = "id") Pageable page) {
		Page<Resposta> respostas = respostaRepository.findAll(page);
		return RespostaDto.converter(respostas);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<RespostaDto> cadastrar(@RequestBody @Valid RespostaForm form, UriComponentsBuilder uriBuilder) {
		Resposta resposta = form.converter(topicoRepository);
		respostaRepository.save(resposta);
		
		URI uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();
		return ResponseEntity.created(uri).body(new RespostaDto(resposta));
	}
	
	
}
