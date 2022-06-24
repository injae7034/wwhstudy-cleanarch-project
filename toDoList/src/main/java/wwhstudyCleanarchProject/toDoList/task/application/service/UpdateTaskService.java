package wwhstudyCleanarchProject.toDoList.task.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.toDoList.task.application.port.in.UpdateTaskUseCase;
import wwhstudyCleanarchProject.toDoList.task.application.port.out.UpdateTaskRepository;
import wwhstudyCleanarchProject.toDoList.task.domain.Task;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateTaskService implements UpdateTaskUseCase {

    private final UpdateTaskRepository repository;

    @Override
    public Task updateTask(Task task) {
        return repository.update(task);
    }
    
}
