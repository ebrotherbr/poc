package br.com.ebrother.poc.persistence;

import java.io.Serializable;
import java.util.List;

import br.com.ebrother.poc.model.EntidadeBase;

public interface Persistence<P extends Serializable, T extends EntidadeBase<P>> {

	T obter(Class<T> tipo, P id);

	List<T> listar(Class<T> tipo);

	T gravar(T objeto);

	void excluir(Class<T> tipo, P id);

	void excluir(T objeto);

}
