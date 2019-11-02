package pl.rzeznicki.CarRental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import pl.rzeznicki.CarRental.Entity.Car;
import pl.rzeznicki.CarRental.Entity.Rental;
import pl.rzeznicki.CarRental.Entity.User;
import pl.rzeznicki.CarRental.Repo.CarRepo;
import pl.rzeznicki.CarRental.Repo.RentalRepo;
import pl.rzeznicki.CarRental.Repo.UserRepo;

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
    private final RentalRepo rentalRepo;


    @Autowired
    public MainController(CarRepo carRepo, UserRepo userRepo,RentalRepo rentalRepo) {
        this.carRepo = carRepo;
        this.userRepo=userRepo;
        this.rentalRepo=rentalRepo;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        return "index";
    }
    @GetMapping("/admin")
    public String showAdmin(Model model) {
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("rentals", rentalRepo.findAll());
        return "admin";
    }
    @PostMapping("/addcar")
    public RedirectView setCar(@Valid Car car, Model model){
        carRepo.save(car);
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("rentals", rentalRepo.findAll());
        return new RedirectView("/admin");
    }
    @GetMapping("/addcarview")
    public String showSignUpForm(Car car, Model model) {
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("rentals", rentalRepo.findAll());
        return "addCar";
    }

    @GetMapping("/editcar/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Car car = carRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        model.addAttribute("car", car);
        model.addAttribute("rentals", rentalRepo.findAll());
        return "updateCar";
    }

    @PostMapping("/updatecar/{id}")
    public RedirectView updateCar(@PathVariable("id") long id, @Valid Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            car.setId(id);
            return new RedirectView("/updatecar/{id}");
        }

        carRepo.save(car);
        model.addAttribute("cars", carRepo.findAll());
        return new RedirectView("/admin");
    }
    @GetMapping("/deletecar/{id}")
    public RedirectView deleteCar(@PathVariable("id") long id, Model model) {
        Car car = carRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        carRepo.delete(car);
        model.addAttribute("cars", carRepo.findAll());
        return new RedirectView("/admin");
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
    @PostMapping("/loginuser")
    @ResponseBody
    public RedirectView logFinal(@Valid User user, Model model){
        String imie=user.getFname();
        /*System.out.println(imie);*/
        String nazwisko=user.getSname();
        String haslo=user.getPassword();
        /*System.out.println(nazwisko);*/
        List<User> uzytkownicy= (List<User>) userRepo.findAll();
        for (User uzyt : uzytkownicy ) {
            /*System.out.println(uzyt.getFname());
            System.out.println(uzyt.getSname());*/
            if (uzyt.getFname().equals(imie) && uzyt.getSname().equals(nazwisko) && uzyt.getAdmin()==1 && uzyt.getPassword().equals(haslo)) {
                return new RedirectView("/admin");
            } else if (uzyt.getFname().equals(imie) && uzyt.getSname().equals(nazwisko) && uzyt.getPassword().equals(haslo)) {
                return new RedirectView("/user/"+uzyt.getId());
            }
        }
        System.out.println("nie zalogowano");
        return new RedirectView("/logUser");
    }
    @GetMapping("/registerRental")
    public String showRegisterRentalForm(Rental rental) {
        return "addrental";
    }

    @PostMapping("/addrental")
    public RedirectView setRental(@Valid Rental rental, Model model){
        rentalRepo.save(rental);
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("rentals", rentalRepo.findAll());
        return new RedirectView("/admin");
    }
    @GetMapping("/editrental/{id}")
    public String showUpdateRental(@PathVariable("id") long id, Model model) {
        Rental rental = rentalRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        model.addAttribute("rental", rental);
        return "updateRental";
    }

    @PostMapping("/updaterental/{id}")
    public RedirectView updateRental(@PathVariable("id") long id, @Valid Rental rental, BindingResult result, Model model) {
        if (result.hasErrors()) {
            rental.setId(id);
            return new RedirectView("/updaterental/{id}");
        }

        rentalRepo.save(rental);
        model.addAttribute("rentals", rentalRepo.findAll());
        return new RedirectView("/admin");
    }
    @GetMapping("/deleterental/{id}")
    public RedirectView deleteRental(@PathVariable("id") long id, Model model) {
        Rental rental = rentalRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        long liczba=1;
        List<Car> samochody= (List<Car>) carRepo.findAll();
        Rental rental1=new Rental();
        rental1.setId(liczba);
        for (Car sam : samochody ) {
            System.out.println("id"+id);
            System.out.println("samid"+sam.getRental().getId());
            if(sam.getRental().getId()==id){
                sam.setRental(rental1);
                System.out.println("po zmianie"+sam.getRental().getId());
                System.out.println(sam);
                carRepo.save(sam);
            }
        }
        rentalRepo.delete(rental);
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("rentals", rentalRepo.findAll());
        return new RedirectView("/admin");
    }
    @GetMapping("/deleteuser/{id}")
    public RedirectView deleteUser(@PathVariable("id") long id, Model model) {
        List<Car> samochody= (List<Car>) carRepo.findAll();
        User uzytkownik=new User();
        long liczba=1;
        uzytkownik.setId(liczba);
        for (Car sam : samochody ) {
            if(sam.getUser().getId()==id){
                System.out.println("1");
                sam.setState(0);
                sam.setUser(uzytkownik);
                System.out.println("2");
                carRepo.save(sam);
                System.out.println("3");
            }
        }
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        userRepo.delete(user);

        model.addAttribute("users", userRepo.findAll());
        return new RedirectView("/admin");
    }
    @GetMapping("/edituser/{e}/{id}")
    public String showUpdateUser(@PathVariable("e") int e,@PathVariable("id") long id, Model model) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        model.addAttribute("user", user);
        model.addAttribute("panel",e);
        return "updateuser";
    }

    @PostMapping("/updateuser/{e}/{id}")
    public RedirectView updateUser(@PathVariable("e") int e,@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return new RedirectView("/updateuser/{id}");
        }

        userRepo.save(user);
        model.addAttribute("users", rentalRepo.findAll());
        if(e==1) {
            return new RedirectView("/admin");
        } else {
            return new RedirectView("/user/"+id);
        }
    }
    @GetMapping("/user/{id}")
    public String showUserPanel(@PathVariable("id") long id,Car car, Model model) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        model.addAttribute("user", user);
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("rentals", rentalRepo.findAll());
        return "userPanel";
    }
    @PostMapping("/returncar/{id}/{idC}")
    public RedirectView showUserPanelRental(@PathVariable("id") long id, @PathVariable("idC") long idC, Car car, Model model) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        Car car2=carRepo.findById(idC).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        car2.setState(0);
        Rental rental=new Rental();
        rental.setId(car.getRental().getId());
        car2.setRental(rental);
        carRepo.save(car2);
        model.addAttribute("user", user);
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("rentals", rentalRepo.findAll());
        return new RedirectView("/user/{id}");
    }

    @GetMapping("/getcar/{id}/{idC}")
    public RedirectView showGetCar(@PathVariable("id") long id, @PathVariable("idC") long idC, Model model) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        Car car = carRepo.findById(idC).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        car.setState(1);
        car.setUser(user);
        carRepo.save(car);

        model.addAttribute("user", user);
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("rentals", rentalRepo.findAll());
        return new RedirectView("/user/{id}");
    }

}