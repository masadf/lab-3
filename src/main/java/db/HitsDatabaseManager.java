package db;

import model.Hit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class HitsDatabaseManager {
    private final EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("db_con");

    public List<Hit> getHitsData() {
        EntityManager manager = managerFactory.createEntityManager();
        List<Hit> hits;

        try {
            TypedQuery<Hit> query = manager.createQuery("SELECT hits FROM Hit hits", Hit.class);
            hits = query.getResultList();
        } finally {
            manager.close();
        }

        return hits;
    }

    public void insert(Hit hit) {
        EntityManager manager = managerFactory.createEntityManager();

        try {
            manager.getTransaction().begin();
            manager.persist(hit);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    public void clear() {
        EntityManager manager = managerFactory.createEntityManager();

        try {
            manager.getTransaction().begin();
            manager.createQuery("delete from Hit h", Hit.class).executeUpdate();
            manager.getTransaction().commit();
        }
        finally {
            manager.close();
        }
    }
}
