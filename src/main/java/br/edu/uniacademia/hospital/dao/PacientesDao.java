package br.edu.uniacademia.hospital.dao;

import br.edu.uniacademia.hospital.model.Pacientes;
import br.edu.uniacademia.hospital.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PacientesDao {
	public static PacientesDao pacientesDao;

	public static PacientesDao getInstance() {
		if (pacientesDao == null) {
			pacientesDao = new PacientesDao();
		}
		
		return pacientesDao;
	}

	public Pacientes buscar(String nome) {
		EntityManager em = PersistenceUtil.getEntityManager();
		Query query = em.createQuery("select a from Pacientes a where a.nomePaciente =:nome ");
		query.setParameter("nome", nome);

		List<Pacientes> lista = query.getResultList();
		if (lista != null && lista.size() > 0) {
			return lista.get(0);
		}

		return null;
	}

	public Pacientes buscarPorId(Long id) {

		EntityManager em = PersistenceUtil.getEntityManager();
		Query query = em.createQuery("SELECT e FROM Pacientes e WHERE e.idPaciente = :idPaciente");
		query.setParameter("idPaciente", id);

		List<Pacientes> pacientes = query.getResultList();
		pacientes = query.getResultList();

		return pacientes.get(0);

	}

	public List<Pacientes> buscarTodas() {
		EntityManager em = PersistenceUtil.getEntityManager();
		Query query = em.createQuery("from Pacientes As a");

		return query.getResultList();
	}

	public void remover(Long id) {
		EntityManager em = PersistenceUtil.getEntityManager();
		em.getTransaction().begin();

		em.remove(em.getReference(Pacientes.class, id));
		em.getTransaction().commit();
	}

	public Pacientes persistir(Pacientes pacientes) {
		EntityManager em = PersistenceUtil.getEntityManager();
		em.getTransaction().begin();

		try {
			pacientes = em.merge(pacientes);
			em.getTransaction().commit();
			System.out.println("Registro Pacientes gravado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pacientes;
	}

	public void removeAll() {
		EntityManager em = PersistenceUtil.getEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery(" delete from Pacientes ");

		query.executeUpdate();
		em.getTransaction().commit();
	}
}
