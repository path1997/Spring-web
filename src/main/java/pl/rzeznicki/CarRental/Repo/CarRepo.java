package pl.rzeznicki.CarRental.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rzeznicki.CarRental.Color;
import pl.rzeznicki.CarRental.Entity.Car;

import java.util.List;

@Repository

public interface CarRepo extends CrudRepository<Car, Long> {
    List<Car> findAllByColor(Color color);
    List<Car> findAllByRental_id(long id);
}
