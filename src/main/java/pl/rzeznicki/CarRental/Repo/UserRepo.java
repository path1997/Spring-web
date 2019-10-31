package pl.rzeznicki.CarRental.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rzeznicki.CarRental.Color;
import pl.rzeznicki.CarRental.Entity.Car;
import pl.rzeznicki.CarRental.Entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
}
