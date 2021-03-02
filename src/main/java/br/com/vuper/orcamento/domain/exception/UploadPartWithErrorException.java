package br.com.vuper.orcamento.domain.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UploadPartWithErrorException extends RuntimeException {

	private List<String> errors;

	public UploadPartWithErrorException(List<String> errors) {

		if (Objects.isNull(errors)) {
			this.errors = new ArrayList<>();
		} else {
			this.errors = errors;
		}

	}

	public List<String> errors() {
		return this.errors;
	}
}
