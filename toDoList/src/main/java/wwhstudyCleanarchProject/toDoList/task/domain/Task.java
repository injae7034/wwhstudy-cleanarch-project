package wwhstudyCleanarchProject.toDoList.task.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wwhstudyCleanarchProject.toDoList.member.domain.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;


@Entity
@Getter
@NoArgsConstructor
public class Task {

    @Id @GeneratedValue
    @Column(name = "task_id")
    private Long id;

    private LocalDateTime startTime;
    private String title;
    private String estimatedTime;
    private boolean isFinished;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "date_id")
    private Date date;
}
