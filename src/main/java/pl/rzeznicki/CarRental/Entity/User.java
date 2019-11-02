package pl.rzeznicki.CarRental.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Car> car;

    private String fname;
    //@Transient
    private String sname;
    private String phone;
    private String email;
    private int admin;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(List<Car> car, String fname, String sname, String phone, String email, int admin, String password) {
        this.car = car;
        this.fname = fname;
        this.sname = sname;
        this.phone = phone;
        this.email = email;
        this.admin = admin;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", car=" + car +
                ", fname='" + fname + '\'' +
                ", sname='" + sname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", admin='" + admin + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public User(List<Car> car, String fname, String sname, String phone, String email, int admin) {
        this.car = car;
        this.fname = fname;
        this.sname = sname;
        this.phone = phone;
        this.email = email;
        this.admin = admin;
    }

    public User() {
    }

    public User(String fname, String sname, String phone, String email) {
        this.fname = fname;
        this.sname = sname;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
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
