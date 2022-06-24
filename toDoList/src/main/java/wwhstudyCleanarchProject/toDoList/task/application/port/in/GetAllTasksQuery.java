package wwhstudyCleanarchProject.toDoList.task.application.port.in;

import wwhstudyCleanarchProject.toDoList.task.domain.Task;

import java.util.List;

public interface GetAllTasksQuery {

    List<Task> getAllTasks();

}
