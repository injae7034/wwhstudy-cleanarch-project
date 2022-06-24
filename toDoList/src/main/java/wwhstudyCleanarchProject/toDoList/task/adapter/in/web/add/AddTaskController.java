package wwhstudyCleanarchProject.toDoList.task.adapter.in.web.add;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import wwhstudyCleanarchProject.toDoList.task.domain.Date;
import wwhstudyCleanarchProject.toDoList.member.domain.Member;
import wwhstudyCleanarchProject.toDoList.task.application.port.in.AddTaskUseCase;
import wwhstudyCleanarchProject.toDoList.task.application.port.in.CreateTodayUseCase;
import wwhstudyCleanarchProject.toDoList.task.domain.Task;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class AddTaskController {

    private final AddTaskUseCase taskUseCase;
    private final CreateTodayUseCase dateUseCase;


    @GetMapping("/add")
    public String addTaskForm(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
                              Model model) {

        model.addAttribute("addTaskForm", new AddTaskForm());

//        List<EstimatedTime> estimatedTimes = makeEstimatedTimes();
//        model.addAttribute("estimatedTimes", estimatedTimes);

        return "toDoList/addTaskForm";
    }

    private List<EstimatedTime> makeEstimatedTimes() {
        List<EstimatedTime> estimatedTimes = new ArrayList<>();
        estimatedTimes.add(new EstimatedTime("15", "15분"));
        estimatedTimes.add(new EstimatedTime("30", "30분"));
        estimatedTimes.add(new EstimatedTime("45", "45분"));
        estimatedTimes.add(new EstimatedTime("60", "60분"));
        return estimatedTimes;
    }

    @PostMapping("/add")
    public String addTask(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
            @Valid @ModelAttribute AddTaskForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "toDoList/addTaskForm";
        }

        Date today = new Date(LocalDate.now().toString(), 0, loginMember);
        Task task = new Task(form.getStartTime(), form.getTitle(),
                form.getEstimatedTime(), false, today);
        today.addTask(task);

        dateUseCase.createToday(today);
        taskUseCase.addTask(task);

        return "toDoList/todayToDoListForm";
    }

}
