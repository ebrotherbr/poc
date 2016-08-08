package br.com.ebrother.poc.exception;

import br.com.ebrother.poc.exception.util.IndicadorTipoException;

public class NenhumRegistroEncontradoException extends ExceptionBase {

	private static final long serialVersionUID = -8536199773502445584L;

	private final static String mensagem = "Nenhum registro encontrado!";

	public NenhumRegistroEncontradoException() {
		super(mensagem, IndicadorTipoException.ERRO);
	}

}
