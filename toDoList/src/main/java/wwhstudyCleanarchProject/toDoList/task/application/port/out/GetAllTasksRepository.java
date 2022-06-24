package wwhstudyCleanarchProject.toDoList.task.application.port.out;

import wwhstudyCleanarchProject.toDoList.task.domain.Task;

import java.util.List;

public interface GetAllTasksRepository {

    List<Task> findAll();

}
