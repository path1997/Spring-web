package pl.rzeznicki.CarRental.Repo;

import org.springframework.data.repository.CrudRepository;
import pl.rzeznicki.CarRental.Entity.Rental;
import pl.rzeznicki.CarRental.Entity.User;

import java.util.List;

public interface RentalRepo extends CrudRepository<Rental,Long> {
    List<User> findAllByCity(String city);
}
