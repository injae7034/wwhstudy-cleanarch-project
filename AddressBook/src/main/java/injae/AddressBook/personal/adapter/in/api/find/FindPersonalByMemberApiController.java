package injae.AddressBook.personal.adapter.in.api.find;

import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
import injae.AddressBook.member.exception.MemberNotFoundException;
import injae.AddressBook.personal.adapter.in.api.get.personal.GetPersonalByMemberResponse;
import injae.AddressBook.personal.application.port.in.FindPersonalUseCase;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FindPersonalByMemberApiController {

    private final FindMemberQuery findMemberQuery;

    private final FindPersonalUseCase findPersonalUseCase;

    @PostMapping("/members/{memberId}/personals/find")
    public ResponseEntity<FindPersonalByMemberResponse> findPersonalByMember(
            @PathVariable Long memberId,
            @RequestBody @Valid
                    FindPersonalByMemberRequest request) {

        Member findMember = findMemberQuery.findMember(memberId);

        if (findMember == null) {
            throw new MemberNotFoundException("해당 id와 일치하는 멤버를 찾을 수 없습니다.");
        }

        List<Personal> findPersonals = findPersonalUseCase.findPersonalByName(
                findMember, request.getName());

        List<GetPersonalByMemberResponse> getPersonalsResponses = new ArrayList<>();

        for (Personal personal : findPersonals) {
            getPersonalsResponses.add(new GetPersonalByMemberResponse(
                    personal.getName(),
                    personal.getAddress(),
                    personal.getTelephoneNumber(),
                    personal.getEmailAddress()));
        }

        int count = getPersonalsResponses.size();

        FindPersonalByMemberResponse findPersonalByMemberResponse =
                new FindPersonalByMemberResponse(
                        count, getPersonalsResponses);

        if (count == 0) {
            return new ResponseEntity<>(findPersonalByMemberResponse, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(findPersonalByMemberResponse, HttpStatus.OK);
    }

}
