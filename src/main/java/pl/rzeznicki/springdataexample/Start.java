package pl.rzeznicki.springdataexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/*@Component
public class Start {

    private CarRepo carRepo;

    @Autowired
    public Start(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample() {
        Car car= new Car("Honda","Jazz",Color.RED);
        carRepo.save(car);

        Car car1= new Car("Audi","A3",Color.BLUE);
        carRepo.save(car1);

        Car car2= new Car("BMW","e39",Color.BLACK);
        carRepo.save(car2);

//        carRepo.deleteById(1L);

//        Iterable<Car> all= carRepo.findAll();
//        Iterable<Car> all= carRepo.findAllByColor(Color.BLACK);
//        all.forEach(System.out::println);
    }
}*/
