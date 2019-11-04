package pl.rzeznicki.CarRental.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rzeznicki.CarRental.Entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
}
