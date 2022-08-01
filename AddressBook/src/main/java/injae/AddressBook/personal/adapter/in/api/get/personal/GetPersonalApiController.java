package injae.AddressBook.personal.adapter.in.api.get.personal;

import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
import injae.AddressBook.member.exception.MemberNotFoundException;
import injae.AddressBook.personal.application.port.in.GetPersonalQuery;
import injae.AddressBook.personal.domain.Personal;
import injae.AddressBook.personal.exception.PersonalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetPersonalApiController {

    private final FindMemberQuery findMemberQuery;

    private final GetPersonalQuery getPersonalQuery;

    @GetMapping("/members/{memberId}/personals/{personalId}")
    public GetPersonalResponse getPersonal(@PathVariable Long memberId,
                                           @PathVariable Long personalId) {

        Member findMember = findMemberQuery.findMember(memberId);

        if (findMember == null) {
            throw new MemberNotFoundException("해당 id와 일치하는 멤버를 찾을 수 없습니다.");
        }

        Personal findPersonal = getPersonalQuery.getPersonal(findMember, personalId);

        if (findPersonal == null) {
            throw new PersonalNotFoundException("해당하는 개인 정보를 찾을 수 없습니다.");
        }

        return new GetPersonalResponse(
                findPersonal.getName(),
                findPersonal.getAddress(),
                findPersonal.getTelephoneNumber(),
                findPersonal.getEmailAddress());
    }
}
