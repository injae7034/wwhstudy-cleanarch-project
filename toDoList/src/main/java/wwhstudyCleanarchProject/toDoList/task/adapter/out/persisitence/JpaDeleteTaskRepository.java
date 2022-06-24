package wwhstudyCleanarchProject.toDoList.task.adapter.out.persisitence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.toDoList.task.application.port.out.DeleteTaskRepository;
import wwhstudyCleanarchProject.toDoList.task.domain.Task;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaDeleteTaskRepository implements DeleteTaskRepository {

    private final EntityManager em;

    @Override
    public Task delete(Task task) {
        em.remove(task);
        return task;
    }
}
