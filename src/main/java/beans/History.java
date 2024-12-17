package beans;

import java.beans.Beans;
import java.util.ArrayList;


public class History extends Beans {
    private final ArrayList<Result> history;

    public History() {
        this.history = new ArrayList<>();
    }

    public void add(Result result) {
        this.history.add(result);
    }

    public ArrayList<Result> getHistory() {
        return history;
    }
}
