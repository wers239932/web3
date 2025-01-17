package database;

import jakarta.persistence.*;
import validation.Validatable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "result")
public class Result implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Validatable(min = -5, max = 3)
    @Column(name = "x", nullable = false)
    private float x;

    @Validatable(min = -5, max = 5)
    @Column(name = "y", nullable = false)
    private float y;

    @Validatable(min = 1, max = 3)
    @Column(name = "r", nullable = false)
    private float r;

    @Column(name = "result", nullable = false)
    private boolean result;

    @Column(name = "creationTime", nullable = false)
    private LocalDateTime creationTime;

    @Column(name = "timeWork", nullable = false)
    private long timeWork;

    @Column(name = "owner_id")
    private Integer ownerId;


    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", result=" + result +
                ", creationTime=" + creationTime +
                ", timeWork=" + timeWork +
                '}';
    }

    public String getTime() {
        return creationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    public long getTimeWork() {
        return timeWork;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void setTimeWork(long timeWork) {
        this.timeWork = timeWork;
    }

    public boolean getResult() {
        return result;
    }

    public String getConvCreationTime() {
        return creationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
