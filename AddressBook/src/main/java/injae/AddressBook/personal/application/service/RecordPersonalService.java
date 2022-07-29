package injae.AddressBook.personal.application.service;

import injae.AddressBook.member.application.port.out.FindByEmailRepository;
import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.in.RecordPersonalUseCase;
import injae.AddressBook.personal.application.port.out.RecordPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RecordPersonalService implements RecordPersonalUseCase {

    private final RecordPersonalRepository recordRepository;

    private final FindByEmailRepository findRepository;

    @Override
    public Long recordPersonal(String name, String address, String telephoneNumber,
                               String emailAddress, Member member) {

        //db에 저장할 새로운 personal 객체 생성
        Personal personal = new Personal(name, address, telephoneNumber,
                emailAddress, member);

        //db에 저장(personal은 member의 참조값을 알고 있음)
        recordRepository.save(personal);

        member.getPersonals().add(personal);

        return personal.getId();

        //member는 현재 personal의 참조값을 모르기 때문에 연관관계를 맺어줘야 함.
        // 멤버를 찾음으로써 영속성 컨텍스트를 활성화시킨다.
//        Member findMember = findRepository.findByEmail(member.getEmail()).orElse(null);
//
//        //이메일에 해당하는 멤버가 있으면
//        if (findMember != null) {
//            //멤버에 새로 생성한 personal의 참조값을 추가한다.
//            findMember.getPersonals().add(personal);
//
//            return personal.getId();
//        } else {
//            //멤버가 없으면 예외를 발생시킨다.
//            throw new IllegalStateException();
//        }
    }
}
