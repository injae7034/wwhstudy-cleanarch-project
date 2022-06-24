package wwhstudyCleanarchProject.toDoList.task.adapter.out.persisitence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.toDoList.task.application.port.out.CreateTodayRepository;
import wwhstudyCleanarchProject.toDoList.task.domain.Date;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaCreateProductRepository implements CreateTodayRepository {

    private final EntityManager em;

    @Override
    public Date save(Date date) {
        em.persist(date);
        return date;
    }
}
