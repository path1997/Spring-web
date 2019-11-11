package pl.rzeznicki.CarRental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import pl.rzeznicki.CarRental.Entity.*;
import pl.rzeznicki.CarRental.Repo.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private final CarRepo carRepo;
    private final UserRepo userRepo;
    private final RentalRepo rentalRepo;
    private final OrdersRepo ordersRepo;
    private final PhotoRepo photoRepo;

    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/";


    @Autowired
    public MainController(CarRepo carRepo, UserRepo userRepo, RentalRepo rentalRepo, OrdersRepo ordersRepo, PhotoRepo photoRepo) {
        this.carRepo = carRepo;
        this.userRepo=userRepo;
        this.rentalRepo=rentalRepo;
        this.ordersRepo = ordersRepo;
        this.photoRepo = photoRepo;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("rentals", rentalRepo.findAll());
        model.addAttribute("photos", photoRepo.findAll());
        return "index";
    }
    @GetMapping("/cos/{id}")
    public ResponseEntity<?> getRentalCars(@PathVariable("id")Long id){
        Optional<Rental> l = rentalRepo.findById(id);
        if(l.isPresent()){
            if(id!=0) {
                return new ResponseEntity<>(l.get().getCar(), HttpStatus.OK);
            }
        }
        return null;
    }
    @GetMapping("/admin")
    public String showAdmin(Model model) {
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("rentals", rentalRepo.findAll());
        return "admin";
    }
    @PostMapping("/addcar")
    public RedirectView setCar(@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,@RequestParam("file3") MultipartFile file3, @Valid Car car, Model model){
        long id=1;
        User user=new User();
        user.setId(id);
        car.setUser(user);
        carRepo.save(car);

        if (file1.isEmpty()) {
        } else {
            try {
                Photo photo=new Photo();
                // Get the file and save it somewhere
                byte[] bytes = file1.getBytes();
                photo.setCar(car);
                photo.setSciezka(file1.getOriginalFilename());
                photo.setPozycja(1);
                photoRepo.save(photo);
                Path path = Paths.get(uploadDirectory + file1.getOriginalFilename());
                Files.write(path, bytes);
                //car.setPhoto(file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file2.isEmpty()) {
        } else {
            try {
                Photo photo=new Photo();
                // Get the file and save it somewhere
                byte[] bytes = file2.getBytes();
                photo.setCar(car);
                photo.setSciezka(file2.getOriginalFilename());
                photo.setPozycja(2);
                photoRepo.save(photo);
                Path path = Paths.get(uploadDirectory + file2.getOriginalFilename());
                Files.write(path, bytes);
                //car.setPhoto(file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file3.isEmpty()) {
        } else {
            try {
                Photo photo=new Photo();
                // Get the file and save it somewhere
                byte[] bytes = file3.getBytes();
                photo.setCar(car);
                photo.setSciezka(file3.getOriginalFilename());
                photo.setPozycja(3);
                photoRepo.save(photo);
                Path path = Paths.get(uploadDirectory + file3.getOriginalFilename());
                Files.write(path, bytes);
                //car.setPhoto(file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        boolean status1=false;
        boolean status2=false;
        boolean status3=false;
        model.addAttribute("checkbox1", status1);
        model.addAttribute("checkbox2", status2);
        model.addAttribute("checkbox3", status3);
        model.addAttribute("rentals", rentalRepo.findAll());
        model.addAttribute("photos", photoRepo.findAll());
        return "updateCar";
    }

    @PostMapping("/updatecar/{id}")
    public RedirectView updateCar(@PathVariable("id") long id,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,@RequestParam("file3") MultipartFile file3,@RequestParam(value = "usun1", required = false) String usun1, @RequestParam(value = "usun2", required = false) String usun2,@RequestParam(value = "usun3", required = false) String usun3, @Valid Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            car.setId(id);
            return new RedirectView("/updatecar/{id}");
        }
        if (file1.isEmpty() && usun1!=null) {
            for(Photo fotka: photoRepo.findAll()){
                if(fotka.getCar().getId()==car.getId() && fotka.getPozycja()==1){
                    File file = new File(uploadDirectory + fotka.getSciezka());
                    file.delete();
                    photoRepo.delete(fotka);
                }
            }
        } else if(file1.isEmpty()) {
        } else {
            try {
                for(Photo fotka: photoRepo.findAll()){
                    if(fotka.getCar().getId()==car.getId() && fotka.getPozycja()==1){
                        File file = new File(uploadDirectory + fotka.getSciezka());
                        file.delete();
                        photoRepo.delete(fotka);
                    }
                }

                // Get the file and save it somewhere
                byte[] bytes = file1.getBytes();
                Photo photo=new Photo();
                photo.setPozycja(1);
                photo.setCar(car);
                photo.setSciezka(file1.getOriginalFilename());
                photoRepo.save(photo);
                Path path = Paths.get(uploadDirectory + file1.getOriginalFilename());
                Files.write(path, bytes);
               // car.setPhoto(file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file2.isEmpty() && usun2!=null) {
            for(Photo fotka: photoRepo.findAll()){
                if(fotka.getCar().getId()==car.getId() && fotka.getPozycja()==2){
                    File file = new File(uploadDirectory + fotka.getSciezka());
                    file.delete();
                    photoRepo.delete(fotka);
                }
            }
        } else if(file2.isEmpty()) {
        } else {
            try {
                for(Photo fotka: photoRepo.findAll()){
                    if(fotka.getCar().getId()==car.getId() && fotka.getPozycja()==2){
                        File file = new File(uploadDirectory + fotka.getSciezka());
                        file.delete();
                        photoRepo.delete(fotka);
                    }
                }
                // Get the file and save it somewhere
                byte[] bytes = file2.getBytes();
                Photo photo=new Photo();
                photo.setPozycja(2);
                photo.setCar(car);
                photo.setSciezka(file2.getOriginalFilename());
                photoRepo.save(photo);
                Path path = Paths.get(uploadDirectory + file2.getOriginalFilename());
                Files.write(path, bytes);
                // car.setPhoto(file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file3.isEmpty() && usun3!=null) {
            for(Photo fotka: photoRepo.findAll()){
                if(fotka.getCar().getId()==car.getId() && fotka.getPozycja()==3){
                    File file = new File(uploadDirectory + fotka.getSciezka());
                    file.delete();
                    photoRepo.delete(fotka);
                }
            }
        } else if(file3.isEmpty()) {
        } else {
            try {
                for(Photo fotka: photoRepo.findAll()){
                    if(fotka.getCar().getId()==car.getId() && fotka.getPozycja()==3){
                        File file = new File(uploadDirectory + fotka.getSciezka());
                        file.delete();
                        photoRepo.delete(fotka);
                    }
                }
                // Get the file and save it somewhere
                byte[] bytes = file3.getBytes();
                Photo photo=new Photo();
                photo.setPozycja(3);
                photo.setCar(car);
                photo.setSciezka(file3.getOriginalFilename());
                photoRepo.save(photo);
                Path path = Paths.get(uploadDirectory + file3.getOriginalFilename());
                Files.write(path, bytes);
                // car.setPhoto(file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        carRepo.save(car);
        model.addAttribute("photos", photoRepo.findAll());
        model.addAttribute("cars", carRepo.findAll());
        return new RedirectView("/admin");
    }
    @GetMapping("/deletecar/{id}")
    public RedirectView deleteCar(@PathVariable("id") long id, Model model) {
        Car car = carRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        //File file1=new File(uploadDirectory+car.getPhoto());
        //file1.delete();
        List<Orders> zamowienia=car.getOrders();
        List<Photo> fotki=car.getPhoto();
        for(Photo fot:fotki){
            File file1=new File(uploadDirectory+fot.getSciezka());
            file1.delete();
            photoRepo.delete(fot);
        }
        for(Orders zam:zamowienia){
            ordersRepo.delete(zam);
        }
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
        String dane="";
        model.addAttribute("komunikat",dane);
        return "login";
    }
    @GetMapping("/logUser/1")
    public String loginUserFail(User car, Model model) {
        String dane="Nieprawidlowe dane";
        model.addAttribute("komunikat",dane);
        return "login";
    }
    @PostMapping("/loginuser")
    @ResponseBody
    public RedirectView logFinal(@Valid User user, Model model){
        String imie=user.getFname();
        String nazwisko=user.getSname();
        String haslo=user.getPassword();
        List<User> uzytkownicy= (List<User>) userRepo.findAll();
        for (User uzyt : uzytkownicy ) {
            if (uzyt.getFname().equals(imie) && uzyt.getSname().equals(nazwisko) && uzyt.getAdmin()==1 && uzyt.getPassword().equals(haslo)) {
                return new RedirectView("/admin");
            } else if (uzyt.getFname().equals(imie) && uzyt.getSname().equals(nazwisko) && uzyt.getPassword().equals(haslo)) {
                return new RedirectView("/user/"+uzyt.getId());
            }
        }
        return new RedirectView("/logUser/1");
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
            if(sam.getRental().getId()==id){
                sam.setRental(rental1);
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
                sam.setState(0);
                sam.setUser(uzytkownik);
                carRepo.save(sam);
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
        model.addAttribute("photos", photoRepo.findAll());
        model.addAttribute("idUser",id);
        return "userPanel";
    }
    @PostMapping("/returncar/{id}/{idC}")
    public RedirectView showUserPanelRental(@PathVariable("id") long id, @PathVariable("idC") long idC, Car car, Model model) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        Car car2=carRepo.findById(idC).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        List<Orders> zamowienia= (List<Orders>) ordersRepo.findAll();
        for(Orders order: zamowienia){
            if(order.getUser().getId()==user.getId() && order.getCar().getId()==car2.getId() && order.getData_oddania()==null){
                order.setData_oddania(new Date());
                ordersRepo.save(order);
                break;
            }
        }
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
        //long idC1=idC;
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        Car car = carRepo.findById(idC).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        Orders order=new Orders();
        car.setState(1);
        car.setUser(user);
        order.setCar(car);
        order.setUser(user);
        order.setData_wypozyczenia(new Date());
        ordersRepo.save(order);
        carRepo.save(car);


        model.addAttribute("user", user);
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("rentals", rentalRepo.findAll());
        return new RedirectView("/user/{id}");
    }

    @GetMapping("/orderhistory/{id}")
    public String showorderhistory(@PathVariable("id") long id, Model model) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id:" + id));
        model.addAttribute("orders", user.getOrders());
        model.addAttribute("id",id);
        return "orderHistory";
    }

}