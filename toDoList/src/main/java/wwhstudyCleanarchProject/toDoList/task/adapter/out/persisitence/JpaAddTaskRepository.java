package wwhstudyCleanarchProject.toDoList.task.adapter.out.persisitence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.toDoList.task.application.port.out.AddTaskRepository;
import wwhstudyCleanarchProject.toDoList.task.domain.Task;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaAddTaskRepository implements AddTaskRepository {

    private final EntityManager em;

    @Override
    public Task save(Task task) {
        em.persist(task);
        return task;
    }
}
