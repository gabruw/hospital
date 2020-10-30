package br.edu.uniacademia.hospital.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.uniacademia.hospital.model.Funcionarios;
import br.edu.uniacademia.hospital.util.PersistenceUtil;

public class FuncionariosDao {
    public static FuncionariosDao funcionariosDAO;

    public static FuncionariosDao getInstance() {
        if (funcionariosDAO == null) {
            funcionariosDAO = new FuncionariosDao();
        }
        
        return funcionariosDAO;
    }

    public Funcionarios buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Funcionarios a where a.nomeFuncionario =:nome ");
        query.setParameter("nome", nome);

        List<Funcionarios> categoria = query.getResultList();
        if (categoria != null && categoria.size() > 0) {
            return categoria.get(0);
        }

        return null;
    }

    public List<Funcionarios> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Funcionarios As a");
        
        return query.getResultList();
    }
    
    public Funcionarios buscarPorId(Long id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("SELECT e FROM Funcionarios e WHERE e.idFuncionario = :idFuncionario");
        query.setParameter("idFuncionario", id);
        
        List<Funcionarios> funcionarios = query.getResultList();
        funcionarios = query.getResultList();
        
        return funcionarios.get(0);    
    }

    public void remover(Long id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();

        em.remove(em.getReference(Funcionarios.class, id));
        em.getTransaction().commit();
    }

    public Funcionarios persistir(Funcionarios funcionarios) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        
        try {
            funcionarios = em.merge(funcionarios);
            em.getTransaction().commit();
            
            System.out.println("Registro Funcionarios gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return funcionarios;
    }

    public void removeAll() {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        
        Query query = em.createQuery(" delete from Funcionarios ");
        query.executeUpdate();
        
        em.getTransaction().commit();
    }
}
