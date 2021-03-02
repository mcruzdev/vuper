package br.com.vuper.orcamento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/web/pecas")
@Controller
public class PecasController {

	@GetMapping
	public String index() {
		return "pecas";
	}

}
