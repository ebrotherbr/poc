package br.com.ebrother.poc.exception;

import br.com.ebrother.poc.exception.util.IndicadorTipoException;

public abstract class ExceptionBase extends RuntimeException {

	private static final long serialVersionUID = -8834904231920657289L;

	private final String mensagem;
	private final IndicadorTipoException tipo;

	public ExceptionBase(final String mensagem, final IndicadorTipoException tipo) {
		this.mensagem = mensagem;
		this.tipo = tipo;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public IndicadorTipoException getTipo() {
		return this.tipo;
	}

}
