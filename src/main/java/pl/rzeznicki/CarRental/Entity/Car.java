package pl.rzeznicki.CarRental.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @OneToMany(mappedBy = "car")
    private List<Orders> orders;

    public Car(User user, Rental rental, List<Orders> orders, List<Photo> photo, String mark, int state, String model, String color, int year, int price) {
        this.user = user;
        this.rental = rental;
        this.orders = orders;
        this.photo = photo;
        this.mark = mark;
        this.state = state;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

    @OneToMany(mappedBy = "car")
    private List<Photo> photo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    private String mark;
    private int state;
    //@Transient
    private String model;
    private String color;
    private int year;
    private int price;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Car(User user, Rental rental, String mark, int state, String model, String color, int year, int price) {
        this.user = user;
        this.rental = rental;
        this.mark = mark;
        this.state = state;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
