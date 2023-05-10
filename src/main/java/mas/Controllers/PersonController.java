package mas.Controllers;

import mas.Models.Person;
import mas.Repositories.PersonRepository;
import mas.Services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("person")
public class PersonController {
    @Autowired
    PersonRepository repository;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Person> listOfRows = (List<Person>) repository.findAll();
        new BaseService().setModelToShowAll(Person.class, listOfRows, model);

        return "baseModelsList";
    }
    @GetMapping(value = "/getById", params = "id")
    public String getById(long id, Model model){
        Person c = repository.findById(id).get();
        List<Person> listOfRows = new ArrayList<>();
        listOfRows.add(c);
        new BaseService().setModelToShowAll(Person.class, listOfRows, model);

        return "baseModelsList";
    }
}