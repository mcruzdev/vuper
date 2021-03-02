package br.com.vuper.orcamento.appservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import br.com.vuper.orcamento.appservice.model.PecaCSV;
import br.com.vuper.orcamento.domain.exception.UploadPartWithErrorException;
import br.com.vuper.orcamento.domain.model.Categoria;

public class CSVHelper {

	private static final Logger logger = LoggerFactory.getLogger(CSVHelper.class);

	public static final String[] FORMATS = { "text/csv", "application/vnd.ms-excel" };

	public static boolean hasCSVFormat(MultipartFile file) {
		return Stream.of(FORMATS).anyMatch(format -> format.equals(file.getContentType()));
	}

	public static List<PecaCSV> transformaCSVParaPecas(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withDelimiter(';'));) {

			List<PecaCSV> pecas = new ArrayList<PecaCSV>();
			var errors = new ArrayList<String>();

			Iterable<CSVRecord> records = csvParser.getRecords();
			
			int line = 1;

			for (CSVRecord record : records) {
				
				var categoria = record.get(0);
				var descricao = record.get(1);
				var unidade   = record.get(2);
				
				if (Strings.isEmpty(categoria) || Strings.isEmpty(descricao) || Strings.isEmpty(unidade)) {
					var message = String.format("A linha %d contém um ou mais erros - Categoria: ' %s ', Descrição: ' %s ', Unidade: ' %s '", line, categoria, descricao, unidade);
					errors.add(message);
				} else {
					var peca = new PecaCSV(categoria, descricao, unidade);
					pecas.add(peca);
				}
				
				line++;
				
			}
			
			if (!errors.isEmpty()) {
				throw new UploadPartWithErrorException(errors);
			}

			return pecas;
			
			

		} catch (IOException e) {
			throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
		}

	}

	public static List<Categoria> transformaCSVParaCategorias(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Categoria> categorias = new ArrayList<Categoria>();

			Iterable<CSVRecord> records = csvParser.getRecords();

			for (CSVRecord record : records) {
				Categoria categoria = new Categoria(record.get("nome"));
				categorias.add(categoria);
			}

			return categorias;

		} catch (IOException e) {
			throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
		}
	}

}
