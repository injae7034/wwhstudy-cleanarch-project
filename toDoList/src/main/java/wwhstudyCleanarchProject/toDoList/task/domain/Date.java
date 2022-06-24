package wwhstudyCleanarchProject.toDoList.task.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wwhstudyCleanarchProject.toDoList.member.domain.Member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Date {

    @Id
    @GeneratedValue
    @Column(name = "date_id")
    private Long id;

    private String toDoListDate;

    private int completedTasksCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "date")
    private List<Task> tasks = new ArrayList<>();

    public Date(String toDoListDate, int completedTasksCount, Member member) {
        this.toDoListDate = toDoListDate;
        this.completedTasksCount = completedTasksCount;
        this.member = member;
    }

    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }
}
