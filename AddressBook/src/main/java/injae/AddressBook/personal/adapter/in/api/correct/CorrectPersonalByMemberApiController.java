package injae.AddressBook.personal.adapter.in.api.correct;

import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
import injae.AddressBook.member.exception.MemberNotFoundException;
import injae.AddressBook.personal.application.port.in.CorrectPersonalUseCase;
import injae.AddressBook.personal.application.port.in.GetPersonalQuery;
import injae.AddressBook.personal.domain.Personal;
import injae.AddressBook.personal.exception.PersonalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CorrectPersonalByMemberApiController {

    private final FindMemberQuery findMemberQuery;
    private final GetPersonalQuery getPersonalQuery;
    private final CorrectPersonalUseCase correctPersonalUseCase;

    @PutMapping("members/{memberId}/personals/{personalId}")
    public void correctPersonalByMember(
            @PathVariable Long memberId, @PathVariable Long personalId,
            @RequestBody @Valid CorrectPersonalByMemberRequest request) {

        Member findMember = findMemberQuery.findMember(memberId);

        if (findMember == null) {
            throw new MemberNotFoundException("해당 id와 일치하는 멤버를 찾을 수 없습니다.");
        }

        Personal findPersonal = getPersonalQuery.getPersonal(findMember, personalId);

        if (findPersonal == null) {
            throw new PersonalNotFoundException("해당하는 개인 정보를 찾을 수 없습니다.");
        }

        correctPersonalUseCase.correctPersonal(personalId, request.getAddress(),
                request.getTelephoneNumber(), request.getEmailAddress());
    }

}
