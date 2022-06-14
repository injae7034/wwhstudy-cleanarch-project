package wwhstudycleanarchproject.no24shop.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wwhstudycleanarchproject.no24shop.common.BaseTimeEntity;
import wwhstudycleanarchproject.no24shop.domain.Address;
import wwhstudycleanarchproject.no24shop.domain.Order;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id; // 회원 아이디

    @Column(name = "user_id")
    private String email;
    @Column(name = "user_password")
    private String password;
    @Column(name = "member_name")
    private String name;
    @Embedded
    private Address address;

    @Column(name = "member_type")
    private String type;

    private String role;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Member(Long id, String email, String password,
                  String name, Address address, String type, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.type = type;
        this.role = role;
    }

    public Member(String email, String password,
                  String name, Address address, String type, String role) {
        this(null, email, password, name, address, type, role);
    }
}
