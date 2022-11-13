package beans;

import lombok.Data;
import model.Hit;
import model.Point;
import model.PointHandler;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.util.List;

@ManagedBean
@ApplicationScoped
@Data
public class HitBean {
    private List<Hit> hits;
    private Point newPoint = new Point();

    private EntityManager manager;
    private EntityManagerFactory managerFactory;

    public HitBean(){
        managerFactory = Persistence.createEntityManagerFactory("db_con");
        manager = managerFactory.createEntityManager();
        TypedQuery<Hit> query = manager.createQuery("SELECT hits FROM Hit hits", Hit.class);
        hits = query.getResultList();
        manager.close();
        managerFactory.close();
    }

    public String  addHit() {
        managerFactory = Persistence.createEntityManagerFactory("db_con");
        manager = managerFactory.createEntityManager();
        Hit hit = new PointHandler().getHitInfo(newPoint);
        hits.add(hit);

        manager.getTransaction().begin();
        manager.persist(hit);
        manager.getTransaction().commit();

        newPoint = new Point();
        TypedQuery<Hit> query = manager.createQuery("SELECT hits FROM Hit hits", Hit.class);
        hits = query.getResultList();
        manager.close();
        managerFactory.close();
        return "table";
    }

    public void clearHits() {
        managerFactory = Persistence.createEntityManagerFactory("db_con");
        manager = managerFactory.createEntityManager();

        manager.getTransaction().begin();
        manager.createQuery("delete from Hit h", Hit.class).executeUpdate();
        manager.getTransaction().commit();

        manager.close();
        managerFactory.close();
        hits.clear();
    }
}
