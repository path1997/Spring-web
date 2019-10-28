package pl.rzeznicki.springdataexample.Entity;

import pl.rzeznicki.springdataexample.Color;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car(User user, String mark, String model, Color color) {
        this.user = user;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    private String mark;
    //@Transient
    private String model;
    @Enumerated(EnumType.STRING)
    private Color color;

    public Car(String mark, String model, Color color) {
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color=" + color +
                '}';
    }
}
