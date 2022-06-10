package wwhstudycleanarchproject.no24shop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id; // 회원 아이디

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime; // 최초 생성 시간(변경 X)

    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime; // 마지막 수정 시간

    @CreatedBy
    @Column(name = "create_by", updatable = false)
    private String createdBy; // 최초에 생성한 사람(변경 X)

    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy; // 마지막에 수정한 사람

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_type")
    private String type;

    private String password;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "token_expiration_time")
    private String tokenExpirationTime;

    private String role;

    @Embedded
    private Address address;

    private String email;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
