package mas.Services;

import mas.Models.Framework;
import mas.Models.TypeOfInitiative;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.util.Pair;
import org.springframework.ui.Model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseService implements CommandLineRunner {

    public Pair<List<String>, List<List<String>>> getFieldsNames(Class<?> cl, List<?> listOfRows) {

        Field[] fields1 = cl.getDeclaredFields();
        Field[] superClassFields = cl.getSuperclass().getDeclaredFields();

        Field[] fields = new Field[fields1.length+superClassFields.length];

        System.arraycopy(superClassFields, 0, fields, 0, superClassFields.length);
        System.arraycopy(fields1, 0, fields, superClassFields.length, fields1.length);

        run(Arrays.toString(fields));

        List<String> fieldsList = Arrays
                .stream(fields)
                .filter(field -> field.getType() != Map.class
                        && field.getType() != List.class
                        && field.getType() != Framework.class
                        && field.getType() != TypeOfInitiative.class)
                .map(Field::getName)
                .toList();
        List<List<String>> propertiesList =
                listOfRows.stream()
                        .map(o -> fieldsList.stream()
                                .flatMap(field -> Arrays.stream(cl.getMethods())
                                        .filter(method -> method.getName().startsWith("get") && method.getName().length() == field.length() + 3)
                                        .filter(method -> method.getName().toLowerCase().endsWith(field.toLowerCase()))
                                        .flatMap(method -> {
                                            try {
                                                return Stream.of(method.invoke(o).toString());
                                            } catch (InvocationTargetException | IllegalAccessException e) {
                                                throw new RuntimeException(e);
                                            }
                                        })
                                )
                                .collect(Collectors.toList())
                        ).collect(Collectors.toList());
        run(propertiesList.toString());
        return Pair.of(fieldsList, propertiesList);
    }

    public void setModelToShowAll(Class<?> cl, List<?> lor, Model model) {
        Pair<List<String>, List<List<String>>> pair = getFieldsNames(cl, lor);
        model.addAttribute("fieldsList", pair.getFirst());
        model.addAttribute("listOfListOfProperties", pair.getSecond());
        model.addAttribute("className", cl.getSimpleName().toLowerCase().toLowerCase());
    }

    public void setModelToForm(Class<?> cl, Model model){
        Field[] fields = cl.getDeclaredFields();
        List<String> fieldsList = Arrays
                .stream(fields)
                .filter(field -> field.getType() != Map.class && field.getType() != List.class)
                .map(Field::getName)
                .toList();
        model.addAttribute("fieldsList", fieldsList);
        model.addAttribute("className", cl.getSimpleName().toLowerCase().toLowerCase());
    }

    @Override
    public void run(String... args) {
        for (String s : args) {
            System.out.println(s);
        }
    }
}