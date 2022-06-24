package wwhstudyCleanarchProject.toDoList.task.adapter.out.persisitence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.toDoList.task.application.port.out.GetTaskRepository;
import wwhstudyCleanarchProject.toDoList.task.domain.Task;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaGetTaskRepository implements GetTaskRepository {

    private final EntityManager em;

    @Override
    public Task findOne(Long id) {
        return em.find(Task.class, id);
    }
}
