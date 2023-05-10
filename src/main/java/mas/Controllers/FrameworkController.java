package mas.Controllers;

import mas.Models.Framework;
import mas.Repositories.FrameworkRepository;
import mas.Services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("framework")
public class FrameworkController {

    @Autowired
    FrameworkRepository repository;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Framework> listOfRows = (List<Framework>) repository.findAll();
        Pair<List<String>, List<List<String>>> pair = new BaseService().getFieldsNames(Framework.class, listOfRows);
        model.addAttribute("fieldsList", pair.getFirst());
        model.addAttribute("listOfListOfProperties", pair.getSecond());
        return "baseModelsList";
    }
}
