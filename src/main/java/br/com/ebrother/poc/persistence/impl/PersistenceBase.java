package br.com.ebrother.poc.persistence.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ebrother.poc.messages.MessageByLocaleService;
import br.com.ebrother.poc.model.EntidadeBase;
import br.com.ebrother.poc.persistence.Persistence;

public abstract class PersistenceBase<P extends Serializable, T extends EntidadeBase<P>> implements Persistence<P, T> {

	@Autowired
	EntityManager entityManager;

	@Autowired
	protected MessageByLocaleService mensagens;

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.getEntityClass());
	}

	protected Criteria createCriteria() {
		final DetachedCriteria detachedCriteria = this.createDetachedCriteria();
		return detachedCriteria.getExecutableCriteria((Session) this.getEntityManager().getDelegate());
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	protected <T> List<T> executeCriteria(final Criteria criteria, final int firstResult, final int maxResults,
			final Map<String, Boolean> mapOrdenacao) {
		criteria.setFirstResult(firstResult);
		if (maxResults > 0) {
			criteria.setMaxResults(maxResults);
		}
		for (final Map.Entry<String, Boolean> entry : mapOrdenacao.entrySet()) {
			this.adicionarOrder(criteria, entry.getKey(), entry.getValue());
		}
		return criteria.list();
	}

	@SuppressWarnings("hiding")
	protected <T> List<T> executeCriteria(final Criteria criteria, final int firstResult, final int maxResults,
			final String orderProperty, final boolean asc) {
		final Map<String, Boolean> mapOrdenacao = new HashMap<>();
		if (orderProperty != null && !orderProperty.trim().isEmpty()) {
			mapOrdenacao.put(orderProperty, Boolean.valueOf(asc));
		}
		return this.executeCriteria(criteria, firstResult, maxResults, mapOrdenacao);
	}

	@SuppressWarnings("hiding")
	protected <T> List<T> executeCriteria(final Criteria criteria, final int firstResult, final int maxResults) {
		return this.executeCriteria(criteria, firstResult, maxResults, null, false);
	}

	@SuppressWarnings("hiding")
	protected <T> List<T> executeCriteria(final Criteria criteria) {
		return this.executeCriteria(criteria, 0, 0, null, false);
	}

	private void adicionarOrder(final Criteria criteria, final String orderProperty, final boolean asc) {
		if (orderProperty != null && !orderProperty.trim().isEmpty()) {
			criteria.addOrder(asc ? Order.asc(orderProperty).ignoreCase() : Order.desc(orderProperty));
		}
	}

	protected Object executeCriteriaUniqueResult(final Criteria criteria) {
		return criteria.uniqueResult();
	}

	protected Long executeCriteriaCount(final Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		final Long result = (Long) this.executeCriteriaUniqueResult(criteria);
		if (result == null) {
			return 0L;
		}
		return result;
	}

	protected Long executeCriteriaCountDistinct(final Criteria criteria, final String countProperty) {
		criteria.setProjection(Projections.countDistinct(countProperty));
		final Long result = (Long) this.executeCriteriaUniqueResult(criteria);
		if (result == null) {
			return 0L;
		}
		return result;
	}

	@Override
	public T obter(final Class<T> tipo, final P id) {
		return this.getEntityManager().find(tipo, id);
	}

	@Override
	public List<T> listar(final Class<T> tipo) {
		final Criteria criteria = this.createCriteria();
		return this.executeCriteria(criteria);
	}

	@Override
	public T gravar(final T objeto) {
		if (objeto.getId() == null) {
			this.getEntityManager().persist(objeto);
			return objeto;
		}
		return this.getEntityManager().merge(objeto);
	}

	@Override
	public void excluir(final Class<T> tipo, final P id) {
		final T objeto = this.obter(tipo, id);
		this.excluir(objeto);
	}

	@Override
	public void excluir(final T objeto) {
		this.getEntityManager().remove(objeto);
	}

}
