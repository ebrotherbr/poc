package br.com.ebrother.poc.exception.util;

public enum IndicadorTipoException {

	ERRO(0, "Erro");

	private Integer codigo;
	private String descricao;

	private IndicadorTipoException(final Integer codigo, final String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
