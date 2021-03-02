package br.com.vuper.orcamento.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vuper.orcamento.appservice.model.DataConfigurationModel;
import br.com.vuper.orcamento.domain.repository.CategoriaRepository;
import br.com.vuper.orcamento.domain.repository.PecaRepository;

@RestController
@RequestMapping("/api/configs")
public class ConfiguracoesRestController {

	private CategoriaRepository categoriaRepository;
	private PecaRepository pecaRepository;

	public ConfiguracoesRestController(CategoriaRepository categoriarepository, PecaRepository pecaRepository) {
		this.categoriaRepository = categoriarepository;
		this.pecaRepository = pecaRepository;
	}

	@GetMapping
	public ResponseEntity<List<DataConfigurationModel>> configs() {

		List<DataConfigurationModel> configs = new ArrayList<DataConfigurationModel>();

		var quantidadePecas = this.pecaRepository.count();
		var quantidadeCategorias = this.categoriaRepository.count();

		configs.add(new DataConfigurationModel("categorias", quantidadeCategorias));
		configs.add(new DataConfigurationModel("pecas", quantidadePecas));

		return ResponseEntity.ok(configs);

	}
}
