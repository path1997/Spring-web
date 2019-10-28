package pl.rzeznicki.springdataexample.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import pl.rzeznicki.springdataexample.Entity.Car;
import pl.rzeznicki.springdataexample.Entity.User;
import pl.rzeznicki.springdataexample.Repo.CarRepo;
import pl.rzeznicki.springdataexample.Repo.UserRepo;

import javax.validation.Valid;
import java.util.List;

/*
@Controller
public class TestController {
    @Autowired
    CarRepo carRepo;
    @GetMapping("/elo")
    public ModelAndView testMapping() {

        Car car1= new Car("Audi","A3",Color.BLUE);
        carRepo.save(car1);
        car1= new Car("BMW","E46",Color.BLUE);
        carRepo.save(car1);
        Iterable<Car> all= carRepo.findAll();
        //System.out.println(all);
        ModelAndView model = new ModelAndView("test");
        model.addObject("tescior", new TestM(10, "xyz"));
        model.addObject("cars", all);
       // model.addObject("zapisz",carRepo.save(new Car("Mazda","323",Color.BLACK)));


        //all.forEach(System.out::println);
        return model;
    }
    */
/*@GetMapping("/car")
    public String carForm(Model model) {
        model.addAttribute("car", new Car());
        return "car";
    }
    @PostMapping("/car")
    public String createCar(Car car, Model model) {
        carRepo.save(car);
        return "car";
    }
    @GetMapping("/car")
    public String getCars(Model model) {
        Iterable<Car> all= carRepo.findAll();
        model.addAttribute("cars" , all);
        return "cars";
    }*//*


    }*/
@Controller
public class MainController {

    private final CarRepo carRepo;
    private final UserRepo userRepo;


    @Autowired
    public MainController(CarRepo carRepo, UserRepo userRepo) {
        this.carRepo = carRepo;
        this.userRepo=userRepo;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        return "index";
    }
    @PostMapping("/addcar")
    public RedirectView setCar(@Valid Car car, Model model){
        carRepo.save(car);
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        return new RedirectView("/");
    }
    @GetMapping("/signup")
    public String showSignUpForm(Car car, Model model) {
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        return "addCar";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Car car = carRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        model.addAttribute("car", car);
        return "updateCar";
    }

    @PostMapping("/update/{id}")
    public RedirectView updateCar(@PathVariable("id") long id, @Valid Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            car.setId(id);
            return new RedirectView("/update/{id}");
        }

        carRepo.save(car);
        model.addAttribute("cars", carRepo.findAll());
        return new RedirectView("/");
    }
    @GetMapping("/delete/{id}")
    public RedirectView deleteCar(@PathVariable("id") long id, Model model) {
        Car car = carRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        carRepo.delete(car);
        model.addAttribute("cars", carRepo.findAll());
        return new RedirectView("/");
    }
    @GetMapping("/registerUser")
    public String showRegisterUserForm(User user) {
        return "addUser";
    }

    @PostMapping("/adduser")
    public RedirectView setUser(@Valid User user, Model model){
        userRepo.save(user);
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("cars", carRepo.findAll());
        return new RedirectView("/");
    }
    @GetMapping("/logUser")
    public String loginUser(User car, Model model) {

        return "login";
    }
    @PostMapping("/logfinal")
    public RedirectView logFinal(@Valid User user, Model model){
        String imie=user.getFname();
        /*System.out.println(imie);*/
        String nazwisko=user.getSname();
        /*System.out.println(nazwisko);*/
        List<User> uzytkownicy= (List<User>) userRepo.findAll();
        for (User uzyt : uzytkownicy ) {
            /*System.out.println(uzyt.getFname());
            System.out.println(uzyt.getSname());*/
            if (uzyt.getFname().equals(imie) && uzyt.getSname().equals(nazwisko)) {
                System.out.println("zalogowano");
                return new RedirectView("/zalogowano/{id}");
            }
        }
        System.out.println("nie zalogowano");
        return new RedirectView("/");
    }



}