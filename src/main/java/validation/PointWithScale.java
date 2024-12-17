package validation;

import jakarta.persistence.Column;

import java.io.Serializable;

public class PointWithScale implements Serializable {

    public PointWithScale(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Column(name = "x", nullable = false)
    @Validatable(min = -5, max = 3)
    public final double x;

    @Column(name = "y", nullable = false)
    @Validatable(min = -5, max = 3)
    public final double y;

    @Column(name = "r", nullable = false)
    @Validatable(min = 1, max = 4)
    public final double r;

    public String getX() {
        return Double.toString(x);
    }

    public String getY() {
        return Double.toString(y);
    }

    public String getR() {
        return Double.toString(r);
    }

    public String toString() {
        return "PointWithScale [x=" + x + ", y=" + y + ", r=" + r + "]";
    }
}
