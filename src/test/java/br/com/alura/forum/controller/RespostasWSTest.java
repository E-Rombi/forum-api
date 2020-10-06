package br.com.alura.forum.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.SSLConfig;

import br.com.alura.forum.controller.dto.RespostaDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RespostasWSTest {

	@LocalServerPort
	int port;
	
	private List<RespostaDto> esperado;
	
	@org.junit.Before
	public void setup() {		
		esperado = java.util.Arrays.asList(new RespostaDto(1L, "Resposta 1", null, ""), 
										new RespostaDto(2L, "Resposta 2", null, ""), 
										new RespostaDto(3L, "Resposta 3", null, ""), 
										new RespostaDto(4L, "Resposta 1", null, ""),
										new RespostaDto(5L, "Resposta 2", null, ""));
	}
	
	@Test
	public void deveRetornarDuasRespostas() {
		RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation());
		com.jayway.restassured.path.json.JsonPath jsonPath = given()
		.port(port)
		.header("Accept", "application/json")
		.get("https://localhost:8443/respostas")
		.andReturn().jsonPath();

		//List<RespostaDto> recebidos  = jsonPath.getList("list.content", RespostaDto.class);
		RespostaDto recebido  = jsonPath.getObject("content[0]", RespostaDto.class);
		RespostaDto recebido2 = jsonPath.getObject("content[1]", RespostaDto.class);
		
		assertEquals(esperado.get(0), recebido);
		assertEquals(esperado.get(1), recebido2);
	}
	
}
