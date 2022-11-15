package beans;

import db.HitsDatabaseManager;
import lombok.Data;
import model.Hit;
import model.Point;
import model.PointHandler;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import java.util.List;

@ManagedBean
@ApplicationScoped
@Data
public class HitBean {
    private List<Hit> hits;
    private Point newPoint = new Point();
    private HitsDatabaseManager databaseManager = new HitsDatabaseManager();

    public HitBean() {
        hits = databaseManager.getHitsData();
    }

    public String addHit() {
        try {
            databaseManager.insert(new PointHandler().getHitInfo(newPoint));
        } catch (Exception e) {
            return "failure";
        }

        newPoint = new Point();
        hits = databaseManager.getHitsData();
        return "success";
    }

    public void clearHits() {
        databaseManager.clear();
        hits.clear();
    }
}
