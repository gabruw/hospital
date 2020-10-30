package br.edu.uniacademia.hospital.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.eclipse.persistence.sessions.Session;

public class PersistenceUtil {
	private static Session session;
    private static EntityManagerFactory FACTORY;
    private static final String PERSISTENCE_UNIT_NAME = "hospitalPU";
    private static ThreadLocal<EntityManager> MANAGER = new ThreadLocal<EntityManager>();

    static {
        if (FACTORY == null) {
            try {
                FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            } catch (Throwable e) {
                Logger.getLogger(PersistenceUtil.class.getName()).log(
                        Level.WARNING, "Falha na criação do EntityManagerFactory!",
                        e.getMessage());
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    public static EntityManager getEntityManager() {
        EntityManager em = MANAGER.get();
        if (em == null) {
            em = FACTORY.createEntityManager();
            MANAGER.set(em);
        }
        
        return em;
    }

    public static void closeEntityManager() {
        EntityManager em = MANAGER.get();
        if (em != null) {
            em.close();
        }
        
        MANAGER.set(null);
    }

    public static Session getSession() {
        if (session == null) {
            session = (Session) getEntityManager().getDelegate();
        }
        
        return session;
    }

}
