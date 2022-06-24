package wwhstudyCleanarchProject.toDoList.task.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.hibernate.LazyInitializationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import wwhstudyCleanarchProject.toDoList.task.domain.Date;
import wwhstudyCleanarchProject.toDoList.member.domain.Member;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskHomeController {

    @GetMapping
    public String toDoList(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
                           Model model) {
        Date today;
        try {
            today = loginMember.getDates().get(LocalDate.now().toString());
        } catch (NullPointerException e) {
            return "toDoList/todayToDoListForm";
        } catch (LazyInitializationException e) {
            return "toDoList/todayToDoListForm";
        }
        if (today != null) {
            model.addAttribute("tasks", today.getTasks());
        }
        return "toDoList/todayToDoListForm";
    }


}
