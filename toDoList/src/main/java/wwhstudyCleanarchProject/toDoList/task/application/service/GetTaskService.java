package wwhstudyCleanarchProject.toDoList.task.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwhstudyCleanarchProject.toDoList.task.application.port.in.GetTaskQuery;
import wwhstudyCleanarchProject.toDoList.task.application.port.out.GetTaskRepository;
import wwhstudyCleanarchProject.toDoList.task.domain.Task;

@RequiredArgsConstructor
@Service
public class GetTaskService implements GetTaskQuery {

    private final GetTaskRepository repository;

    @Override
    public Task getTask(Long id) {
        return repository.findOne(id);
    }

}
