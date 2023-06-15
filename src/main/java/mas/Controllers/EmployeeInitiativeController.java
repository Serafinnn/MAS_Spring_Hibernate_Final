package mas.Controllers;

import mas.Models.Country;
import mas.Models.EmployeeInitiative;
import mas.Repositories.EmployeeInitiativeRepository;
import mas.Services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("employeeinitiative")
public class EmployeeInitiativeController {

    @Autowired
    EmployeeInitiativeRepository repository;


    @GetMapping("/all")
    public String getAll(Model model, RedirectAttributes redirectAttributes) {
        List<EmployeeInitiative> listOfRows = (List<EmployeeInitiative>) repository.findAll();
        if (listOfRows.size() == 0){
            redirectAttributes.addFlashAttribute("alertAdd", 4);
            return "redirect:/classes/show";
        }
        new BaseService().setModelToShowAll(EmployeeInitiative.class, listOfRows, model);
        return "baseModelsList";
    }
}
