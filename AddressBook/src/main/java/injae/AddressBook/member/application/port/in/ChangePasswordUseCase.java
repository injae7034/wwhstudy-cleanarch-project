package injae.AddressBook.member.application.port.in;

public interface ChangePasswordUseCase {

    void changePassword(Long memberId, String changePassword);

}
