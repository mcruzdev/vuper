package br.com.vuper.orcamento.appservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.vuper.orcamento.domain.exception.UploadPartWithErrorException;

@ControllerAdvice
public class VuperExceptionHandler {

	@ExceptionHandler(UploadPartWithErrorException.class)
	public ResponseEntity<Object> handleUploadPartWithErrorException(UploadPartWithErrorException exception,
			WebRequest webRequest) {

		
		return ResponseEntity.badRequest().body(exception.errors());

	}

}
