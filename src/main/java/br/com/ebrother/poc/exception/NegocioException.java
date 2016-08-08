package br.com.ebrother.poc.exception;

import br.com.ebrother.poc.exception.util.IndicadorTipoException;

public class NegocioException extends ExceptionBase {

	private static final long serialVersionUID = 7788449426572887941L;

	public NegocioException(final String mensagem, final IndicadorTipoException tipo) {
		super(mensagem, tipo);
	}

}
