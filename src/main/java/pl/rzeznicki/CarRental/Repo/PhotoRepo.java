package pl.rzeznicki.CarRental.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rzeznicki.CarRental.Entity.Orders;
import pl.rzeznicki.CarRental.Entity.Photo;

@Repository

public interface PhotoRepo extends CrudRepository<Photo, Long> {
}
