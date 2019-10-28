package pl.rzeznicki.springdataexample.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rzeznicki.springdataexample.Color;
import pl.rzeznicki.springdataexample.Entity.Car;

import java.util.List;

@Repository

public interface CarRepo extends CrudRepository<Car, Long> {
    List<Car> findAllByColor(Color color);

}
