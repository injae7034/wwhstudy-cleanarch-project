package wwhstudyCleanarchProject.toDoList.task.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.toDoList.task.application.port.in.AddTaskUseCase;
import wwhstudyCleanarchProject.toDoList.task.application.port.out.AddTaskRepository;
import wwhstudyCleanarchProject.toDoList.task.domain.Task;

@RequiredArgsConstructor
@Transactional
@Service
public class AddTaskService implements AddTaskUseCase {

    private final AddTaskRepository repository;

    @Override
    public Task addTask(Task task) {
        return repository.save(task);
    }

}
