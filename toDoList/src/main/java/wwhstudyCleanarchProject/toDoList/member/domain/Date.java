package wwhstudyCleanarchProject.toDoList.member.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import wwhstudyCleanarchProject.toDoList.task.domain.Task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@NoArgsConstructor
public class Date {

    @Id @GeneratedValue
    @Column(name = "date_id")
    private Long id;

    private LocalDateTime toDoListDate;

    private int completedTasksCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "date")
    private List<Task> tasks = new ArrayList<>();
}
