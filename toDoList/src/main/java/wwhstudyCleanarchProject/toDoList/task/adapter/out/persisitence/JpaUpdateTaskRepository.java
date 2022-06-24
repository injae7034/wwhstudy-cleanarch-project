package wwhstudyCleanarchProject.toDoList.task.adapter.out.persisitence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.toDoList.task.application.port.out.UpdateTaskRepository;
import wwhstudyCleanarchProject.toDoList.task.domain.Task;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaUpdateTaskRepository implements UpdateTaskRepository {

    private final EntityManager em;

    @Override
    public Task update(Task task) {

        Task findTask = em.find(Task.class, task.getId());

        findTask.changeTaskInfo(task);

        return findTask;
    }
}
