package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DocumentacaoController {
	
	@RequestMapping("/docs")
	public String docs() {
		return "redirect:swagger-ui.html";
	}
}
