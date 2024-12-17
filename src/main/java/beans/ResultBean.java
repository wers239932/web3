package beans;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import validation.Validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SessionScoped
@Named("resultBean")
public class ResultBean implements Serializable {
    private final List<Result> results = Collections.synchronizedList(new ArrayList<>());

    public List<Result> getResults() {
        results.forEach(System.out::println);
        return results;
    }

    public void addResult(Result point) {
        results.add(point);
    }
    public void updatePoints(float radius) {
        for (Result point : results) {
            point.setR(radius);
            point.setResult(Validator.check(point));
        }
    }
    public void clearPoints() {
        results.clear();
    }
}
