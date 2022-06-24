package wwhstudyCleanarchProject.toDoList.task.application.port.out;

import wwhstudyCleanarchProject.toDoList.task.domain.Date;

public interface CreateTodayRepository {

    Date save(Date date);

}
