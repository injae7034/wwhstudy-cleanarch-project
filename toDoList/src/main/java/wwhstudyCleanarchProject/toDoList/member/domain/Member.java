package wwhstudyCleanarchProject.toDoList.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wwhstudyCleanarchProject.toDoList.task.domain.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id")
    private String email;
    private String password;

    private String name;

    @OneToMany(mappedBy = "member")
    private Map<String, Date> dates = new LinkedHashMap<>();

    public Member(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Member(String email, String password, String name) {
        this(null, email, password, name);
    }

    public void changePassword(String password) {
        this.password = password;
    }

}
