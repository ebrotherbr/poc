package br.com.ebrother.poc.model;

import java.io.Serializable;

public abstract class EntidadeBase<P extends Serializable> implements Serializable {

	private static final long serialVersionUID = -3103675696559189708L;

	public abstract P getId();

}
