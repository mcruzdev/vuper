package br.com.vuper.orcamento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/web/estruturas")
@Controller
public class EstruturasController {

	@GetMapping
	public String index() {
		return "estruturas";
	}

}
