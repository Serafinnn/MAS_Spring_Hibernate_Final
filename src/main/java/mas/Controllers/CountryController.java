package mas.Controllers;

import mas.Models.Country;
import mas.Repositories.CountryRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("country")
public class CountryController {

    @Autowired
    CountryRepository repository;

    @GetMapping("/getAll")
    public @ResponseBody List<Country> getAll(){
        return repository.findAll();
    }
}
