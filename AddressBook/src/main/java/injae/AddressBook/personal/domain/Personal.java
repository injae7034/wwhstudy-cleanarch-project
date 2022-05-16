package injae.AddressBook.personal.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public Personal(Long id, String name, String address,
                    String telephoneNumber, String emailAddress) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
    }

    public Personal(String name, String address,
                    String telephoneNumber, String emailAddress) {
        this(null, name, address, telephoneNumber, emailAddress);
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
