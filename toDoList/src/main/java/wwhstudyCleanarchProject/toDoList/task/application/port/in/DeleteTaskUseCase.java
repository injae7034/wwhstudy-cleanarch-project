package wwhstudyCleanarchProject.toDoList.task.application.port.in;

import wwhstudyCleanarchProject.toDoList.task.domain.Task;

public interface DeleteTaskUseCase {

    Task deleteTask(Task task);

}
