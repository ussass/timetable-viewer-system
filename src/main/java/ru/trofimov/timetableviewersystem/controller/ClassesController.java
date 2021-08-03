package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.service.ClassesService;
import ru.trofimov.timetableviewersystem.service.GroupService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/classes")
public class ClassesController {
    private final ClassesService classesService;
    private final GroupService groupService;

    public ClassesController(ClassesService classesService, GroupService groupService) {
        this.classesService = classesService;
        this.groupService = groupService;
    }

    @GetMapping()
    public String showIndex(Model model) {
        model.addAttribute("active", "classes");
        try {
            model.addAttribute("groups", groupService.findAll());
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }
        return "classes/index";
    }

    @PostMapping("/search")
    public String searchClasses(RedirectAttributes attributes,
                                @RequestParam long group,
                                @RequestParam String start,
                                @RequestParam String end
    ) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date dateStart;
        Date dateEnd;
        try {
            dateStart = simpleDateFormat.parse(start);
        } catch (ParseException e) {
            dateStart = new Date(1);
        }
        try {
            dateEnd = simpleDateFormat.parse(end);
        } catch (ParseException e) {
            dateEnd = new Date(Long.MAX_VALUE);
        }
        attributes.addAttribute("groupId", group);
        attributes.addAttribute("dateStart", dateStart.getTime());
        attributes.addAttribute("dateEnd", dateEnd.getTime());
        return "redirect:/classes/show";
    }

    @GetMapping("/show")
    public String showTimetable(Model model,
                                @RequestParam long groupId,
                                @RequestParam long dateStart,
                                @RequestParam long dateEnd
    ) {
        System.out.println(groupId);
        System.out.println(dateStart);
        System.out.println(dateEnd);
        List<Classes> classesList = null;
        try {
            classesList = groupService.getGroupTimetable(groupId, dateStart, dateEnd);
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }
        model.addAttribute("classesList", classesList);
        return "classes/show";
    }
}
