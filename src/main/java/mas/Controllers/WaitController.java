package mas.Controllers;

import mas.ModelDTO.CodeProjectDto;
import mas.Models.Employee;
import mas.Models.EmployeeInitiative;
import mas.Models.Initiative;
import mas.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;

@Controller
@RequestMapping("wait")
public class WaitController implements CommandLineRunner {

    @Autowired
    CodeProjectRepository repository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    InitiativeRepository initiativeRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    EmployeeInitiativeRepository employeeInitiativeRepository;

    @GetMapping(value = "/get")
    public String getWait(ModelMap modelMap, @ModelAttribute("name") String name, @ModelAttribute("empName") String empName, RedirectAttributes redirectAttributes) {

        return "waiter";
    }

    @Transactional
    @GetMapping(value = "/add")
    public RedirectView addCodeProject(ModelMap model, @ModelAttribute("name") String name, @ModelAttribute("empName") String empName) {
        run(name+" - "+empName);

        Initiative init = initiativeRepository.findByName(name);

        long empId =  personRepository.findByFirstName(empName);
        Employee emp = employeeRepository.findById(empId).get();

        employeeInitiativeRepository.save(new EmployeeInitiative(emp, init, false));

        return new RedirectView("/classes/show");
    }

    @Override
    public void run(String... args) {
        System.out.println(Arrays.toString(args));
    }
}
