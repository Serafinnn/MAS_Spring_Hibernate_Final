package mas.Controllers;

import mas.Models.CodeProject;
import mas.Models.Initiative;
import mas.Repositories.InitiativeRepository;
import mas.Services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("initiative")
public class InitiativeController implements CommandLineRunner {

    @Autowired
    InitiativeRepository initiativeRepository;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Initiative> listOfRows = (List<Initiative>) initiativeRepository.findAll();
        run(listOfRows.toString());
        new BaseService().setModelToShowAll(Initiative.class, listOfRows, model);

        return "baseModelsList";
    }

    @Override
    public void run(String... args) {
        System.out.println("=============");
        System.out.println(Arrays.toString(args));
        System.out.println("=============");
    }
}
