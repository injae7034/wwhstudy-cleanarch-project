package wwhstudyCleanarchProject.toDoList.task.application.port.out;

import wwhstudyCleanarchProject.toDoList.task.domain.Task;

public interface GetTaskRepository {

    Task findOne(Long id);

}
