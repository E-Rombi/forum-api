package br.com.alura.forum.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.Http2;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.SSLConfig;
import com.jayway.restassured.path.json.JsonPath;

import br.com.alura.forum.builders.RespostaBuilder;
import br.com.alura.forum.controller.dto.RespostaDto;
import br.com.alura.forum.controller.form.RespostaForm;
import br.com.alura.forum.modelo.Resposta;
import br.com.alura.forum.repository.RespostaRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RespostasWSTest {

	@LocalServerPort
	int port;
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private RespostaRepository respostaRepository;
	
	private List<RespostaDto> esperado;
	private Resposta respostaEsperada;
	
	@org.junit.Before
	public void setup() {		
		RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation());
		RestAssured.baseURI = "https://localhost:8443";
			
		esperado = java.util.Arrays.asList(new RespostaDto(1L, "Resposta 1", LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)), ""), 
										   new RespostaDto(2L, "Resposta 2", LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)), ""));
		respostaEsperada = new RespostaBuilder()
								 .setId(6L)
								 .setMensagem("Rest Assured")
								 .setSolucao(false)
								 .setTopico(topicoRepository.getOne(1L))
								 .build();
	}
	
	@Test
	public void deveRetornarDuasRespostas() {
		JsonPath jsonPath = given()
							.port(port)
							.header("Accept", "application/json")
							.get("/respostas")
							.andReturn().jsonPath();

		RespostaDto recebido  = jsonPath.getObject("content[0]", RespostaDto.class);
		RespostaDto recebido2 = jsonPath.getObject("content[1]", RespostaDto.class);
		
		assertEquals(esperado.get(0), recebido);
		assertEquals(esperado.get(1), recebido2);
	}
	
	@Test
	public void deveCadastrarUmaResposta() {
		
		
		JsonPath jsonPath = given()
							.port(port)
							.header("Content-Type","application/json")
							.body(new RespostaForm("Rest Assured", 1L, LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)), false))
						.expect()
							.statusCode(403)
						.when()
							.post("/respostas")
						.andReturn()
							.jsonPath();
		
		RespostaForm formRecebido = jsonPath.getObject("$", RespostaForm.class);
		Resposta respostaRecebida = formRecebido.converter(topicoRepository);
		
		assertEquals(respostaEsperada, respostaRecebida);
	}
	
}
