package br.com.alura.forum.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alura.forum.modelo.Resposta;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RespostasWSTest {

	@LocalServerPort
	int port;
	
	private List<Resposta> esperado;
	
	
	@org.junit.Before
	public void setup() {
		esperado = java.util.Arrays.asList(new Resposta(1L, "Resposta 1"), 
										new Resposta(2L, "Resposta 2"), 
										new Resposta(3L, "Resposta 3"), 
										new Resposta(4L, "Resposta 1"),
										new Resposta(5L, "Resposta 2"));
	}
	
	@Test
	public void deveRetornarListadeRespostas() {
		com.jayway.restassured.path.json.JsonPath jsonPath = given()
		.port(port)
		.header("Accept", "application/json")
		.get("/respostas")
		.andReturn().jsonPath();
		
		List<Resposta> recebido = jsonPath.getList("content", Resposta.class);
		
		assertEquals(recebido, esperado);
		
	}
	
}
