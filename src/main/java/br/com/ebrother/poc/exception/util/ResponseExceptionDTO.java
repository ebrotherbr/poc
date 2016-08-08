package br.com.ebrother.poc.exception.util;

public class ResponseExceptionDTO {

	private String mensagem;
	private String tipo;

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(final String mensagem) {
		this.mensagem = mensagem;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

}
