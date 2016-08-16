package br.com.ebrother.poc.exception;

import br.com.ebrother.poc.exception.util.IndicadorTipoException;

public class GenericException extends ExceptionBase {

	private static final long serialVersionUID = 7788449426572887941L;

	public GenericException(final String mensagem, final IndicadorTipoException tipo) {
		super(mensagem, tipo);
	}

}
