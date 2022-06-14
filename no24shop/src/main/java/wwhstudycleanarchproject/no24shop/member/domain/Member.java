package wwhstudycleanarchproject.no24shop.member.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import wwhstudycleanarchproject.no24shop.common.CreateAndUpdateTimeAndBy;
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
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id; // 회원 아이디

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_type")
    private String type;

    private String password;

    private String role;

    @Embedded
    private Address address;

    private String email;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Embedded
    private CreateAndUpdateTimeAndBy timeAndBy;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "token_expiration_time")
    private String tokenExpirationTime;

    public Member(Long id, String name, String type, String password, String role,
                  Address address, String email, CreateAndUpdateTimeAndBy timeAndBy) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.password = password;
        this.role = role;
        this.address = address;
        this.email = email;
        this.timeAndBy = timeAndBy;
    }

    public Member(String name, String type, String password, String role,
                  Address address, String email, CreateAndUpdateTimeAndBy timeAndBy) {
        this(null, name, type, password, role, address, email, timeAndBy);
    }
}
