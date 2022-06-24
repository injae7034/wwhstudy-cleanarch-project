package wwhstudyCleanarchProject.toDoList.task.adapter.in.web.add;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class AddTaskForm {

    @NotEmpty(message = "시작 시간을 입력해주세요.")
    private String startTime;
    @NotEmpty(message = "할 일을 입력해주세요.")
    private String title;
    @NotEmpty(message = "예상 소요시간을 입력해주세요.")
    private String estimatedTime;

}
