package pl.rzeznicki.CarRental.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private int pozycja;

    public Photo(Car car, int pozycja, String sciezka) {
        this.car = car;
        this.pozycja = pozycja;
        this.sciezka = sciezka;
    }

    public int getPozycja() {
        return pozycja;
    }

    public void setPozycja(int pozycja) {
        this.pozycja = pozycja;
    }

    public String getSciezka() {
        return sciezka;
    }


    public void setSciezka(String sciezka) {
        this.sciezka = sciezka;
    }

    public Photo(Car car, String sciezka) {
        this.car = car;
        this.sciezka = sciezka;
    }

    private String sciezka;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Photo(Car car) {
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Photo() {
    }
}