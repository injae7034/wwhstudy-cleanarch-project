package wwhstudyCleanarchProject.toDoList.task.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/toDoList")
public class ToDoListController {

    @GetMapping("/tasks")
    public String toDoList(Model model) {

        return "toDoList/todayToDoListForm";
    }
}
