package br.com.ebrother.poc.persistence;

import java.io.Serializable;
import java.util.List;

import br.com.ebrother.poc.model.EntidadeBase;
import br.com.ebrother.poc.persistence.impl.PersistenceBase;

public class DAOBase<T extends EntidadeBase<PK>, PK extends Serializable> extends PersistenceBase<PK, T> {

	public T obter(final PK id) {
		return super.obter(this.getEntityClass(), id);
	}

	public List<T> listar() {
		return super.listar(this.getEntityClass());
	}

	public void excluir(final PK id) {
		super.excluir(this.getEntityClass(), id);
	}

}
