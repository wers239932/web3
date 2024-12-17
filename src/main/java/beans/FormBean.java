package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import repository.Repository;
import validation.Validator;

import java.time.LocalDateTime;

@RequestScoped
@Named("formBean")
public class FormBean {
    @Inject
    private Repository databaseService;

    @Inject
    private ResultBean resultBean;

    public void processForm(Result point) {
        System.out.println("processForm");
        long startTime = System.nanoTime();
        point.setResult(Validator.check(point));
        point.setCreationTime(LocalDateTime.now());
        long endTime = System.nanoTime();
        point.setTimeWork(endTime - startTime);
        databaseService.save(point);
        resultBean.addResult(point);
        System.out.println(point.toString());
    }

    public void changeR(float r) {
        databaseService.updateAll(r);
        resultBean.updatePoints(r);
        System.out.println(r);
    }

    private boolean shouldUpdateAllPoints(float radius) {
        return !resultBean.getResults().isEmpty() && resultBean.getResults().get(0).getR() != radius;
    }

    public void processClean() {
        databaseService.clear();
        resultBean.clearPoints();
    }
}
