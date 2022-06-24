package wwhstudyCleanarchProject.toDoList.task.application.port.out;

import wwhstudyCleanarchProject.toDoList.task.domain.Task;

public interface AddTaskRepository {

    Task save(Task task);

}
