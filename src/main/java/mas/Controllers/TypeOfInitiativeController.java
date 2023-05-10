package mas.Controllers;

import mas.Models.TypeOfInitiative;
import mas.Repositories.TypeOfInitiativeRepository;
import mas.Services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("typeofinitiative")
public class TypeOfInitiativeController {
    @Autowired
    TypeOfInitiativeRepository repository;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<TypeOfInitiative> listOfRows = (List<TypeOfInitiative>) repository.findAll();
        Pair<List<String>, List<List<String>>> pair = new BaseService().getFieldsNames(TypeOfInitiative.class, listOfRows);
        model.addAttribute("fieldsList", pair.getFirst());
        model.addAttribute("listOfListOfProperties", pair.getSecond());

        return "baseModelsList";
    }
    @GetMapping(value = "/getById", params = "id")
    public String getById(long id, Model model){
        TypeOfInitiative c = repository.findById(id).get();
        List<TypeOfInitiative> listOfRows = new ArrayList<>();
        listOfRows.add(c);
        new BaseService().setModelToShowAll(TypeOfInitiative.class, listOfRows, model);

        return "baseModelsList";
    }
    @Transactional
    @PostMapping(value = "/add")
    public @ResponseBody TypeOfInitiative addCountry(@RequestBody TypeOfInitiative entity){
        return repository.save(entity);
    }
}
