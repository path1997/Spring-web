package pl.rzeznicki.CarRental.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private Date data_wypozyczenia;
    private Date data_oddania;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getData_wypozyczenia() {
        return data_wypozyczenia;
    }

    public void setData_wypozyczenia(Date data_wypozyczenia) {
        this.data_wypozyczenia = data_wypozyczenia;
    }

    public Date getData_oddania() {
        return data_oddania;
    }

    public void setData_oddania(Date data_oddania) {
        this.data_oddania = data_oddania;
    }

    public Orders(User user, Car car, Date data_wypozyczenia, Date data_oddania) {
        this.user = user;
        this.car = car;
        this.data_wypozyczenia = data_wypozyczenia;
        this.data_oddania = data_oddania;
    }

    public Orders() {
    }
}

