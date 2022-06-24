package wwhstudyCleanarchProject.toDoList.task.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.toDoList.task.application.port.in.CreateTodayUseCase;
import wwhstudyCleanarchProject.toDoList.task.application.port.out.CreateTodayRepository;
import wwhstudyCleanarchProject.toDoList.task.domain.Date;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateTodayService implements CreateTodayUseCase {

    private final CreateTodayRepository repository;

    @Override
    public Date createToday(Date date) {
        return repository.save(date);
    }
}
