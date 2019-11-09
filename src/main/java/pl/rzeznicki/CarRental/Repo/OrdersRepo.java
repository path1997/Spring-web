package pl.rzeznicki.CarRental.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rzeznicki.CarRental.Entity.Car;
import pl.rzeznicki.CarRental.Entity.Orders;

import java.util.List;

@Repository

public interface OrdersRepo extends CrudRepository<Orders, Long> {
}
