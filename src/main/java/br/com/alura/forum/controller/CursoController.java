package br.com.alura.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.CursoDto;
import br.com.alura.forum.controller.dto.MessageDto;
import br.com.alura.forum.controller.form.AtualizacaoCursoForm;
import br.com.alura.forum.controller.form.CursoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursoRepository;

@RestController
@RequestMapping("/cursos")
public class CursoController {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	@Cacheable(value = "listaCursos")
	public Page<CursoDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable page) {
		Page<Curso> cursos = cursoRepository.findAll(page);
		return CursoDto.converter(cursos);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Curso> detalhar(@PathVariable Long id) {
		Optional<Curso> curso = cursoRepository.findById(id);
		if (curso.isPresent()) {
			return ResponseEntity.ok(curso.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaCursos", allEntries = true)
	public ResponseEntity<CursoDto> cadastrar(@RequestBody @Valid CursoForm form, UriComponentsBuilder uriBuilder) {
		Curso curso = form.converter();
		cursoRepository.save(curso);
		
		URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
		return ResponseEntity.created(uri).body(new CursoDto(curso));
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	@CacheEvict(value = "listaCursos", allEntries = true)
	public ResponseEntity<CursoDto> atualizar(@RequestBody @Valid AtualizacaoCursoForm form, @PathVariable Long id) {
		Optional<Curso> curso = cursoRepository.findById(id);
		if (curso.isPresent()) {
			form.atualizar(curso);
			return ResponseEntity.ok(new CursoDto(curso.get()));
		}
		
		return ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	@CacheEvict(value = "listaCursos", allEntries = true)
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Curso> curso = cursoRepository.findById(id);
		if (curso.isPresent()) {
			cursoRepository.delete(curso.get());
			return ResponseEntity.ok(new MessageDto("Curso deletado com Sucesso"));
		}
		
		return ResponseEntity.notFound().build();
	}

	
	
	
	
}