package injae.AddressBook.personal.domain;

import injae.AddressBook.member.domain.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "id")
public class Personal {

    @Id @GeneratedValue
    @Column(name = "personal_id")
    private Long id;

    private String name;
    private String address;
    private String telephoneNumber;
    private String emailAddress;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Personal(Long id, String name, String address,
                    String telephoneNumber, String emailAddress, Member member) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.member = member;
    }

    public Personal(String name, String address,
                    String telephoneNumber, String emailAddress, Member member) {
        this(null, name, address, telephoneNumber, emailAddress, member);
    }

    public void changePersonalInfo(Personal personal) {
        String address = personal.getAddress();
        if (this.address.compareTo(address) != 0) {
            this.address = address;
        }

        String telephoneNumber = personal.getTelephoneNumber();
        if (this.telephoneNumber.compareTo(telephoneNumber) != 0) {
            this.telephoneNumber = telephoneNumber;
        }

        String emailAddress = personal.getEmailAddress();
        if (this.emailAddress.compareTo(emailAddress) != 0) {
            this.emailAddress = emailAddress;
        }
    }

}
