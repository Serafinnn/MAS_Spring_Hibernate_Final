package mas.Controllers;

import mas.Models.Country;
import mas.Repositories.CountryRepository;
import mas.Services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("country")
public class CountryController {

    @Autowired
    CountryRepository repository;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Country> listOfRows = (List<Country>) repository.findAll();
        new BaseService().setModelToShowAll(Country.class, listOfRows, model);

        return "baseModelsList";
    }

    @GetMapping("/showForm")
    public String showForm(Model model) {
        new BaseService().setModelToForm(Country.class, model);

        return "baseAddForm";
    }
    @GetMapping(value = "/getById", params = "id")
    public String getById(long id, Model model){
        Country c = repository.findById(id).get();
        List<Country> listOfRows = new ArrayList<>();
        listOfRows.add(c);
        new BaseService().setModelToShowAll(Country.class, listOfRows, model);

        return "baseModelsList";
    }
    @Transactional
    @PostMapping(value = "/add")
    public String addCountry(Country country, Model model){
        String alert = "";
        if (!repository.exists(country.getShortName(), country.getFullName())){
            repository.save(country);
        }else {
            alert = "Unique constrains prevented adding new Country";
        }

        model.addAttribute("alertTxt", alert);

        return "redirect:/classes/show";
    }
}
