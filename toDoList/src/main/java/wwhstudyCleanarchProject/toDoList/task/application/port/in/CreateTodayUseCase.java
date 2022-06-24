package wwhstudyCleanarchProject.toDoList.task.application.port.in;

import wwhstudyCleanarchProject.toDoList.task.domain.Date;

public interface CreateTodayUseCase {

    Date createToday(Date date);

}
