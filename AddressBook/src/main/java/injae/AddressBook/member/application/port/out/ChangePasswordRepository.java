package injae.AddressBook.member.application.port.out;

public interface ChangePasswordRepository {

    void update(Long memberId, String changePassword);

}
