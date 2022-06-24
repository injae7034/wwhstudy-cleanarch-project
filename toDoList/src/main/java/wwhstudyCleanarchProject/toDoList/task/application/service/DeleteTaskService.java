package wwhstudyCleanarchProject.toDoList.task.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.toDoList.task.application.port.in.DeleteTaskUseCase;
import wwhstudyCleanarchProject.toDoList.task.application.port.out.DeleteTaskRepository;
import wwhstudyCleanarchProject.toDoList.task.domain.Task;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteTaskService implements DeleteTaskUseCase {

    private final DeleteTaskRepository repository;

    @Override
    public Task deleteTask(Task task) {
        return repository.delete(task);
    }

}
