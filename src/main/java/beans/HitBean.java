package beans;

import db.HitsDatabaseManager;
import lombok.Data;
import model.Hit;
import model.PointHandler;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean
@ApplicationScoped
@Data
public class HitBean {
    private List<Hit> hits;
    private HitsDatabaseManager databaseManager = new HitsDatabaseManager();

    public HitBean() {
        hits = databaseManager.getHitsData();
    }

    public String addHit(Point newPoint) {
        try {
            databaseManager.insert(new PointHandler().getHitInfo(newPoint));
        } catch (Exception e) {
            return "failure";
        }
        hits = databaseManager.getHitsData();
        return "success";
    }

    public void clearHits() {
        databaseManager.clear();
        hits.clear();
    }
}
