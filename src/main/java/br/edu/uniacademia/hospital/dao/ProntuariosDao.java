package br.edu.uniacademia.hospital.dao;

import br.edu.uniacademia.hospital.model.Prontuarios;
import br.edu.uniacademia.hospital.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProntuariosDao {
	public static ProntuariosDao prontuariosDao;

	public static ProntuariosDao getInstance() {
		if (prontuariosDao == null) {
			prontuariosDao = new ProntuariosDao();
		}

		return prontuariosDao;
	}

	public Prontuarios buscar(String descricao) {
		EntityManager em = PersistenceUtil.getEntityManager();
		Query query = em.createQuery("select a from Prontuarios a where a.descricao =:descricao ");
		query.setParameter("descricao", descricao);

		List<Prontuarios> lista = query.getResultList();
		if (lista != null && lista.size() > 0) {
			return lista.get(0);
		}

		return null;
	}

	public Prontuarios buscarPorId(Long id) {
		EntityManager em = PersistenceUtil.getEntityManager();
		Query query = em.createQuery("SELECT e FROM Prontuarios e WHERE e.idProntuario = :idProntuario");
		query.setParameter("idProntuario", id);

		List<Prontuarios> prontuarios = query.getResultList();
		prontuarios = query.getResultList();

		return prontuarios.get(0);
	}

	public List<Prontuarios> buscarTodas() {
		EntityManager em = PersistenceUtil.getEntityManager();
		Query query = em.createQuery("from Prontuarios As a");

		return query.getResultList();
	}

	public void remover(Long id) {
		EntityManager em = PersistenceUtil.getEntityManager();
		em.getTransaction().begin();

		em.remove(em.getReference(Prontuarios.class, id));
		em.getTransaction().commit();
	}

	public Prontuarios persistir(Prontuarios prontuarios) {
		EntityManager em = PersistenceUtil.getEntityManager();
		em.getTransaction().begin();

		try {
			prontuarios = em.merge(prontuarios);
			em.getTransaction().commit();
			System.out.println("Registro Prontuarios gravado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prontuarios;
	}

	public void removeAll() {
		EntityManager em = PersistenceUtil.getEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery(" delete from Prontuarios ");

		query.executeUpdate();
		em.getTransaction().commit();
	}
}
