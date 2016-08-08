package br.com.ebrother.poc.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.ebrother.poc.model.EntidadeBase;
import br.com.ebrother.poc.vo.DTOBase;

public abstract class ConverterBase<T extends EntidadeBase<? extends Serializable>, S extends DTOBase>
		implements Converter<T, S> {

	@Override
	public List<T> converterParaListaEntidade(final List<S> dtos) {
		final List<T> entidades = new ArrayList<>();
		for (final S dto : dtos) {
			entidades.add(this.converterParaEntidade(dto));
		}
		return entidades;
	}

	@Override
	public List<S> converterParaListaDTO(final List<T> entidades) {
		final List<S> dtos = new ArrayList<>();
		for (final T entidade : entidades) {
			dtos.add(this.converterParaDTO(entidade));
		}
		return dtos;
	}

}
