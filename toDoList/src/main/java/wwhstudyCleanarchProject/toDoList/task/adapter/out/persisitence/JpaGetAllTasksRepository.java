package wwhstudyCleanarchProject.toDoList.task.adapter.out.persisitence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.toDoList.task.application.port.out.GetAllTasksRepository;
import wwhstudyCleanarchProject.toDoList.task.domain.Task;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaGetAllTasksRepository implements GetAllTasksRepository {

    private final EntityManager em;

    @Override
    public List<Task> findAll() {
        return em.createQuery("select t from Task t", Task.class)
                .getResultList();
    }
}
