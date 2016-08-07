package br.com.ebrother.poc.conversor;

public interface ConversorBase {

	public <S, T> T converterParaEntidade(S dto);

}
