package pl.rzeznicki.springdataexample.Repo;

import org.springframework.data.repository.CrudRepository;
import pl.rzeznicki.springdataexample.Entity.Rental;

public interface RentalRepo extends CrudRepository<Rental,Long> {

}
