package br.com.ebrother.poc.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ebrother.poc.controller.ControllerBase;
import br.com.ebrother.poc.exception.GenericException;
import br.com.ebrother.poc.exception.NenhumRegistroEncontradoException;
import br.com.ebrother.poc.exception.util.ResponseExceptionDTO;

@ControllerAdvice
public class ExceptionControllerAdviceBase extends ControllerBase {

	@ExceptionHandler(GenericException.class)
	public ResponseEntity<ResponseExceptionDTO> erroDeNegocio(final HttpServletRequest req, final GenericException e) {
		final ResponseExceptionDTO response = new ResponseExceptionDTO();
		response.setMensagem(e.getMensagem());
		response.setTipo(e.getTipo().getDescricao());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NenhumRegistroEncontradoException.class)
	public ResponseEntity<String> nenhumRegistroEncontrado(final HttpServletRequest req,
			final NenhumRegistroEncontradoException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
