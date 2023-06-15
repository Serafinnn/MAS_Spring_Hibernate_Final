package mas.Controllers;

import mas.ModelDTO.CodeProjectDto;
import mas.Models.CodeProject;
import mas.Repositories.*;
import mas.Services.BaseService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("codeproject")
public class CodeProjectController implements CommandLineRunner {

    @Autowired
    CodeProjectRepository repository;
    @Autowired
    FrameworkRepository frameworkRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    InitiativeRepository initiativeRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/all")
    public java.lang.String getAll(Model model) {
        List<CodeProject> listOfRows = (List<CodeProject>) repository.findAll();
        new BaseService().setModelToShowAll(CodeProject.class, listOfRows, model);

        return "baseModelsList";
    }

    @GetMapping("/showForm")
    public java.lang.String showForm(Model model) {
        new BaseService().setModelToForm(CodeProject.class, model);
        List<java.lang.String> frameworks = frameworkRepository.getNames();
        List<String> employees = personRepository.getNames();
        CodeProjectDto c = new CodeProjectDto(null, null, null, null,null, null);

        model.addAttribute("frameworks",frameworks);
        model.addAttribute("employees",employees);
        model.addAttribute("codeProjectDto", c);
        return "baseAddFormInit";
    }
    @Transactional
    @PostMapping(value = "/add")
    public RedirectView addCodeProject(@ModelAttribute(name = "codeProjectDto") CodeProjectDto codeProjectDto, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

        if (repository.countByName(codeProjectDto.getName())  != 0){
            redirectAttributes.addFlashAttribute("alertAdd", 3);

            return new RedirectView("/codeproject/showForm");
        }

        run(codeProjectDto.toString());

        CodeProject codeProject = new CodeProject(
                codeProjectDto.getName(),
                codeProjectDto.getDescription(),
                LocalDate.parse(codeProjectDto.getStartDate()),
                Objects.equals(codeProjectDto.getCompleted(), "Tak"),
                frameworkRepository.findByName(codeProjectDto.getType()));

        run(codeProject.toString());

        repository.save(codeProject);

        if (Objects.equals(codeProjectDto.getEmpName(), "empty")){
            redirectAttributes.addFlashAttribute("alertAdd", 2);

            return new RedirectView("/classes/show");
        }

        redirectAttributes.addFlashAttribute("name", codeProjectDto.getName());
        redirectAttributes.addFlashAttribute("empName", codeProjectDto.getEmpName());

        return new RedirectView("/wait/add");
    }

    @Override
    public void run(java.lang.String... args) {
        System.out.println(Arrays.toString(args));
    }
}
