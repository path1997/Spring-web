package pl.rzeznicki.springdataexample.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rzeznicki.springdataexample.Entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
}
