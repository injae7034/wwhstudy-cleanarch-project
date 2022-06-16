package wwhstudycleanarchproject.smallShop.Member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "user_id")
    private String email;
    private String password;

    private String name;
    private String address;

    public Member(Long id, String email, String password, String name, String address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public Member(String email, String password, String name, String address) {
        this(null, email, password, name, address);
    }

    public void changeMemberInfo(Member member) {
        String password = member.getPassword();
        if (this.password.compareTo(password) != 0) {
            this.password = password;
        }

        String name = member.getName();
        if (this.name.compareTo(name) != 0) {
            this.name = name;
        }

        String address = member.getAddress();
        if (this.address != address) {
            this.address = address;
        }
    }
}
