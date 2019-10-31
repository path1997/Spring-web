package pl.rzeznicki.springdataexample.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "rental")
    private List<Car> car;

    private String city;
    //@Transient
    private String address;
    private String phone;
    private String email;

    public Rental() {
    }

    public Rental(List<Car> car, String city, String address, String phone, String email) {
        this.car = car;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Rental(String city, String address, String phone, String email) {
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", car=" + car +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
