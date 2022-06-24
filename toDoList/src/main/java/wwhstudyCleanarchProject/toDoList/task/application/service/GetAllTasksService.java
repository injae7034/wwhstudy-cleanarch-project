package wwhstudyCleanarchProject.toDoList.task.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwhstudyCleanarchProject.toDoList.task.application.port.in.GetAllTasksQuery;
import wwhstudyCleanarchProject.toDoList.task.application.port.out.GetAllTasksRepository;
import wwhstudyCleanarchProject.toDoList.task.domain.Task;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllTasksService implements GetAllTasksQuery {

    private final GetAllTasksRepository repository;

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }
}
