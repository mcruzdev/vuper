package br.com.vuper.orcamento.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.vuper.orcamento.appservice.CSVHelper;
import br.com.vuper.orcamento.domain.exception.UploadPartWithErrorException;
import br.com.vuper.orcamento.domain.model.Categoria;
import br.com.vuper.orcamento.domain.model.Peca;
import br.com.vuper.orcamento.domain.repository.CategoriaRepository;
import br.com.vuper.orcamento.domain.repository.PecaRepository;

@Controller
@RequestMapping("/web/configs")
public class ConfiguracoesController {

	private Logger logger = LoggerFactory.getLogger(ConfiguracoesController.class);

	private final CategoriaRepository categoriaRepository;
	private final PecaRepository pecaRepository;

	public ConfiguracoesController(CategoriaRepository categoriaRepository, PecaRepository pecaRepository) {
		this.categoriaRepository = categoriaRepository;
		this.pecaRepository = pecaRepository;
	}

	@GetMapping
	public String index() {
		return "configs";
	}

	@PostMapping("/categorias")
	public String configurarCategorias(@RequestParam("file") MultipartFile file) {

		logger.info("Content type: {}", file.getContentType());

		if (CSVHelper.hasCSVFormat(file)) {
			try {
				var categorias = CSVHelper.transformaCSVParaCategorias(file.getInputStream());
				this.categoriaRepository.saveAll(categorias);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		return "configs";
	}

	@PostMapping("/pecas")
	public String configurarPecas(@RequestParam("file") MultipartFile file) {

		var categorias = this.categoriaRepository.findAll();
		var pecas = new ArrayList<Peca>();
		var errors = new ArrayList<String>();

		if (CSVHelper.hasCSVFormat(file)) {

			try {

				var pecasCSV = CSVHelper.transformaCSVParaPecas(file.getInputStream());

				logger.info("CSV items: {}", pecasCSV.size());

				pecasCSV.forEach(peca -> {

					for (Categoria categoria : categorias) {

						if (peca.getNome().equalsIgnoreCase(categoria.getName())) {
							pecas.add(new Peca(peca.getNome(), peca.getUnidadeMedida(), categoria));
						}
					}

					if (Strings.isBlank(peca.getCategoria())) {
						errors.add(String.format("Não foi possível preencher a categoria da peça %s", peca.getNome()));
					}
				});

			} catch (IOException e) {
				e.printStackTrace();
			}

			if (!errors.isEmpty()) {
				throw new UploadPartWithErrorException(errors);
			}

			this.pecaRepository.saveAll(pecas);
		}

		return "configs";
	}

}
