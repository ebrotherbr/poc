package br.com.ebrother.poc.converter;

import java.io.Serializable;
import java.util.List;

import br.com.ebrother.poc.model.EntidadeBase;
import br.com.ebrother.poc.vo.DTOBase;

public interface Converter<T extends EntidadeBase<? extends Serializable>, S extends DTOBase> {

	public T converterParaEntidade(S dto);

	public S converterParaDTO(T entidade);

	public List<T> converterParaListaEntidade(List<S> dtos);

	public List<S> converterParaListaDTO(List<T> entidades);

}
