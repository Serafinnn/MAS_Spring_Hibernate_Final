package mas.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("classes")
public class ClassesController {


    @GetMapping("/show")
    public String showClasses(Model model) {
        Set<String> names;
        names = Stream.of(Objects.requireNonNull(new File("src/main/java/mas/Models").listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .map(name -> name.substring(0, name.indexOf('.')))
                .collect(Collectors.toSet());
        model.addAttribute("names", names);
        return "classesList";
    }
}